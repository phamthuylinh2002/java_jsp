package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.PageType;
import services.GiangVienService;

@WebServlet({"/staff/GiangVien",
			"/staff/GiangVien/thong-tin-nhom"})

public class GiangVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GiangVienService gvService;
    public GiangVienController() {
        super();
        this.gvService = new GiangVienService();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.gvService.prepareAndForward(request, response, PageType.GIANGVIEN_PAGE);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.gvService.prepareAndDirect(request, response);
	}
}
