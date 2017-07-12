package filters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class ConfigFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		/*
		 * File diretorio = new File(System.getProperty("user.home") +
		 * "\\IntegraPSY"); File integraPsyProperties = new
		 * File(diretorio.toString() + "\\integrapsy.properties");
		 */

		if (req.getRequestURI().equals("/integrapsy-web/") || req.getRequestURI().equals("/integrapsy-web/index.xhtml")
				|| req.getRequestURI().equals("/integrapsy-web/login.xhtml")) {
			File diretorio = new File(System.getProperty("user.home") + "\\IntegraPSY");
			File integraPsyProperties = new File(diretorio.toString() + "\\integrapsy.properties");
			if (!diretorio.exists()) {
				res.sendRedirect(req.getContextPath() + "/config.xhtml");
			} else if (!integraPsyProperties.exists()) {

			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}

		/*
		 * if(req.getRequestURI().equals("/integrapsy-web/index.xhtml") ||
		 * req.getRequestURI().equals("/integrapsy-web/")){ HttpServletResponse
		 * res = (HttpServletResponse) response;
		 * res.sendRedirect(req.getContextPath() +"/config.xhtml" ); }else{
		 * chain.doFilter(request, response); }
		 */

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
