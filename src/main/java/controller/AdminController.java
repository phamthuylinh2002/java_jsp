package controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.PageType;
import services.AdminService;

@MultipartConfig
@WebServlet({ "/staff/admin/dashboard", "/staff/admin/quan-ly-sinh-vien", "/staff/admin/quan-ly-sinh-vien/edit/*",
		"/staff/admin/quan-ly-sinh-vien/delete/*", "/staff/admin/quan-ly-sinh-vien/insert",
		"/staff/admin/danh-sach-sinh-vien-chua-dang-ky", "/staff/admin/danh-sach-sinh-vien-da-dang-ky",
		"/staff/admin/danh-sach-cac-nhom-da-tao", "/staff/admin/quan-ly-nhan-vien", "/staff/admin/chi-tiet-nhom",
		"/staff/admin/logout",
		"/staff/Admin/dot-dang-ky",
		"/staff/Admin/dot-dang-ky/update",
		"/staff/Admin/dot-dang-ky/tao",
		"/staff/admin/danh-sach-sinh-vien-chua-dang-ky/tim-kiem-sinh-vien-chua-dang-ky",
		"/staff/admin/danh-sach-sinh-vien-da-dang-ky/tim-kiem-sinh-vien-da-dang-ky",
		"/staff/admin/quan-ly-nhan-vien/tim-kiem-nhan-vien", "/staff/admin/danh-sach-cac-nhom-da-tao/tim-kiem",
		"/staff/admin/quan-ly-sinh-vien/tim-kiem", "/staff/admin/quan-ly-nhan-vien/edit/*",
		"/staff/admin/quan-ly-nhan-vien/delete/*", "/staff/admin/quan-ly-nhan-vien/insert",
		"/staff/admin/hoc-ky",
		"/staff/admin/hoc-ky/insert/*"})
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminService service;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminController() {
		super();
		this.service = new AdminService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.service.prepareAndForward(request, response, PageType.admin_DASHBOARD_PAGE);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			this.service.prepareAndDirect(request, response);
		} catch (ServletException | IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
