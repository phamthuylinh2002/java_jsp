package filters;

import java.io.IOException;
import java.util.Date;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DotDangKy;
import model.SinhVien;
import repositories.DotDangKyRepositories;


/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST},
	urlPatterns = { "/Student/*" })
public class StudentAuthenticationFilter implements Filter {

    private DotDangKyRepositories dot ;
    public StudentAuthenticationFilter() {
        // TODO Auto-generated constructor stub
    	this.dot = new DotDangKyRepositories();
    }

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest)request;
		SinhVien sv = (SinhVien) servletRequest.getSession().getAttribute("student");
		
		if (sv == null ) {
			servletRequest.setAttribute("message", "Yêu cầu đăng nhập");
			((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath()+"/auth/login");
		}  else {
			try {
				DotDangKy dotDK = this.dot.findDotDKbyId(sv.getDotDangKyId());
				if (dotDK.getThoiGianBatDau().after(new Date()) || dotDK.getThoiGianKetThuc().before(new Date())){
					((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath()+"/auth/login");
				} else {
					chain.doFilter(servletRequest, response);
				}
				
			} catch (Exception e) {
				String duongDanHome = servletRequest.getContextPath() + "/Student";
				request.setAttribute("path", duongDanHome);
				request.setAttribute("message", "Bạn không ở trong đợt đăng ký này");
				request.getRequestDispatcher("/views/404.jsp").forward(request, response);
				e.printStackTrace();
			}
		}
			
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
