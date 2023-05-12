package filters;

import java.io.IOException;

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

import model.NhanVien;

@WebFilter(dispatcherTypes = {DispatcherType.REQUEST},
urlPatterns = { "/staff/PDT/*"})
public class PDTFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PDTFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest  servletRequest = (HttpServletRequest) request;
		NhanVien nv = (NhanVien) servletRequest.getSession().getAttribute("staff");
		if(nv != null && nv.getRole()==1) {
			chain.doFilter(servletRequest, response);
		} else {
			servletRequest.setAttribute("message", "Yêu cầu đăng nhập");
			((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath()+"/auth/login");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
