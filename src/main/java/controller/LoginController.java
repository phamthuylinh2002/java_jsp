package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.PageType;
import services.LoginService;

@WebServlet({
	"/auth/login",
	"/auth/logout"
})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginService service;


	public LoginController() {

		super();
		this.service= new LoginService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				this.service.prepareAndForward(request, response, PageType.LOGIN);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
