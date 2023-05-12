package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.PageType;
import services.StudentService;


@WebServlet({
	"/Student",
	"/Student/dashboard",
	"/Student/join-private",
	"/Student/tao-nhom",
	"/Student/dang-ky",
	"/Student/huy-dang-ky",
	"/Student/out-nhom",
	"/Student/logout",
	"/Student/join-public",
	"/Student/update-team"
})

public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentService service;

	public StudentController() {
		super();
		this.service= new StudentService();

	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.service.prepareAndForward(request, response, PageType.STUDENT_DASHBOARD_PAGE);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.service.prepareAndDirect(request, response);
	}

}
