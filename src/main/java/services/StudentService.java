package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.cert.ocsp.Req;
import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.util.DateTime;

import common.PageInfo;
import common.PageType;
import lombok.extern.log4j.Log4j2;
import model.NhanVien;
import model.Nhom;
import model.SinhVien;
import model.ThongTinNhom;
import repositories.ChuyenNganhRepositories;
import repositories.GvHuongDanRepositories;
import repositories.NhanVienRepositories;
import repositories.NhomRepositories;
import repositories.SinhVienRepositories;

@Log4j2
public class StudentService {
	private SinhVien sv;
	public final Integer SV_DANG_KY_DATN = 1;
	public final Integer SV_CHUA_DANG_KY_DATN = 0;
	public final Integer SV_CO_NHOM = 1;
	public final Integer SV_CHUA_CO_NHOM = 0;
	private Integer dot_dang_ky, co_so_id, chuyen_nganh_id;
	
	private RegisterService res_service;
	private CreateTService cre_ser;
	private JoinTeamService join_service;
	private ChuyenNganhRepositories cn;
	private NhomRepositories nhomRep;
	private Nhom nhom;
	private NhanVienRepositories nvRep;
	private GvHuongDanRepositories gvHuongDanRepo;
	private SinhVienRepositories svRep;
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
	public StudentService() {
		this.res_service = new RegisterService();
		this.cre_ser = new CreateTService();
		this.join_service = new JoinTeamService();
		this.cn = new ChuyenNganhRepositories();
		this.nhomRep = new NhomRepositories();
		this.nvRep = new NhanVienRepositories();
		this.gvHuongDanRepo = new GvHuongDanRepositories();
		this.svRep = new SinhVienRepositories();

	}

	public void prepareAndForward(HttpServletRequest request, HttpServletResponse response, PageType pageType)
			throws ServletException, IOException {
		try {
			PageInfo page = PageInfo.pageRoute.get(pageType);
			request.setAttribute("page", page);
			request.setAttribute("path", request.getContextPath());
			String uri = request.getRequestURI();
			if (uri.contains("logout")) {
				request.getSession().setAttribute("student", null);
				response.sendRedirect(request.getContextPath() + "/Student");
				return;
			}
	 
			this.sv = (SinhVien) request.getSession().getAttribute("student");

			this.displayStatus(request, response);
			
			
			String chuyenNganh = this.cn.getTenById(sv.getChuyenNganhId());

			request.setAttribute("chuyenNganhSV", chuyenNganh);
			request.setAttribute("sv", sv);
 			hienThiDsachGV(request, response);
			
			if (sv.getNhomId() != null) {
				this.nhom = this.nhomRep.findNhomId(sv.getNhomId());
				request.setAttribute("tenDeTai", nhom.getTenDeTai());
				request.getSession().setAttribute("ScopeTT", nhom.getCode()==null?1:0);
				hienThiDanhSachTvNhom(request, response, nhom);
				if (this.nhom.getNhomTruongId().equalsIgnoreCase(sv.getMaSv())) {
					request.setAttribute("capNhatValues", false);
				} else {
					request.setAttribute("capNhatValues", true);
				}
				int idgv = this.nhom.getGiangVienHdId();
				//set Scope
				request.getSession().setAttribute("ScopeGV",idgv);
			}
			
			
			// set liên hệ PDT
			List<Object[]> obj = this.nvRep.getNhanVienPDT(sv.getCoSoId());
			if (obj != null) {
				String email =  (String) obj.get(0)[0];
				String sub_email = email.substring(0, email.indexOf("@")+1);
				String pattern = "fe.edu.vn";
				email=sub_email+pattern;
				request.setAttribute("email", email);
				request.setAttribute("ten", obj.get(0)[1]);
			}
			request.getRequestDispatcher("/views/index.jsp").forward(request, response);
		} catch (Exception e) {
			thongBaoLoi(request, response, "Vui lòng thử lại sau");
		}
	}

	private void displayStatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("SinhVien", sv);
		if (sv.getTrangThai() == SV_DANG_KY_DATN) {
			request.setAttribute("trang_thai", SV_DANG_KY_DATN);
		} else {
			request.setAttribute("trang_thai", SV_CHUA_DANG_KY_DATN);
		}
		if (sv.getNhomId() == null || sv.getNhomId() == 0) {
			request.setAttribute("gianhap_roi_nhom", SV_CHUA_CO_NHOM);
		} else {
			request.setAttribute("gianhap_roi_nhom", SV_CO_NHOM);

		}
		getAllNhom(request, response, sv);
	}

	public void prepareAndDirect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		SinhVien sv = (SinhVien) request.getSession().getAttribute("student");

		if (uri.contains("huy-dang-ky")) {
			sv = this.res_service.capNhatTrangThaiSV(sv, SV_CHUA_DANG_KY_DATN);
			if (sv == null) {
				thongBaoLoi(request, response, "Bạn chưa vào chưa đủ 1 ngày để rời nhóm và hủy đăng ký nhé");
				return;
			}
		} else if (uri.contains("dang-ky")) {
			this.res_service.capNhatTrangThaiSV(sv, SV_DANG_KY_DATN);
		} else if (uri.contains("out-nhom")) {
			sv = this.res_service.roiNhom(sv);
			if (sv == null) {
				thongBaoLoi(request, response, "Bạn chưa vào chưa đủ 1 ngày để rời nhóm");
				return;
			}
		} else if (uri.contains("tao-nhom")) {
			sv = taonhom(request, response, sv);

		} else if (uri.contains("join-private")) {
			thongBaoLoiJSON(request, response);
		} else if (uri.contains("join-public")) {
			String id = request.getParameter("nhom_id");
			this.join_service.joinPublicTeam(sv, id);
			if (sv.getTrangThai() == SV_CHUA_DANG_KY_DATN) {
				this.res_service.capNhatTrangThaiSV(sv, SV_DANG_KY_DATN);
			}
		} else if (uri.contains("update-team")) {
			capNhatNhom(request, response, this.nhom);
		}
		request.getSession().setAttribute("student", sv);
		response.sendRedirect(request.getContextPath() + "/Student/dashboard");
	}

	private void thongBaoLoi(HttpServletRequest request, HttpServletResponse response, String text)
			throws ServletException, IOException {
		String duongDanHome = request.getContextPath() + "/Student";
		request.setAttribute("path", duongDanHome);
		request.setAttribute("message", text);
		request.getRequestDispatcher("/views/404.jsp").forward(request, response);
	}

	private void thongBaoLoiJSON(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }

		  try {
		    JSONObject jsonObject =  HTTP.toJSONObject(jb.toString());
		    Map<String, Object> map =jsonObject.toMap();
		    String abc = map.get("Method").toString();
		    JSONObject obs = new JSONObject(abc);
		    String code =  obs.get("code_join").toString().trim();
		    this.join_service.joinPrivateTeam(sv, code);		    
			if (sv.getTrangThai() == SV_CHUA_DANG_KY_DATN) {
				this.res_service.capNhatTrangThaiSV(sv, SV_DANG_KY_DATN);
			}
		  } catch (JSONException e) {
		    // crash and burn
		    throw new IOException("Error parsing JSON request string");
		  }
	}

	private void getAllNhom(HttpServletRequest request, HttpServletResponse response, SinhVien sv)
			throws ServletException, IOException {
		try {
			//repositories.NhomRepositories nhom_repositories = new NhomRepositories();
			Integer boMon =  this.cn.getBoMonbyChuyenNganhId(sv.getChuyenNganhId());
			
			List<Object[]> lst = this.nhomRep.getAllNhomByCoSoBoMon(sv.getCoSoId(), boMon, sv.getDotDangKyId());

			request.setAttribute("Nhoms", lst);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	private SinhVien taonhom(HttpServletRequest request, HttpServletResponse response, SinhVien sv)
			throws UnsupportedEncodingException, IOException, ServletException {
		try {
			String gvid = request.getParameter("giang_vien_id");
			int gv_id = utils.HelperUtils.parseInteger(gvid);
			String trangThaiNhom = request.getParameter("is_public");
			String tenDeTai = request.getParameter("ten_de_tai");
			tenDeTai = new String(tenDeTai.getBytes("ISO8859_1"), "UTF-8");
			long tongNhom = this.gvHuongDanRepo.getSum(gv_id);
			Integer soNhom = this.gvHuongDanRepo.selectSoNhomHD(gv_id);
			if(tongNhom == soNhom) {
				request.setAttribute("ErrorCT", "no");
			} else {
				request.setAttribute("ErrorCT", "yes");
				sv = this.cre_ser.createTeam(sv, gv_id, trangThaiNhom, tenDeTai, request);
				if (sv.getTrangThai() == SV_CHUA_DANG_KY_DATN) {
					this.res_service.capNhatTrangThaiSV(sv, SV_DANG_KY_DATN);
				}
			} 
			
		} catch (Exception e) {
			thongBaoLoi(request, response, "Lỗi tạo nhóm");
		}
//		
		return sv;
		
	}

	private void hienThiDsachGV(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		SinhVien sv = (SinhVien) request.getSession().getAttribute("student");

		String ten = this.cn.getTenById(sv.getChuyenNganhId());
		request.setAttribute("chuyenNganhSV", ten);

		this.dot_dang_ky = sv.getDotDangKyId();
		this.co_so_id = sv.getCoSoId();
		this.chuyen_nganh_id = sv.getChuyenNganhId();

		List<Object[]> giangVien = this.nvRep.getNv_Gv(3, this.dot_dang_ky, this.co_so_id, this.chuyen_nganh_id);
		List<Object[]> giangVien1 = this.nvRep.getNv_Gv(2, this.dot_dang_ky, this.co_so_id, this.chuyen_nganh_id);
		giangVien.addAll(giangVien1);
		request.setAttribute("gv",giangVien );
	}
	
	private void capNhatNhom(HttpServletRequest req, HttpServletResponse res, Nhom nhom)
			throws UnsupportedEncodingException {
		String gv_id = req.getParameter("giang_vien_id");
		String trangThaiNhom = req.getParameter("is_public");
		
		
		String tenDeTai = req.getParameter("ten_de_tai");

		tenDeTai = new String(tenDeTai.getBytes("ISO8859_1"), "UTF-8");
		
		Integer gvid = utils.HelperUtils.parseInteger(gv_id);
		long tongNhom = this.gvHuongDanRepo.getSum(gvid);
		Integer soNhom = this.gvHuongDanRepo.selectSoNhomHD(gvid);
		
		if(tongNhom==soNhom) {
			req.setAttribute("ErrorCT", "no");
		} else {
			req.setAttribute("ErrorCT", "yes");
			this.cre_ser.updateTeam(nhom, sv, gv_id, trangThaiNhom, tenDeTai);
			
			if (sv.getTrangThai() == SV_CHUA_DANG_KY_DATN) {
				this.res_service.capNhatTrangThaiSV(sv, SV_DANG_KY_DATN);
			}
		}
		
	}

	private void hienThiDanhSachTvNhom(HttpServletRequest req, HttpServletResponse res, Nhom nhom) {
		int gv_id = nhom.getGiangVienHdId();
		int nv_id = this.gvHuongDanRepo.getMaNV(gv_id);
		String tenNV = this.nvRep.getTenById(nv_id);

		if (nhom.getCode() != null) {
			req.setAttribute("maCode", nhom.getCode());
		} else {
			req.setAttribute("maCode", "null");
		}
		req.setAttribute("giangVien", tenNV);

		int nhom_id = nhom.getId();

		List<Object[]> lst = this.svRep.selectSVByNhomIdOb(nhom_id);
		req.setAttribute("sinhVien", lst);
	}
}
