package io.robusta.api;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CorsFilter
 */
@WebFilter("/fora/*")
public class CorsFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public CorsFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (response instanceof HttpServletResponse){
			((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
			((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, POST, PUT");
			((HttpServletResponse) response).addHeader("Access-Control-Allow-Headers", "Content-Type");
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
