package services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.PageInfo;
import common.PageType;
import lombok.extern.log4j.Log4j2;
import model.ChuyenNganh;
import model.NhanVien;
import model.SinhVien;
import utils.HelperUtils;
import repositories.ChuyenNganhGvRepositories;
import repositories.ChuyenNganhRepositories;
import repositories.DotDangKyRepositories;
import repositories.GvHuongDanRepositories;
import repositories.NhanVienRepositories;
import repositories.NhomRepositories;
import repositories.SinhVienRepositories;

@Log4j2
public class CNBMService {
	private int COSO_ID;
	private int DOT_DKY_ID;
	private final int TRANGTHAI_SV_CHUA_DK = 0;
	private final int TRANGTHAI_SV_DA_DK = 1;
	private int BO_MON_ID;
	private List<Integer> CHUYEN_NGANH_ID;

	private NhanVien nv;

	private NhomRepositories nhom_rep;
	private SinhVienRepositories sv_rep;
	private DotDangKyRepositories dotDK_rep;
	private ManagerSVService quanLySV;
	private GvHuongDanRepositories gv_rep;
	private ExcelService ex_service;
	private ChuyenNganhRepositories chuyenNganhRep;
	private NhanVienRepositories nvRep;
	private ChuyenNganhGvRepositories cnGvRep;
	private ManagerGVService quanLyGV;
	private static final Logger LOGGER = LoggerFactory.getLogger(CNBMService.class);
	public CNBMService() {
		this.nhom_rep = new NhomRepositories();
		this.sv_rep = new SinhVienRepositories();
		this.dotDK_rep = new DotDangKyRepositories();
		this.quanLySV = new ManagerSVService();
		this.chuyenNganhRep = new ChuyenNganhRepositories();
		this.gv_rep = new GvHuongDanRepositories();
		this.ex_service = new ExcelService();
		this.nvRep = new NhanVienRepositories();
		this.cnGvRep = new ChuyenNganhGvRepositories();
		this.quanLyGV = new ManagerGVService();

	}

	public void prepareAndForward(HttpServletRequest request, HttpServletResponse response, PageType pageType)
			throws ServletException, IOException {

		this.nv = (NhanVien) request.getSession().getAttribute("staff");
		COSO_ID = nv.getCoSoId();
		BO_MON_ID = nv.getBoMonId();

		// lấy ra tất cả id của chuyên ngành thuộc bộ môn của CNBM
		this.CHUYEN_NGANH_ID = this.chuyenNganhRep.getCNByBoMon(BO_MON_ID);

		DOT_DKY_ID = this.dotDK_rep.getDot(COSO_ID);

		String uri = request.getRequestURI();

		// set liên hệ PDT
		List<Object[]> obj = this.nvRep.getNhanVienPDT(nv.getCoSoId());
		if (obj != null) {
			request.setAttribute("email", obj.get(0)[0]);
			request.setAttribute("ten", obj.get(0)[1]);
		}
		if (uri.contains("dashboard")) {
			displayDashboard(request, response);

		} else if (uri.contains("quan-ly-giang-vien")) {
			pageType = PageType.CNBM_MANAGERGV_PAGE;
			displayManager(request, response);
		} else if (uri.contains("danh-sach-sinh-vien-chua-dang-ky")) {
			displaySVChuaDK(request, response);
			pageType = PageType.CNBM_SVCHUADANGKY_PAGE;
		} else if (uri.contains("danh-sach-sinh-vien-da-dang-ky")) {
			pageType = PageType.CNBM_SVDADANGKY_PAGE;
			displaySVDaDK(request, response);
		} else if (uri.contains("danh-sach-cac-nhom-da-tao")) {

			pageType = PageType.CNBM_DSACHNHOM_PAGE;
			displayNhom(request, response);
		} else if (uri.contains("chia-nhom")) {
			displaySVDaDKChuaCoNhom(request, response);
			pageType = PageType.CNBM_CHIANHOM_PAGE;
		} else if (uri.contains("logout")) {
			request.getSession().setAttribute("staff", null);
			response.sendRedirect(request.getContextPath() + "/staff/CNBM");
			return;
		} else if (uri.contains("chi-tiet-gv")) {
			chiTietGVHD(request, response);
			pageType = PageType.chitietGV;
		} else if (uri.contains("chi-tiet-nhom/deleteSV")) {
			String idSV = request.getParameter("idSV");
			int idN = 0;
			if (idSV != null) {
				int idSVInt = utils.HelperUtils.parseInteger(idSV);
				SinhVien sv = this.sv_rep.findById(idSVInt);
				idN = sv.getNhomId();
				sv.setNhomId(null);
				sv.setThoiGianDkNhom(null);
				sv_rep.update(sv);
			}
			String url = request.getContextPath() + "/staff/CNBM/chi-tiet-nhom?nhom_id="+idN;
			response.sendRedirect(url);
			return;
		} else if (uri.contains("chi-tiet-nhom")) {
			displayChiTietNhom(request, response);
			pageType = PageType.CNBM_CHITIETNHOM_PAGE;
		}

		File dir = new File(request.getServletContext().getRealPath("/files"));
		if (!dir.exists()) {
			dir.mkdir();
		}
		String path = dir + "/tham-khao.xlsx";
		ex_service.exportThamKhaoGV(path, BO_MON_ID);

		PageInfo page = PageInfo.pageRoute.get(pageType);
		request.setAttribute("page", page);
		request.setAttribute("path", request.getContextPath());

		request.getRequestDispatcher("/views/staff/CNBM/index.jsp").forward(request, response);
	}

	public void prepareAndDirect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String url = uri.substring(0, uri.lastIndexOf("/"));

		List<Object[]> obj = this.nvRep.getNhanVienPDT(this.nv.getCoSoId());
		if (obj != null) {
			request.setAttribute("email", obj.get(0)[0]);
			request.setAttribute("ten", obj.get(0)[1]);
		}

		if (url.contains("delete")) {
			this.quanLyGV.xoaGV(response, request);
		} else if (url.contains("edit")) {
			String id = request.getParameter("idGv");
			Integer idGv = utils.HelperUtils.parseInteger(id);
			NhanVien nv = this.nvRep.getById(idGv);

			this.quanLyGV.suaGV(request, response, nv, BO_MON_ID, DOT_DKY_ID);

		} else if (uri.contains("quan-ly-giang-vien/tim-kiem")) {
			timKiemGV(request, response);
			return;
		} else if (uri.contains("danh-sach-sinh-vien-chua-dang-ky/tim-kiem-sinh-vien-chua-dang-ky")) {
			timKiemSVChuaDangKy(request, response);
			return;
		} else if (uri.contains("danh-sach-cac-nhom-da-tao/tim-kiem")) {
			timKiemNhom(request, response);
			return;
		} else if (uri.contains("danh-sach-sinh-vien-da-dang-ky/tim-kiem-sinh-vien-da-dang-ky")) {
			timKiemSVDaDangKy(request, response);
			return;
		}

		response.sendRedirect(request.getContextPath() + "/staff/CNBM/quan-ly-giang-vien");
	}

	private void displayDashboard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("dotdk", dotDK_rep.getDotDangKyByCoSo(COSO_ID));
		NhanVien nv = (NhanVien) request.getSession().getAttribute("staff");

		String uri = request.getRequestURI();
		if (uri.contains("dashboard")) {
			String doDK_id = request.getParameter("dot-dang-ky-id");
			int slSVChuaDK = 0;
			int slSVDaDK = 0;
			int gvHD = 0;
			long slNhom = 0;

			int dotdkID = 0;
			if (doDK_id != null) {
				dotdkID = utils.HelperUtils.parseInteger(doDK_id);

				for (Integer x : this.CHUYEN_NGANH_ID) {
					slSVChuaDK += sv_rep.getListSV_ChuaDangKy(dotdkID, COSO_ID, x).size();
					slSVDaDK += sv_rep.getListSV_DaDangKy(dotdkID, COSO_ID, x).size();

					slNhom += nhom_rep.getSLNhomByCN(dotdkID, COSO_ID, x);
					gvHD += gv_rep.getGVHDbyCOSO_DotDK_CN(dotdkID, COSO_ID, x).size();
				}

				request.getSession().setAttribute("ScopeDotDKCNDashboard", dotdkID);
			}
			request.getSession().setAttribute("dashboard", dotdkID);

			request.setAttribute("SinhVienChuaDangKy", slSVChuaDK);
			request.setAttribute("SinhVienDaDangKy", slSVDaDK);
			request.setAttribute("SoNhomDaTao", slNhom);
			request.setAttribute("SoGVcoTheDK", gvHD);

			long check = this.gv_rep.checkGV(nv.getId());
			if (check == 0) {
				request.setAttribute("check", 0);
			} else {

				request.setAttribute("check", 1);
				List<Object[]> lstGVDDK = nvRep.findGVHD_DotDK_byNVid(nv.getId());
				if (lstGVDDK.size() > 0) {
					int GVHD_ID = (int) lstGVDDK.get(0)[0];
					int DOTDK_ID = (int) lstGVDDK.get(0)[1];
					List<Object[]> lst = nhom_rep.getListNhom(nv.getCoSoId(), DOTDK_ID, GVHD_ID);
					request.setAttribute("Nhoms", lst);
				}
			}

		}
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

	private void displaySVChuaDK(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Object[]> lstSV = this.sv_rep.getListSVTT(COSO_ID,DOT_DKY_ID, TRANGTHAI_SV_CHUA_DK, this.CHUYEN_NGANH_ID.get(0));
		for (Integer x = 1; x < this.CHUYEN_NGANH_ID.size(); x++) {
			List<Object[]> lst = this.sv_rep.getListSVTT(COSO_ID,DOT_DKY_ID, TRANGTHAI_SV_CHUA_DK, this.CHUYEN_NGANH_ID.get(x));
			lstSV.addAll(lst);
		}
		request.setAttribute("sinhVienChuaDK", lstSV);
		request.setAttribute("tongSVChuaDK", lstSV.size());


		// setScope

		request.getSession().setAttribute("ScopeCN_chuaDK", 0);
		request.getSession().setAttribute("ScopeKhoa_chuaDK", 0);
		request.getSession().setAttribute("ScopeDotDK_chuaDK", 0);
		

		setCombobox(request,"chuyenNganh", "dotdk", "khoa");
	}

	private void displaySVDaDK(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Object[]> lstSV = this.sv_rep.getListSVTT(COSO_ID,DOT_DKY_ID, TRANGTHAI_SV_DA_DK, this.CHUYEN_NGANH_ID.get(0));
		for (Integer x = 1; x < this.CHUYEN_NGANH_ID.size(); x++) {
			List<Object[]> lst = this.sv_rep.getListSVTT(COSO_ID,DOT_DKY_ID, TRANGTHAI_SV_DA_DK, this.CHUYEN_NGANH_ID.get(x));
			lstSV.addAll(lst);
		}
		request.setAttribute("sinhVienDaDK", lstSV);
		request.setAttribute("tongSVDaDK", lstSV.size());


		// setScope

		request.getSession().setAttribute("ScopeCN_DaDK", 0);

		request.getSession().setAttribute("ScopeKhoa_DaDK", 0);
		request.getSession().setAttribute("ScopeDotDK_DaDK", 0);

		setCombobox(request,"chuyenNganh","dotdk", "khoa");

	}

	private void displaySVDaDKChuaCoNhom(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Object[]> lstSV = this.sv_rep.getListSVDKChuaNhom(COSO_ID,DOT_DKY_ID, TRANGTHAI_SV_DA_DK,
				this.CHUYEN_NGANH_ID.get(0));
		for (Integer x = 1; x < this.CHUYEN_NGANH_ID.size(); x++) {
			List<Object[]> lst = this.sv_rep.getListSVDKChuaNhom(COSO_ID, DOT_DKY_ID,TRANGTHAI_SV_DA_DK,
					this.CHUYEN_NGANH_ID.get(x));
			lstSV.addAll(lst);
		}

		request.setAttribute("sinhVienDaDKChuaNhom", lstSV);
		request.setAttribute("tongSVDaDKChuaNhom", lstSV.size());

		// setScope

		request.getSession().setAttribute("ScopeCN_DaDK", 0);

		request.getSession().setAttribute("ScopeKhoa_DaDK", 0);
		request.getSession().setAttribute("ScopeDotDK_DaDK", 0);

		setCombobox(request,"chuyenNganh", "dotdk", "khoa");

	}

	// chuyên ngành, đợt đăng ký
	private void setCombobox(HttpServletRequest request,String cn, String dotDK) {

		List<Object[]> lstCN = this.chuyenNganhRep.getCNByBMon(BO_MON_ID);
		request.setAttribute(cn, lstCN);
		
		// set dot dk theo cơ sở
		List<Object[]> lstDotDK = dotDK_rep.getDotDK(COSO_ID);
		request.setAttribute(dotDK, lstDotDK);

	}

	// đợt đăng ký, khóa
	private void setCombobox(HttpServletRequest request,String cn, String coSo, String khoa) {

		List<Object[]> lstCN = this.chuyenNganhRep.getCNByBMon(BO_MON_ID);
		request.setAttribute(cn, lstCN);
		
		List<Object[]> lstDotDK = dotDK_rep.getDotDK(COSO_ID);
		request.setAttribute(coSo, lstDotDK);

		List<String> lstKhoa = sv_rep.getKhoaSV();
		request.setAttribute(khoa, lstKhoa);
	}

	private void displayNhom(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// setScope
		try {
			request.getSession().setAttribute("ScopeGVHD_nhom", 0);
			request.getSession().setAttribute("ScopeCN_nhom", 0);
			request.getSession().setAttribute("ScopeDotDK_nhom", 0);
			request.getSession().setAttribute("ScopeSLTV_nhom", 0);

			setCombobox(request, "chuyenNganh","dotdk");



			List<Object[]> lstSV = this.nhom_rep.getListNhomCN(COSO_ID, DOT_DKY_ID, this.CHUYEN_NGANH_ID.get(0));
			for (Integer x = 1; x < this.CHUYEN_NGANH_ID.size(); x++) {
				List<Object[]> lst = this.nhom_rep.getListNhomCN(COSO_ID, DOT_DKY_ID, this.CHUYEN_NGANH_ID.get(x));
				lstSV.addAll(lst);
			}

			request.setAttribute("dSachNhom", lstSV);
			request.setAttribute("tongNhom", lstSV.size());

			request.getSession().setAttribute("dotDkyNhomCT", DOT_DKY_ID);

			// setGV hướng dẫn
			List<Object[]> lstGVHD = gv_rep.getGvHDByCoSo_DotDK_ChuyenNganh(DOT_DKY_ID, COSO_ID,
					this.CHUYEN_NGANH_ID.get(0));
			for (Integer x = 1; x < this.CHUYEN_NGANH_ID.size(); x++) {
				List<Object[]> lst = gv_rep.getGvHDByCoSo_DotDK_ChuyenNganh(DOT_DKY_ID, COSO_ID,
						this.CHUYEN_NGANH_ID.get(x));
				lstGVHD.addAll(lst);
			}

			request.setAttribute("GVHuongDan", lstGVHD);

			
		} catch (Exception e) {

			request.getRequestDispatcher("/views/404.jsp").forward(request, response);
		}
	}

	private void displayManager(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String key = request.getParameter("keyword");
		if (key != null) {
			key = key.trim();
		} else {
			key = "";
		}
		key = utils.HelperUtils.changeString(key);
		List<Object[]> lstGVHD = this.cnGvRep.getListGVHD(COSO_ID,DOT_DKY_ID, this.CHUYEN_NGANH_ID.get(0), key);
		for (int x = 1; x < this.CHUYEN_NGANH_ID.size(); x++) {
			List<Object[]> lst = this.cnGvRep.getListGVHD(COSO_ID,DOT_DKY_ID, this.CHUYEN_NGANH_ID.get(x), key);
			lstGVHD.addAll(lst);
		}

		List<Object[]> lstCN = this.chuyenNganhRep.getCNByBMon(BO_MON_ID);
		
		request.setAttribute("chuyenNganh", lstCN);
		request.setAttribute("giangVien", lstGVHD);
		request.setAttribute("tongGv", lstGVHD.size());
		request.setAttribute("dotdky", DOT_DKY_ID);
		request.getSession().setAttribute("ScopeTK", key);
		
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
			if (chuyenNganh ==0) {
				 lstGVHD = this.cnGvRep.getListGVHD(COSO_ID,DOT_DKY_ID,  this.CHUYEN_NGANH_ID.get(0), key);
				for (int x = 1; x < this.CHUYEN_NGANH_ID.size(); x++) {
					List<Object[]> lst = this.cnGvRep.getListGVHD(COSO_ID,DOT_DKY_ID, this.CHUYEN_NGANH_ID.get(x), key);
					lstGVHD.addAll(lst);
				}
			} else {
				lstGVHD = this.cnGvRep.getListGVHD(COSO_ID,DOT_DKY_ID, chuyenNganh, key);	
			}
			
			request.setAttribute("giangVien", lstGVHD);
			request.setAttribute("tongGv", lstGVHD.size());
			request.getSession().setAttribute("ScopeCN_TKGV", chuyenNganh);
			request.getSession().setAttribute("ScopeTK", key);
			List<Object[]> lstCN = this.chuyenNganhRep.getCNByBMon(BO_MON_ID);
			
			request.setAttribute("chuyenNganh", lstCN);
			ChuyenTrang(request, response, PageType.CNBM_MANAGERGV_PAGE);
		} catch (Exception e) {
			request.getRequestDispatcher("/views/404.jsp").forward(request, response);
		}

	}

	private void ChuyenTrang(HttpServletRequest request, HttpServletResponse response, PageType pageType)
			throws ServletException, IOException {
		PageInfo page = PageInfo.pageRoute.get(pageType);

		request.setAttribute("page", page);
		request.setAttribute("path", request.getContextPath());
		request.getRequestDispatcher("/views/staff/CNBM/index.jsp").forward(request, response);
	}

	private void timKiemSVChuaDangKy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String dotDKId = request.getParameter("dot_id");
			String khoa = request.getParameter("khoa");

			Integer dotDKIdInt = Integer.parseInt(dotDKId);
			String cnid = request.getParameter("chuyen_nganh_id");
			int chuyenNganh = utils.HelperUtils.parseInteger(cnid);
			List<Object[]> lstSV = null;
			if (chuyenNganh ==0) {
				lstSV = sv_rep.getListSV_TimKiem(dotDKIdInt, COSO_ID, this.CHUYEN_NGANH_ID.get(0), khoa,
						TRANGTHAI_SV_CHUA_DK);
				for (int x = 1; x < this.CHUYEN_NGANH_ID.size(); x++) {
					List<Object[]> lst = sv_rep.getListSV_TimKiem(dotDKIdInt, COSO_ID, this.CHUYEN_NGANH_ID.get(x), khoa,
							TRANGTHAI_SV_CHUA_DK);
					lstSV.addAll(lst);
				}
			} else {
				lstSV = sv_rep.getListSV_TimKiem(dotDKIdInt, COSO_ID, chuyenNganh, khoa,
						TRANGTHAI_SV_CHUA_DK);
			}
			 

			request.setAttribute("sinhVienChuaDK", lstSV);
			request.setAttribute("tongSVChuaDK", lstSV.size());

			// setScope
			request.getSession().setAttribute("ScopeCN_ChuaDK", chuyenNganh);
			request.getSession().setAttribute("ScopeKhoa_chuaDK", khoa);
			request.getSession().setAttribute("ScopeDotDK_chuaDK", dotDKId);

			setCombobox(request,"chuyenNganh", "dotdk", "khoa");
			ChuyenTrang(request, response, PageType.CNBM_SVCHUADANGKY_PAGE);
		} catch (Exception e) {
			request.getRequestDispatcher("/views/404.jsp").forward(request, response);
		}
	}

	private void timKiemSVDaDangKy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String dotDKId = request.getParameter("dot_id");
			String khoa = request.getParameter("khoa");

			Integer dotDKIdInt = utils.HelperUtils.parseInteger(dotDKId);
			String cnid = request.getParameter("chuyen_nganh_id");
			int chuyenNganh = utils.HelperUtils.parseInteger(cnid);
			List<Object[]> lstSV = null;
			if (chuyenNganh ==0) {
				 lstSV = sv_rep.getListSV_TimKiem(dotDKIdInt, COSO_ID, this.CHUYEN_NGANH_ID.get(0), khoa,
						TRANGTHAI_SV_DA_DK);
				for (int x = 1; x < this.CHUYEN_NGANH_ID.size(); x++) {
					List<Object[]> lst = sv_rep.getListSV_TimKiem(dotDKIdInt, COSO_ID, this.CHUYEN_NGANH_ID.get(x), khoa,
							TRANGTHAI_SV_DA_DK);
					lstSV.addAll(lst);
				}
			} else {
				lstSV = sv_rep.getListSV_TimKiem(dotDKIdInt, COSO_ID,chuyenNganh, khoa,
						TRANGTHAI_SV_DA_DK);
			}
			

			request.setAttribute("sinhVienDaDK", lstSV);
			request.setAttribute("tongSVDaDK", lstSV.size());

			// setScope
			request.getSession().setAttribute("ScopeCN_DaDK", chuyenNganh);
			request.getSession().setAttribute("ScopeKhoa_DaDK", khoa);
			request.getSession().setAttribute("ScopeDotDK_DaDK", dotDKId);

			setCombobox(request,"chuyenNganh", "dotdk", "khoa");
			ChuyenTrang(request, response, PageType.CNBM_SVDADANGKY_PAGE);
		} catch (Exception e) {
			request.getRequestDispatcher("/views/404.jsp").forward(request, response);
		}
	}

	private void timKiemNhom(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String dotDKId = request.getParameter("dot_id");

			String gvId = request.getParameter("giang_vien_id");
			String slThanhVien = request.getParameter("so_thanh_vien");

			int dotDKIdInt = Integer.parseInt(dotDKId);
			int gvHDId = Integer.parseInt(gvId);
			int slTv = Integer.parseInt(slThanhVien);
			String cnid = request.getParameter("chuyen_nganh_id");
			int chuyenNganh = utils.HelperUtils.parseInteger(cnid);
			List<Object[]> lstNhom = null;
			if (chuyenNganh ==0 ) {
				lstNhom = this.nhom_rep.getListNhomTimKiem(COSO_ID, this.CHUYEN_NGANH_ID.get(0), dotDKIdInt,
						gvHDId, slTv);
				for (int x = 1; x < this.CHUYEN_NGANH_ID.size(); x++) {
					List<Object[]> lst = this.nhom_rep.getListNhomTimKiem(COSO_ID, this.CHUYEN_NGANH_ID.get(x), dotDKIdInt,
							gvHDId, slTv);
					lstNhom.addAll(lst);
				}
			} else {
				lstNhom = this.nhom_rep.getListNhomTimKiem(COSO_ID, chuyenNganh, dotDKIdInt,
						gvHDId, slTv);
			}
			 

			request.setAttribute("dSachNhom", lstNhom);
			request.setAttribute("tongNhom", lstNhom.size());

			// setScope
			request.getSession().setAttribute("ScopeGVHD_nhom", gvHDId);
			request.getSession().setAttribute("ScopeCN_nhom", chuyenNganh);
			request.getSession().setAttribute("ScopeDotDK_nhom", dotDKIdInt);
			request.getSession().setAttribute("ScopeSLTV_nhom", slTv);

			// setGV hướng dẫn
			int dot = this.dotDK_rep.getDot(COSO_ID);
			List<Object[]> lstGVHD = gv_rep.getGvHDByCoSo_DotDK_ChuyenNganh(dot, COSO_ID, this.CHUYEN_NGANH_ID.get(0));
			for (int x = 1; x < this.CHUYEN_NGANH_ID.size(); x++) {
				List<Object[]> lst = gv_rep.getGvHDByCoSo_DotDK_ChuyenNganh(dot, COSO_ID, this.CHUYEN_NGANH_ID.get(x));
				lstGVHD.addAll(lst);
			}

			request.setAttribute("GVHuongDan", lstGVHD);

			setCombobox(request,"chuyenNganh", "dotdk");
			
			ChuyenTrang(request, response, PageType.CNBM_DSACHNHOM_PAGE);

		} catch (Exception e) {
			e.printStackTrace();

			request.getRequestDispatcher("/views/404.jsp").forward(request, response);
		}
	}

	private void chiTietGVHD(HttpServletRequest request, HttpServletResponse response) {
		String idGV = request.getParameter("id-GV");
		String dotdk = request.getParameter("dotdk");

		if (idGV != null && dotdk != null) {
			int idGVInt = utils.HelperUtils.parseInteger(idGV);
			int dotdkInt = utils.HelperUtils.parseInteger(dotdk);
			List<Object[]> lst = this.nhom_rep.getListNhomByGVHD(COSO_ID, dotdkInt, idGVInt);
			request.setAttribute("dSachNhom", lst);
		}
	}

}
