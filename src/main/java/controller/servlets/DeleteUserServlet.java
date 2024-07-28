package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import utils.StringUtils;

/**
 * Servlet implementation class DeleteUserServet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_DELETE_PROFILE  })
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController dbController;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
    	this.dbController = new DBController();
    }

    /**
     * Handles HTTP POST requests for deleting a story.
     * 
     * Retrieves parameters from the request such as story ID and user ID.
     * Validates the user and deletes the story if the user is authorized to do so.
     * 
     * @param request  the HttpServletRequest object containing the request parameters
     * @param response the HttpServletResponse object for sending the response
     * @throws ServletException if the servlet encounters difficulty while handling the request
     * @throws IOException      if an I/O error occurs while handling the request
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter(StringUtils.DELETE_USERNAME);
		
		int result = dbController.deleteUserInfo(username);
		if (result == -1) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_DELETE);
			request.getRequestDispatcher(StringUtils.URL_EDIT_PROFILE).forward(request, response);

		}
		else {
			request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_DELETE);
			request.getRequestDispatcher(StringUtils.SERVLET_URL_LOGOUT).forward(request, response);
		}
	}

}
