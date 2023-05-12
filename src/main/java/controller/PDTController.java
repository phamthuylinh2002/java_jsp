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
import services.PDTService;

@MultipartConfig
@WebServlet({
	"/staff/PDT/dashboard",
	"/staff/PDT",
	"/staff/PDT/quan-ly-sinh-vien",
	"/staff/PDT/quan-ly-sinh-vien/edit/*",
	"/staff/PDT/quan-ly-sinh-vien/delete/*",
	"/staff/PDT/quan-ly-sinh-vien/insert",
	"/staff/PDT/danh-sach-sinh-vien-chua-dang-ky",
	"/staff/PDT/danh-sach-sinh-vien-da-dang-ky",
	"/staff/PDT/danh-sach-cac-nhom-da-tao",
	"/staff/PDT/quan-ly-giang-vien",
	"/staff/PDT/chi-tiet-nhom",
	"/staff/PDT/dot-dang-ky",
	"/staff/PDT/dot-dang-ky/tao",
	"/staff/PDT/logout",
	"/staff/PDT/dot-dang-ky/update",
	
	"/staff/PDT/quan-ly-giang-vien/edit/*",
	"/staff/PDT/quan-ly-giang-vien/delete/*",
	"/staff/PDT/quan-ly-giang-vien/tim-kiem",

	"/staff/PDT/danh-sach-sinh-vien-chua-dang-ky/tim-kiem-sinh-vien-chua-dang-ky",
	"/staff/PDT/danh-sach-sinh-vien-da-dang-ky/tim-kiem-sinh-vien-da-dang-ky",
	"/staff/PDT/danh-sach-cac-nhom-da-tao/tim-kiem",
	"/staff/PDT/quan-ly-sinh-vien/tim-kiem"
})
public class PDTController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PDTService service;

    public PDTController() {
        super();
        this.service= new PDTService();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.service.prepareAndForward(request, response, PageType.PDT_DASHBOARD_PAGE);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			this.service.prepareAndDirect(request, response);
		} catch (ServletException | IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}