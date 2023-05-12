package services;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.PageInfo;
import common.PageType;
import model.ChuyenNganh;
import model.CoSo;
import model.DotDangKy;
import model.NhanVien;
import model.SinhVien;
import repositories.ChuyenNganhGvRepositories;
import repositories.ChuyenNganhRepositories;
import repositories.CoSoRepositories;
import repositories.DotDangKyRepositories;
import repositories.GvHuongDanRepositories;
import repositories.NhomRepositories;
import repositories.SinhVienRepositories;

public class PDTService {
	private int COSO_ID;
	private int DOT_DKY_ID;
	private final int TRANGTHAI_SV_CHUA_DK = 0;
	private final int TRANGTHAI_SV_DA_DK = 1;
	private SimpleDateFormat sdf;
	private NhomRepositories nhom_rep;
	private SinhVienRepositories sv_rep;
	private DotDangKyRepositories dotDK_rep;
	private ManagerSVService quanLySV;
	private GvHuongDanRepositories gv_rep;
	private ExcelService ex_service;
	private ChuyenNganhRepositories chuyenNganhRep;
	private CoSoRepositories coso_rep;
	private ChuyenNganhGvRepositories cnGvRep;
	private List<Integer> CHUYEN_NGANH_ID;

	public PDTService() {
		this.nhom_rep = new NhomRepositories();
		this.sv_rep = new SinhVienRepositories();
		this.dotDK_rep = new DotDangKyRepositories();
		this.quanLySV = new ManagerSVService();
		this.chuyenNganhRep = new ChuyenNganhRepositories();
		this.gv_rep = new GvHuongDanRepositories();
		this.ex_service = new ExcelService();
		this.coso_rep = new CoSoRepositories();
		this.sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.CHUYEN_NGANH_ID = this.chuyenNganhRep.getCNByBoMon();
		this.cnGvRep = new ChuyenNganhGvRepositories();

	}

	public void prepareAndForward(HttpServletRequest request, HttpServletResponse response, PageType pageType)
			throws ServletException, IOException {
		NhanVien nv = (NhanVien) request.getSession().getAttribute("staff");
		COSO_ID = nv.getCoSoId();
		request.setAttribute("role", nv.getRole());
		// DOT_DKY_ID = this.gv_rep.getDotDky(nv.getId());
		DOT_DKY_ID = this.dotDK_rep.getDot(COSO_ID);
		String uri = request.getRequestURI();
		
		

		if (uri.contains("dashboard")) {
			displayDashboard(request, response);
		} else if (uri.contains("quan-ly-sinh-vien")) {
			pageType = PageType.PDT_MANAGERSV_PAGE;
			displayManager(request, response);
		} else if (uri.contains("quan-ly-giang-vien")) {
			pageType = PageType.PDT_MANAGERGV_PAGE;
			displayManagerGV(request, response);
		} else if (uri.contains("danh-sach-sinh-vien-chua-dang-ky")) {
			displaySVChuaDK(request, response);
			pageType = PageType.PDT_SVCHUADANGKY_PAGE;
		} else if (uri.contains("danh-sach-sinh-vien-da-dang-ky")) {
			pageType = PageType.PDT_SVDADANGKY_PAGE;
			displaySVDaDK(request, response);
		} else if (uri.contains("danh-sach-cac-nhom-da-tao")) {
			pageType = PageType.PDT_DSACHNHOM_PAGE;
			displayNhom(request, response);
		} else if (uri.contains("chi-tiet-nhom")) {
			displayChiTietNhom(request, response);
			pageType = PageType.PDT_CHITIETNHOM_PAGE;
		} else if (uri.contains("logout")) {
			request.getSession().setAttribute("staff", null);
			response.sendRedirect(request.getContextPath() + "/staff/PDT");
			return;
		} else if (uri.contains("dot-dang-ky")) {
			displayDotDangKy(request, response);
			pageType = PageType.PDT_DOTDK_PAGE;
		}

		File dir = new File(request.getServletContext().getRealPath("/files"));
		if (!dir.exists()) {
			dir.mkdir();
		}
		String path = dir + "/tham-khao.xlsx";
		ex_service.exportThamKhao(path);
		String path2 = dir + "/tham-khao-gv.xlsx";
		ex_service.exportThamKhaoGV(path2, 0);

		PageInfo page = PageInfo.pageRoute.get(pageType);
		request.setAttribute("page", page);
		request.setAttribute("path", request.getContextPath());

		request.getRequestDispatcher("/views/staff/index.jsp").forward(request, response);
	}

	private void displayManagerGV(HttpServletRequest request, HttpServletResponse response) {
		
		String page = request.getParameter("page_size");
		System.out.println("fsdifdsj    "+page);
		
		String key = request.getParameter("keyword");
		if (key != null) {
			key = key.trim();
		} else {
			key = "";
		}
		key = utils.HelperUtils.changeString(key);
		List<Object[]> lstGVHD = this.cnGvRep.getListGVHD(COSO_ID, DOT_DKY_ID, this.CHUYEN_NGANH_ID.get(0), key);
		for (int x = 1; x < this.CHUYEN_NGANH_ID.size(); x++) {
			List<Object[]> lst = this.cnGvRep.getListGVHD(COSO_ID, DOT_DKY_ID, this.CHUYEN_NGANH_ID.get(x), key);
			lstGVHD.addAll(lst);
		}

		List<Object[]> lstCN = this.chuyenNganhRep.getAll();

		request.setAttribute("chuyenNganh", lstCN);
		request.setAttribute("giangVien", lstGVHD);
		request.setAttribute("tongGv", lstGVHD.size());
		request.setAttribute("dotdky", DOT_DKY_ID);
		request.getSession().setAttribute("ScopeTK", key);
	}

	public void prepareAndDirect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {

		String uri = request.getRequestURI();
		String url = uri.substring(0, uri.lastIndexOf("/"));

		if (url.contains("delete")) {
			this.quanLySV.xoaSinhVien(request, response);
		} else if (url.contains("edit")) {
			String id = request.getParameter("idSv");
			Integer idSv = utils.HelperUtils.parseInteger(id);
			SinhVien sinhVien = this.sv_rep.findById(idSv);
			this.quanLySV.suaSinhVien(request, response, sinhVien);
		} else if (uri.contains("insert")) {
			this.quanLySV.themSinhVien(request, response);
		} else if (uri.contains("tim-kiem-sinh-vien-chua-dang-ky")) {
			timKiemSVChuaDangKy(request, response);
			return;
		} else if (uri.contains("tim-kiem-sinh-vien-da-dang-ky")) {
			timKiemSVDaDangKy(request, response);
			return;
		} else if (uri.contains("danh-sach-cac-nhom-da-tao/tim-kiem")) {
			timKiemNhom(request, response);
			return;
		} else if (uri.contains("quan-ly-sinh-vien/tim-kiem")) {
			timKiemDS_SV(request, response);
			return;
		}else if(uri.contains("dot-dang-ky/tao")) {
			taoDotDangKy(request,response);
			return;
		}else if(uri.contains("dot-dang-ky/update")) {
			updateDotDangKy(request,response);
			return;
		}else if (uri.contains("quan-ly-giang-vien/tim-kiem")) {
			timKiemGV(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath() + "/staff/PDT/quan-ly-sinh-vien");
	}

	private void updateDotDangKy(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
		// TODO Auto-generated method stub
		Integer dot_id = Integer.parseInt(request.getParameter("dotdk"));
		DotDangKy dk = dotDK_rep.findById(dot_id);		
		String ten = request.getParameter("ten_dot_dk");
		Date ngay_bat_dau = sdf.parse(request.getParameter("thoi_gian_bat_dau"));
		Date ngay_ket_thuc = sdf.parse(request.getParameter("thoi_gian_ket_thuc"));
		dk.setHocKy(ten);
		dk.setThoiGianBatDau(ngay_bat_dau);
		dk.setThoiGianKetThuc(ngay_ket_thuc);
		dotDK_rep.update(dk);
		response.sendRedirect(request.getContextPath() + "/staff/PDT/dot-dang-ky");
	}

	private void timKiemGV(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String key = request.getParameter("keyword");
			if (key != null) {
				key = key.trim();
			} else {
				key = "";
			}

			key = utils.HelperUtils.changeString(key);
			String cnid = request.getParameter("chuyen_nganh_id");
			int chuyenNganh = utils.HelperUtils.parseInteger(cnid);
			List<Object[]> lstGVHD = null;
			if (chuyenNganh == 0) {
				lstGVHD = this.cnGvRep.getListGVHD(COSO_ID, DOT_DKY_ID, this.CHUYEN_NGANH_ID.get(0), key);
				for (int x = 1; x < this.CHUYEN_NGANH_ID.size(); x++) {
					List<Object[]> lst = this.cnGvRep.getListGVHD(COSO_ID, DOT_DKY_ID, this.CHUYEN_NGANH_ID.get(x),
							key);
					lstGVHD.addAll(lst);
				}
			} else {
				lstGVHD = this.cnGvRep.getListGVHD(COSO_ID, DOT_DKY_ID, chuyenNganh, key);
			}

			request.setAttribute("giangVien", lstGVHD);
			request.setAttribute("tongGv", lstGVHD.size());
			request.getSession().setAttribute("ScopeCN_TKGV", chuyenNganh);
			request.getSession().setAttribute("ScopeTK", key);
			List<Object[]> lstCN = this.chuyenNganhRep.getCNByBMon();

			request.setAttribute("chuyenNganh", lstCN);
			ChuyenTrang(request, response, PageType.CNBM_MANAGERGV_PAGE);
		} catch (Exception e) {
			request.getRequestDispatcher("/views/404.jsp").forward(request, response);
		}

	}

	private void taoDotDangKy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		DotDangKy dk = new DotDangKy();

		dk.setCoSoId(COSO_ID);
		String ten = request.getParameter("ten_dot_dk");
		Date ngay_bat_dau = sdf.parse(request.getParameter("thoi_gian_bat_dau"));
		Date ngay_ket_thuc = sdf.parse(request.getParameter("thoi_gian_ket_thuc"));
		dk.setHocKy(ten);
		dk.setThoiGianBatDau(ngay_bat_dau);
		dk.setThoiGianKetThuc(ngay_ket_thuc);
		dotDK_rep.insert(dk);
		response.sendRedirect(request.getContextPath() + "/staff/PDT/dot-dang-ky");
	}

	private void displayChiTietNhom(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("nhom_id");

		Integer dotDktInt = this.dotDK_rep.getDot(COSO_ID);
		Integer nhomId = utils.HelperUtils.parseInteger(id);
		List<Object[]> lst = this.nhom_rep.getListNhomChiTiet(COSO_ID, dotDktInt, nhomId);
		request.setAttribute("dSachNhom", lst);
		List<SinhVien> lstTVNhom = sv_rep.selectSVByNhomId(nhomId);
		request.setAttribute("Nhoms", lstTVNhom);
	}

	private void displayDashboard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("dotdk", dotDK_rep.getDotDangKyByCoSo(COSO_ID));

		String uri = request.getRequestURI();
		if (uri.contains("dashboard")) {
			String doDK_id = request.getParameter("dot-dang-ky-id");
			int slSVChuaDK = 0;
			int slSVDaDK = 0;
			int slNhom = 0;
			int dotdkID = 0;
			int gvHD = 0;
			if (doDK_id != null) {
				dotdkID = Integer.parseInt(doDK_id);

				slSVChuaDK = sv_rep.getListSV_ChuaDangKy(dotdkID, COSO_ID).size();
				slSVDaDK = sv_rep.getListSV_DaDangKy(dotdkID, COSO_ID).size();
				slNhom = nhom_rep.getListNhomByCoSo(dotdkID, COSO_ID).size();
				gvHD = gv_rep.getGVHDbyCOSO_DotDK(dotdkID, COSO_ID).size();
				request.getSession().setAttribute("ScopeDotDKDashboard", dotdkID);
			}
			request.getSession().setAttribute("dashboard", dotdkID);

			request.setAttribute("SinhVienChuaDangKy", slSVChuaDK);
			request.setAttribute("SinhVienDaDangKy", slSVDaDK);
			request.setAttribute("SoNhomDaTao", slNhom);
			request.setAttribute("SoGVcoTheDK", gvHD);

		}
	}

	private void displayNhom(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// setScope
		request.getSession().setAttribute("ScopeGVHD_nhom", 0);
		request.getSession().setAttribute("ScopeCN_nhom", 0);
		request.getSession().setAttribute("ScopeDotDK_nhom", 0);
		request.getSession().setAttribute("ScopeSLTV_nhom", 0);

		setCombobox(request, "chuyenNganh", "dotdk");

		int dotDky = this.dotDK_rep.getDot(COSO_ID);

		List<Object[]> lst = this.nhom_rep.getListNhom(COSO_ID, dotDky);
		request.setAttribute("dSachNhom", lst);
		request.setAttribute("tongNhom", lst.size());

		request.getSession().setAttribute("dotDkyNhomCT", dotDky);

		// setGV hướng dẫn
		List<Object[]> lstGVHD = gv_rep.getGvByCoSo_DotDK_ChuyenNganh(dotDky, COSO_ID);
		request.setAttribute("GVHuongDan", lstGVHD);

		String cn = request.getParameter("chuyen-nganh");
		if (cn != null) {
			int cnInt = utils.HelperUtils.parseInteger(cn);
			request.getSession().setAttribute("ScopeCN_nhom", cnInt);
			List<Object[]> lstGVHD1 = gv_rep.getGvByCoSo_DotDK_ChuyenNganh(dotDky, COSO_ID, cnInt);
			request.setAttribute("GVHuongDan", lstGVHD1);
		}
	}

	private void displayManager(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("sinhVien", this.sv_rep.getListSV(COSO_ID));
		request.setAttribute("tongSV", this.sv_rep.getListSV(COSO_ID).size());
		request.setAttribute("khoa", this.sv_rep.getKhoaSV());
		request.setAttribute("tenChuyenNganh", this.chuyenNganhRep.findAll());
		
		long countSV = this.sv_rep.countSV();
		
		// set Scope
		request.getSession().setAttribute("ScopeCN_DS", 0);
		request.getSession().setAttribute("ScopeDotDK_DS", 0);

		setCombobox(request, "chuyenNganh", "dotdk");
		request.getSession().setAttribute("ScopeTK", "");

	}

	private void displaySVChuaDK(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Object[]> lst = this.sv_rep.getListSVTT(COSO_ID, TRANGTHAI_SV_CHUA_DK);
		request.setAttribute("sinhVienChuaDK", lst);
		request.setAttribute("tongSVChuaDK", lst.size());

		// setScope
		request.getSession().setAttribute("ScopeCN_chuaDK", 0);
		request.getSession().setAttribute("ScopeKhoa_chuaDK", 0);
		request.getSession().setAttribute("ScopeDotDK_chuaDK", 0);

		setCombobox(request, "chuyenNganh", "dotdk", "khoa");
	}

	private void displaySVDaDK(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Object[]> lst = this.sv_rep.getListSVTT(COSO_ID, TRANGTHAI_SV_DA_DK);
		request.setAttribute("sinhVienDaDK", lst);
		request.setAttribute("tongSVDaDK", lst.size());

		// setScope
		request.getSession().setAttribute("ScopeCN_DaDK", 0);
		request.getSession().setAttribute("ScopeKhoa_DaDK", 0);
		request.getSession().setAttribute("ScopeDotDK_DaDK", 0);

		setCombobox(request, "chuyenNganh", "dotdk", "khoa");

	}

	private void timKiemSVChuaDangKy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String chuyenNganhId = request.getParameter("chuyen_nganh_id");
			String dotDKId = request.getParameter("dot_id");
			String khoa = request.getParameter("khoa");

			Integer dotDKIdInt = Integer.parseInt(dotDKId);
			Integer chuyenNganhIdInt = Integer.parseInt(chuyenNganhId);
			List<Object[]> lstSV = sv_rep.getListSV_TimKiem(dotDKIdInt, COSO_ID, chuyenNganhIdInt, khoa,
					TRANGTHAI_SV_CHUA_DK);
			request.setAttribute("sinhVienChuaDK", lstSV);
			request.setAttribute("tongSVChuaDK", lstSV.size());

			// setScope
			request.getSession().setAttribute("ScopeCN_chuaDK", chuyenNganhId);
			request.getSession().setAttribute("ScopeKhoa_chuaDK", khoa);
			request.getSession().setAttribute("ScopeDotDK_chuaDK", dotDKId);

			setCombobox(request, "chuyenNganh", "dotdk", "khoa");
			ChuyenTrang(request, response, PageType.PDT_SVCHUADANGKY_PAGE);
		} catch (Exception e) {
			request.getRequestDispatcher("/views/404.jsp").forward(request, response);
		}
	}

	private void timKiemSVDaDangKy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String chuyenNganhId = request.getParameter("chuyen_nganh_id");
			String dotDKId = request.getParameter("dot_id");
			String khoa = request.getParameter("khoa");

			Integer dotDKIdInt = Integer.parseInt(dotDKId);
			Integer chuyenNganhIdInt = Integer.parseInt(chuyenNganhId);
			List<Object[]> lstSV = sv_rep.getListSV_TimKiem(dotDKIdInt, COSO_ID, chuyenNganhIdInt, khoa,
					TRANGTHAI_SV_DA_DK);

			request.setAttribute("sinhVienDaDK", lstSV);
			request.setAttribute("tongSVDaDK", lstSV.size());

			// setScope

			request.getSession().setAttribute("ScopeCN_DaDK", chuyenNganhId);
			request.getSession().setAttribute("ScopeKhoa_DaDK", khoa);
			request.getSession().setAttribute("ScopeDotDK_DaDK", dotDKId);

			setCombobox(request, "chuyenNganh", "dotdk", "khoa");
			ChuyenTrang(request, response, PageType.PDT_SVDADANGKY_PAGE);
		} catch (Exception e) {
			request.getRequestDispatcher("/views/404.jsp").forward(request, response);
		}
	}

	private void timKiemNhom(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String chuyenNganhId = request.getParameter("chuyen_nganh_id");
			String dotDKId = request.getParameter("dot_id");

			String gvId = request.getParameter("giang_vien_id");
			String slThanhVien = request.getParameter("so_thanh_vien");

			int dotDKIdInt = Integer.parseInt(dotDKId);
			int chuyenNganhIdInt = Integer.parseInt(chuyenNganhId);
			int gvHDId = Integer.parseInt(gvId);
			int slTv = Integer.parseInt(slThanhVien);

			List<Object[]> lst = this.nhom_rep.getListNhomTimKiem(COSO_ID, chuyenNganhIdInt, dotDKIdInt, gvHDId, slTv);

			request.setAttribute("dSachNhom", lst);
			request.setAttribute("tongNhom", lst.size());
			int dotDky = this.dotDK_rep.getDot(COSO_ID);
			// setScope
			request.getSession().setAttribute("ScopeGVHD_nhom", gvHDId);
			request.getSession().setAttribute("ScopeCN_nhom", chuyenNganhIdInt);
			request.getSession().setAttribute("ScopeDotDK_nhom", dotDKIdInt);
			request.getSession().setAttribute("ScopeSLTV_nhom", slTv);

			setCombobox(request, "chuyenNganh", "dotdk");

			// setGV hướng dẫn
			List<Object[]> lstGVHD = gv_rep.getGvByCoSo_DotDK_ChuyenNganh(dotDky, COSO_ID);
			request.setAttribute("GVHuongDan", lstGVHD);
			ChuyenTrang(request, response, PageType.PDT_DSACHNHOM_PAGE);
		} catch (Exception e) {
			request.getRequestDispatcher("/views/404.jsp").forward(request, response);
		}
	}

	private void timKiemDS_SV(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String chuyenNganhId = request.getParameter("chuyen_nganh_id");
			String dotDKId = request.getParameter("dot_id");
			String keyword = request.getParameter("keyword");
			int chuyenNganhIdInt = Integer.parseInt(chuyenNganhId);
			int dotDKIdInt = Integer.parseInt(dotDKId);

			request.getSession().setAttribute("ScopeDotDK", dotDKIdInt);
			if (keyword != null) {
				keyword = keyword.trim();
			} else {
				keyword = "";
			}
			keyword = utils.HelperUtils.changeString(keyword);
			List<Object[]> lstDSSV = this.sv_rep.getListSV_TimKiem(COSO_ID, dotDKIdInt, chuyenNganhIdInt, keyword);

			request.setAttribute("sinhVien", lstDSSV);
			request.setAttribute("tongSV", lstDSSV.size());
			request.setAttribute("khoa", this.sv_rep.getKhoaSV());
			request.setAttribute("tenChuyenNganh", this.chuyenNganhRep.findAll());
			setCombobox(request, "tenChuyenNganh", "dot_id");

			// set Scope
			request.getSession().setAttribute("ScopeCN_DS", chuyenNganhIdInt);
			request.getSession().setAttribute("ScopeDotDK_DS", dotDKIdInt);

			setCombobox(request, "chuyenNganh", "dotdk");
			request.getSession().setAttribute("ScopeCN", chuyenNganhIdInt);
			request.getSession().setAttribute("ScopeTK", keyword);
			ChuyenTrang(request, response, PageType.PDT_MANAGERSV_PAGE);
		} catch (Exception e) {
			request.getRequestDispatcher("/views/404.jsp").forward(request, response);
		}
	}

	private void ChuyenTrang(HttpServletRequest request, HttpServletResponse response, PageType pageType)
			throws ServletException, IOException {
		PageInfo page = PageInfo.pageRoute.get(pageType);
		request.setAttribute("page", page);
		request.setAttribute("path", request.getContextPath());
		request.getRequestDispatcher("/views/staff/index.jsp").forward(request, response);
	}

	// chuyên ngành, đợt đăng ký
	private void setCombobox(HttpServletRequest request, String chuyenNganh, String dotDK) {
		// lấy ra tất cả chuyên ngành
		List<ChuyenNganh> lstCN = chuyenNganhRep.findAll();
		request.setAttribute(chuyenNganh, lstCN);

		// set dot dk theo cơ sở
		List<Object[]> lstDotDK = dotDK_rep.getDotDK(COSO_ID);
		request.setAttribute(dotDK, lstDotDK);

	}

	// chuyên ngành, đợt đăng ký, khóa
	private void setCombobox(HttpServletRequest request, String chuyenNganh, String coSo, String khoa) {
		List<ChuyenNganh> lstCN = chuyenNganhRep.findAll();
		request.setAttribute(chuyenNganh, lstCN);

		List<Object[]> lstDotDK = dotDK_rep.getDotDK(COSO_ID);
		request.setAttribute(coSo, lstDotDK);

		List<String> lstKhoa = sv_rep.getKhoaSV();
		request.setAttribute(khoa, lstKhoa);
	}

	private void displayDotDangKy(HttpServletRequest request, HttpServletResponse response) {
		long tong_dot_dk = dotDK_rep.getSoLuongDotDK(COSO_ID);
		CoSo coso = coso_rep.findById(COSO_ID);

		request.setAttribute("co_so", coso.getTen());
		request.setAttribute("tong_dot_dk", tong_dot_dk);
		request.setAttribute("dsachdot", dotDK_rep.getListDotDK(COSO_ID));
	}
}
