package common;

import java.util.Map;

import org.apache.commons.collections.map.HashedMap;



public class PageInfo {
	private String title;
	private String contentUrl;

	public static Map<PageType, PageInfo> pageRoute = new HashedMap();
	static {
		pageRoute.put(PageType.STUDENT_DASHBOARD_PAGE, new PageInfo("Dashboard Student", "/views/student/model/dashboard.jsp"));
// PDT
		pageRoute.put(PageType.PDT_DASHBOARD_PAGE, new PageInfo("Dashboard PDT", "/views/staff/du_an_tot_nghiep/dashboard.jsp"));
		pageRoute.put(PageType.PDT_MANAGERSV_PAGE, new PageInfo("Quản lý sinh viên", "/views/staff/du_an_tot_nghiep/danh_sach_sinh_vien.jsp"));
		pageRoute.put(PageType.PDT_MANAGERGV_PAGE, new PageInfo("Quản lý giảng viên", "/views/staff/du_an_tot_nghiep/danh_sach_giang_vien.jsp"));
		pageRoute.put(PageType.PDT_CHIANHOM_PAGE, new PageInfo("Chia nhóm", "/views/staff/du_an_tot_nghiep/chia_nhom.jsp"));
		pageRoute.put(PageType.PDT_DSACHNHOM_PAGE, new PageInfo("Quản lý nhóm PDT", "/views/staff/du_an_tot_nghiep/danh_sach_nhom.jsp"));
		pageRoute.put(PageType.PDT_DOTDK_PAGE, new PageInfo("Quản lý đợt đăng ký PDT", "/views/staff/du_an_tot_nghiep/dot_dang_ky.jsp"));
		pageRoute.put(PageType.PDT_SVCHUADANGKY_PAGE, new PageInfo("Quản lý sinh viên chưa đăng ký PDT", "/views/staff/du_an_tot_nghiep/danh_sach_sinh_vien_chua_dang_ky.jsp"));
		pageRoute.put(PageType.PDT_SVDADANGKY_PAGE, new PageInfo("Quản lý sinh viên đã đăng ký PDT", "/views/staff/du_an_tot_nghiep/danh_sach_sinh_vien_da_dang_ky.jsp"));
		pageRoute.put(PageType.PDT_CHITIETNHOM_PAGE, new PageInfo("Chi tiết nhóm PDT", "/views/staff/du_an_tot_nghiep/chi_tiet_nhom.jsp"));
		
		pageRoute.put(PageType.admin_DASHBOARD_PAGE, new PageInfo("Dashboard", "/views/staff/admin/dashboard.jsp"));
		pageRoute.put(PageType.admin_MANAGERSV_PAGE, new PageInfo("Manager student", "/views/staff/admin/danh_sach_sinh_vien.jsp"));
		pageRoute.put(PageType.admin_CHIANHOM_PAGE, new PageInfo("Chia nhóm", "/views/staff/admin/chia_nhom.jsp"));
		pageRoute.put(PageType.admin_DSACHNHOM_PAGE, new PageInfo("Danh sách nhóm", "/views/staff/admin/danh_sach_nhom.jsp"));
		pageRoute.put(PageType.admin_SVCHUADANGKY_PAGE, new PageInfo("SV chưa đăng ký", "/views/staff/admin/danh_sach_sinh_vien_chua_dang_ky.jsp"));
		pageRoute.put(PageType.admin_SVDADANGKY_PAGE, new PageInfo("SV đã đăng ký", "/views/staff/admin/danh_sach_sinh_vien_da_dang_ky.jsp"));
		pageRoute.put(PageType.admin_CHITIETNHOM_PAGE, new PageInfo("Chi tiết nhóm", "/views/staff/admin/chi_tiet_nhom.jsp"));
		pageRoute.put(PageType.admin_MANAGER_NV_PAGE, new PageInfo("Manager staff", "/views/staff/admin/danh_sach_nhan_vien.jsp"));
		pageRoute.put(PageType.admin_hocky, new PageInfo("Quản lý học kỳ", "/views/staff/admin/hoc_ky.jsp"));
		pageRoute.put(PageType.admin_dotdangky, new PageInfo("Quản lý đợt đăng ký Admin", "/views/staff/admin/dot_dang_ky.jsp"));
		
		pageRoute.put(PageType.GIANGVIEN_PAGE, new PageInfo("Giang Vien", "/views/staff/giang_vien/dashboard.jsp"));
//CNBM
		pageRoute.put(PageType.CNBM_DASHBOARD_PAGE, new PageInfo("Dashboard CNBM", "/views/staff/CNBM/dashboard.jsp"));
		pageRoute.put(PageType.CNBM_CHITIETNHOM_PAGE, new PageInfo("Chi tiết nhóm CNBM", "/views/staff/CNBM/chi_tiet_nhom.jsp"));
		pageRoute.put(PageType.CNBM_MANAGERGV_PAGE, new PageInfo("Quản lý giảng viên CNBM", "/views/staff/CNBM/danh_sach_giang_vien.jsp"));
		pageRoute.put(PageType.CNBM_CHIANHOM_PAGE, new PageInfo("Chia nhóm CNBM", "/views/staff/du_an_tot_nghiep/chia_nhom.jsp"));
		pageRoute.put(PageType.CNBM_DSACHNHOM_PAGE, new PageInfo("Danh sách nhóm CNBM", "/views/staff/CNBM/danh_sach_nhom.jsp"));
		pageRoute.put(PageType.CNBM_SVCHUADANGKY_PAGE, new PageInfo("SV chưa đăng ký", "/views/staff/CNBM/danh_sach_sinh_vien_chua_dang_ky.jsp"));
		pageRoute.put(PageType.CNBM_SVDADANGKY_PAGE, new PageInfo("SV đã đăng ký", "/views/staff/CNBM/danh_sach_sinh_vien_da_dang_ky.jsp"));
		pageRoute.put(PageType.chitietGV, new PageInfo("Chi tiết giảng viên", "/views/staff/CNBM/chi_tiet_gv.jsp"));
		
		pageRoute.put(PageType.LOGIN,new PageInfo("Login", "/views/auth/login.jsp"));

		pageRoute.put(PageType.STAFF,new PageInfo("STAFF", "/views/staff/du_an_tot_nghiep/danh_sach_sinh_vien.jsp"));
	}

	public PageInfo(String title, String contentUrl) {
		super();
		this.title = title;
		this.contentUrl = contentUrl;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContentUrl() {
		return contentUrl;
	}
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
}
