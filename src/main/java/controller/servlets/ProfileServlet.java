package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.UserModel;
import utils.StringUtils;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = {StringUtils.SERVLET_URL_VIEW_PROFILE })
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private final DBController dbController;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
    	this.dbController = new DBController();
    }

    /**
     * Handles HTTP POST requests for retrieving and displaying user profile information.
     * 
     * Retrieves the username parameter from the request and fetches the corresponding user information from the database.
     * 
     * @param request  the HttpServletRequest object containing the request parameters
     * @param response the HttpServletResponse object for sending the response
     * @throws ServletException if the servlet encounters difficulty while handling the request
     * @throws IOException      if an I/O error occurs while handling the request
     */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter(StringUtils.USERNAME);
		
		UserModel currentUser = dbController.getCurrentUser(userName);
		
		request.setAttribute(StringUtils.PROFILE, currentUser);
		request.getRequestDispatcher(StringUtils.URL_PROFILE).forward(request, response);
	}

}
