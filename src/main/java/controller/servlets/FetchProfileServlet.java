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
 * Servlet implementation class FetchProfileServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_FETCH_PROFILE })
public class FetchProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final DBController dbController;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchProfileServlet() {
    	this.dbController = new DBController();
    }

    /**
     * Handles HTTP GET requests for retrieving user profile information.
     * 
     * Retrieves the username parameter from the request and fetches the corresponding user information from the database.
     * 
     * @param request  the HttpServletRequest object containing the request parameters
     * @param response the HttpServletResponse object for sending the response
     * @throws ServletException if the servlet encounters difficulty while handling the request
     * @throws IOException      if an I/O error occurs while handling the request
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter(StringUtils.USERNAME);
		
		UserModel current_user = dbController.getCurrentUser(userName);
		
		request.setAttribute(StringUtils.PROFILE, current_user);
		request.getRequestDispatcher(StringUtils.URL_EDIT_PROFILE).forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
