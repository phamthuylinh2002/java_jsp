package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.NhanVien;
import repositories.ChuyenNganhRepositories;
import repositories.DotDangKyRepositories;
import services.ExcelService;
import utils.HelperUtils;

/**
 * Servlet implementation class FilesController
 */
@MultipartConfig
@WebServlet({ "/files", "/files/export-danh-sach-sv-da-dang-ky", "/files/export-danh-sach-sv-chua-dang-ky",
		"/files/upload-them", "/files/delete-sv", "/files/exportDSNhom", "/files/delete-gv", "/files/upload-addNV",
		"/files/delete-Nv", "/files/upload-them-gv"

})

public class FilesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	File dir;
	ExcelService excel_service;
	private ChuyenNganhRepositories cnRep;
	private DotDangKyRepositories dotRep;

	public FilesController() {
		super();
		this.excel_service = new ExcelService();
		this.cnRep = new ChuyenNganhRepositories();
		this.dotRep = new DotDangKyRepositories();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		dir = new File(request.getServletContext().getRealPath("/files"));
		if (!dir.exists()) {
			dir.mkdir();
		}
		NhanVien nv = (NhanVien) request.getSession().getAttribute("staff");
		int co_so = nv.getCoSoId();
		int role = nv.getRole();
		if (uri.contains("exportDSNhom")) {
			Integer chuyen_nganh_id = HelperUtils.parseInteger(request.getSession().getAttribute("ScopeCN_nhom") + "");
			Integer dotdk = HelperUtils.parseInteger(request.getSession().getAttribute("ScopeDotDK_nhom") + "");
			String path = dir + "/danh-sach-nhom-" + chuyen_nganh_id + "-" + dotdk + ".xlsx";
//				Integer chuyen_nganh_id = HelperUtils.parseInteger(request.getParameter("chuyen_nganh_id"));
			if (role == 1) {
				excel_service.exportNhom(dotdk, chuyen_nganh_id, path, co_so);
			} else if (role == 2) {
				int bm_id = nv.getBoMonId();
				List<Integer> cn = this.cnRep.getCNByBoMon(bm_id);
				excel_service.exportNhomCNBM(dotdk, cn, path, co_so);
			}

			response.sendRedirect(
					request.getContextPath() + "/files/danh-sach-nhom-" + chuyen_nganh_id + "-" + dotdk + ".xlsx");
		} else if (uri.contains("export-danh-sach-sv-chua-dang-ky")) {
			Integer chuyen_nganh_id = HelperUtils
					.parseInteger(request.getSession().getAttribute("ScopeCN_chuaDK") + "");
			Integer dot_dk = HelperUtils.parseInteger(request.getSession().getAttribute("ScopeDotDK_chuaDK") + "");
			String path = dir + "/danh-sach-chua-dang-ky-" + chuyen_nganh_id + "-" + dot_dk + ".xlsx";
			int trang_thai = 0;
			if (role == 1) {
				excel_service.exportEx(co_so, chuyen_nganh_id, trang_thai, dot_dk, path);
			} else if (role == 2) {
				int bm_id = nv.getBoMonId();
				List<Integer> cn = this.cnRep.getCNByBoMon(bm_id);
				excel_service.exportExCNBM(co_so, cn, trang_thai, dot_dk, path);
			}

			response.sendRedirect(request.getContextPath() + "/files/danh-sach-chua-dang-ky-" + chuyen_nganh_id + "-"
					+ dot_dk + ".xlsx");
			return;
		} else if (uri.contains("export-danh-sach-sv-da-dang-ky")) {
			Integer chuyen_nganh_id = HelperUtils.parseInteger(request.getSession().getAttribute("ScopeCN_DaDK") + "");
			Integer dot_dk = HelperUtils.parseInteger(request.getSession().getAttribute("ScopeDotDK_DaDK") + "");
			String path = dir + "/danh-sach-da-dang-ky-" + chuyen_nganh_id + "-" + dot_dk + ".xlsx";
			int trang_thai = 1;
			if (role == 1) {
				excel_service.exportEx(co_so, chuyen_nganh_id, trang_thai, dot_dk, path);
			} else if (role == 2) {
				int bm_id = nv.getBoMonId();
				List<Integer> cn = this.cnRep.getCNByBoMon(bm_id);
				excel_service.exportExCNBM(co_so, cn, trang_thai, dot_dk, path);
			}
			response.sendRedirect(request.getContextPath() + "/files/danh-sach-da-dang-ky-" + chuyen_nganh_id + "-"
					+ dot_dk + ".xlsx");
			return;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dir = new File(request.getServletContext().getRealPath("/files"));
		if (!dir.exists()) {
			dir.mkdir();
		}
		String uri = request.getRequestURI();
		NhanVien nv = (NhanVien) request.getSession().getAttribute("staff");
		int co_so = nv.getCoSoId();
		int role = nv.getRole();
		if (role == 1) {
			if (uri.contains("upload-them-gv")) {
				Part file = request.getPart("file_dsgv");
				File ex_file = new File(dir, file.getSubmittedFileName());
				file.write(ex_file.getAbsolutePath());
				Integer dotdk = this.dotRep.getDot(co_so);
				String path = ex_file.getAbsolutePath();
				excel_service.insertIntoDSGV(path, co_so, 0, dotdk);
				response.sendRedirect(request.getContextPath() + "/staff/PDT/quan-ly-giang-vien");
			} else if (uri.contains("upload-them")) {
				Part file = request.getPart("file_dssv");
				File ex_file = new File(dir, file.getSubmittedFileName());
				file.write(ex_file.getAbsolutePath());
				Integer dotdk = HelperUtils.parseInteger(request.getParameter("dot_id"));
				String path = ex_file.getAbsolutePath();
				excel_service.insertIntoDS(dotdk, path, co_so);
				response.sendRedirect(request.getContextPath() + "/staff/PDT/quan-ly-sinh-vien");
			} else if (uri.contains("delete-sv")) {
				Part file = request.getPart("file_ds_delete");
				File ex_file = new File(dir, file.getSubmittedFileName());
				file.write(ex_file.getAbsolutePath());
				Integer dotdk = HelperUtils.parseInteger(request.getParameter("dot_id"));
				String path = ex_file.getAbsolutePath();
				excel_service.deleteDS(dotdk, path, co_so);

				response.sendRedirect(request.getContextPath() + "/staff/PDT/quan-ly-sinh-vien");
			}else if (uri.contains("delete-gv")) {
				Part file = request.getPart("file_ds_delete");
				File ex_file = new File(dir, file.getSubmittedFileName());
				file.write(ex_file.getAbsolutePath());
				String path = ex_file.getAbsolutePath();
				excel_service.deleteDSGV(path, co_so, 0);

				response.sendRedirect(request.getContextPath() + "/staff/PDT/quan-ly-giang-vien");
			}
		} else if (role == 2) {
			int bo_mon = nv.getBoMonId();
			if (uri.contains("upload-them")) {
				Part file = request.getPart("file_dsgv");
				File ex_file = new File(dir, file.getSubmittedFileName());
				file.write(ex_file.getAbsolutePath());
				Integer dotdk = this.dotRep.getDot(co_so);
				String path = ex_file.getAbsolutePath();
				excel_service.insertIntoDSGV(path, co_so, bo_mon, dotdk);
				response.sendRedirect(request.getContextPath() + "/staff/CNBM/quan-ly-giang-vien");
			} else if (uri.contains("delete-gv")) {
				Part file = request.getPart("file_ds_delete");
				File ex_file = new File(dir, file.getSubmittedFileName());
				file.write(ex_file.getAbsolutePath());
				String path = ex_file.getAbsolutePath();
				excel_service.deleteDSGV(path, co_so, bo_mon);

				response.sendRedirect(request.getContextPath() + "/staff/CNBM/quan-ly-giang-vien");
			}
		} else if (role == 0) {
			if (uri.contains("upload-addNV")) {
				Part file = request.getPart("file_dsNv");
				File ex_file = new File(dir, file.getSubmittedFileName());
				file.write(ex_file.getAbsolutePath());
				String path = ex_file.getAbsolutePath();
				excel_service.insertNVtoDS(path);
				response.sendRedirect(request.getContextPath() + "/staff/admin/quan-ly-nhan-vien");
			} else if (uri.contains("delete-Nv")) {
				Part file = request.getPart("file_dsNv_delete");
				File ex_file = new File(dir, file.getSubmittedFileName());
				file.write(ex_file.getAbsolutePath());

				String path = ex_file.getAbsolutePath();
				excel_service.deleteNVtoDS(path);
				response.sendRedirect(request.getContextPath() + "/staff/admin/quan-ly-nhan-vien");
			} else if (uri.contains("upload-them")) {
				Part file = request.getPart("file_dssv");
				File ex_file = new File(dir, file.getSubmittedFileName());
				file.write(ex_file.getAbsolutePath());
				Integer dotdk = HelperUtils.parseInteger(request.getParameter("dot_id"));
				String path = ex_file.getAbsolutePath();
				excel_service.insertIntoDS(dotdk, path, co_so);
				response.sendRedirect(request.getContextPath() + "/staff/admin/quan-ly-sinh-vien");
			} else if (uri.contains("delete-sv")) {
				Part file = request.getPart("file_ds_delete");
				File ex_file = new File(dir, file.getSubmittedFileName());
				file.write(ex_file.getAbsolutePath());
				Integer dotdk = HelperUtils.parseInteger(request.getParameter("dot_id"));
				String path = ex_file.getAbsolutePath();
				excel_service.deleteDS(dotdk, path, co_so);

				response.sendRedirect(request.getContextPath() + "/staff/admin/quan-ly-sinh-vien");
			}
		}
	}
}
