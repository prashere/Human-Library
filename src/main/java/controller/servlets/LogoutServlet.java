package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.StringUtils;

@WebServlet(asyncSupported = true, urlPatterns = StringUtils.SERVLET_URL_LOGOUT)
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Handles HTTP POST requests for logging out a user.
	 * 
	 * Performs the following actions:
	 * 1. Clears existing cookies by setting their max age to 0.
	 * 2. Invalidates the user session (if it exists).
	 * 3. Redirects the user to the login page with a parameter indicating successful logout.
	 * 
	 * @param request  the HttpServletRequest object containing the request parameters
	 * @param response the HttpServletResponse object for sending the response
	 * @throws ServletException if the servlet encounters difficulty while handling the request
	 * @throws IOException      if an I/O error occurs while handling the request
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Handle logout request (assuming this is a logout servlet)

		// 1. Clear existing cookies
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				// Set max age to 0 to effectively delete the cookie
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}

		// 2. Invalidate user session (if it exists)
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		// 3. Redirect to login page
		String messageDeleteSuccess = (String) request.getAttribute(StringUtils.MESSAGE_SUCCESS);
		if (messageDeleteSuccess != null) {
			String redirectUrl = request.getContextPath() + StringUtils.URL_INDEX + "?" + "isLogout" + "=" + "false"+ "&" + "isDelete=true";
			response.sendRedirect(redirectUrl);
		}
		else {
			String redirectUrl = request.getContextPath() + StringUtils.URL_INDEX + "?" + "isLogout" + "=" + "true";
			response.sendRedirect(redirectUrl);
		}
		
	}

}