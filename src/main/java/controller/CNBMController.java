package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.PageType;
import services.CNBMService;

@WebServlet({"/staff/CNBM",
	"/staff/CNBM/dashboard",
	"/staff/CNBM/chi-tiet-nhom/*",
	"/staff/CNBM/quan-ly-giang-vien",
	"/staff/CNBM/quan-ly-giang-vien/edit/*",
	"/staff/CNBM/quan-ly-giang-vien/delete/*",
	"/staff/CNBM/quan-ly-giang-vien/tim-kiem",
	"/staff/CNBM/chia-nhom",
	"/staff/CNBM/danh-sach-cac-nhom-da-tao",
	"/staff/CNBM/danh-sach-sinh-vien-da-dang-ky",
	"/staff/CNBM/danh-sach-sinh-vien-chua-dang-ky",
	"/staff/CNBM/danh-sach-cac-nhom-da-tao/tim-kiem",
	"/staff/CNBM/danh-sach-sinh-vien-da-dang-ky/tim-kiem-sinh-vien-da-dang-ky",
	"/staff/CNBM/danh-sach-sinh-vien-chua-dang-ky/tim-kiem-sinh-vien-chua-dang-ky",
	"/staff/CNBM/logout",
	"/staff/CNBM/chi-tiet-gv/*",
	"/staff/CNBM/chi-tiet-nhom/deleteSV*"})

public class CNBMController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CNBMService cnbmService;
    public CNBMController() {
        super();
        this.cnbmService = new CNBMService();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.cnbmService.prepareAndForward(request, response, PageType.CNBM_DASHBOARD_PAGE);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.cnbmService.prepareAndDirect(request, response);
	}
}
