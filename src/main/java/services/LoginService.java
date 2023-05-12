package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Constants;
import common.PageInfo;
import common.PageType;
import model.GooglePoJo;
import model.NhanVien;
import model.SinhVien;
import repositories.NhanVienRepositories;
import repositories.SinhVienRepositories;
import utils.GoogleUtils;

public class LoginService {
	protected SinhVienRepositories repositories;
	protected NhanVienRepositories repo;

	public LoginService() {
		this.repositories = new SinhVienRepositories();
		this.repo = new NhanVienRepositories();
	}

	public void prepareAndForward(HttpServletRequest request, HttpServletResponse response, PageType pageType)
			throws ServletException, IOException {
		PageInfo page = PageInfo.pageRoute.get(pageType);
		request.setAttribute("page", page);

		sendDirect(request, response);
	}

	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("path", request.getContextPath());
		request.setAttribute("URI", Constants.GOOGLE_REDIRECT_URI);
		request.setAttribute("ID", Constants.GOOGLE_CLIENT_ID);
		request.getRequestDispatcher("/views/auth/auth.jsp").forward(request, response);
	}

	private void sendDirect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		if (code == null || code.isEmpty()) {
			index(request, response);
			return;
		}
		String accessToken = GoogleUtils.getToken(code);
		GooglePoJo googlePojo = GoogleUtils.getUserInfo(accessToken);
//		request.getSession().setAttribute("googlePojo", googlePojo);
//		request.setAttribute("googlePojo", googlePojo);
		String email = googlePojo.getEmail();

		String role = getRole(email);
		if (role.equals("student")) {
			SinhVien sv = this.repositories.findByEmai(email);
			request.getSession().setAttribute("student", sv);
			response.sendRedirect(request.getContextPath()+"/Student/dashboard");
		} else if (role.equals("staff")) {
			NhanVien nv = this.repo.findByEmai(email);
			if (nv.getRole() == 1) {
				request.getSession().setAttribute("staff", nv);
				response.sendRedirect(request.getContextPath()+"/staff/PDT/dashboard");
			} else if (nv.getRole() == 0) {
				request.getSession().setAttribute("staff", nv);
				response.sendRedirect(request.getContextPath()+"/staff/admin/dashboard");
			} else if (nv.getRole() == 3) {
				request.getSession().setAttribute("staff", nv);
				response.sendRedirect(request.getContextPath()+"/staff/GiangVien");
			} else if(nv.getRole()==2){
				request.getSession().setAttribute("staff", nv);
				response.sendRedirect(request.getContextPath()+"/staff/CNBM/dashboard");
			}

		} else {
			request.getSession().setAttribute("googlePojo", null);
			request.setAttribute("message", "Đăng nhập thất bại");
			index(request, response);
		}
	}

	private String getRole(String email) {
		String pattern_student = "fpt.edu.vn";
//	    String pattern_staff = "fpt.edu.vn";
		String pattern_staff = "gmail.com";
	    
		String sub_email = email.substring(email.indexOf("@") + 1);
		if (sub_email.equals(pattern_staff) && isStaff(email)) {
			return "staff";
		} else if (pattern_student.equals(sub_email) && isStudent(email)) {
			return "student";
		}
		return "guest";
	}

	private boolean isStudent(String email) {
		SinhVien sv = this.repositories.findByEmai(email);
		return sv != null;
	}

	private boolean isStaff(String email) {
		NhanVien nv = this.repo.findByEmai(email);
		return nv != null;
	}
}
