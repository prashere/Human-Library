package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DBController;
import model.LoginModel;
import model.UserModel;
import utils.StringUtils;

/**
 * This Servlet class handles login requests for a student management system.
 * It retrieves username and password from the login form submission,
 * validates them against a database using a `DBController`, and redirects the user
 * accordingly based on the login result.
 */
@WebServlet(urlPatterns = StringUtils.SERVLET_URL_LOGIN, asyncSupported = true)
public class LogInUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final DBController dbController;

    public LogInUserServlet() {
        this.dbController = new DBController();
    }

    /**
     * Handles HTTP POST requests for login.
     *
     * @param request The HttpServletRequest object containing login form data.
     * @param response The HttpServletResponse object for sending responses.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Extract username and password from the request parameters
        String userName = request.getParameter(StringUtils.USERNAME);
        String password = request.getParameter(StringUtils.PASSWORD);

        // Create a LoginModel object to hold user credentials
        LoginModel loginModel = new LoginModel(userName, password);
        
        UserModel currentUser = dbController.getCurrentUser(userName);

        // Call DBController to validate login credentials
        int loginResult = dbController.getStudentLoginInfo(loginModel);

        // Handle login results with appropriate messages and redirects
        if (loginResult == 1) {
            // Login successful
        	System.out.print("Success");
        	HttpSession userSession = request.getSession();
			userSession.setAttribute(StringUtils.USERNAME, userName);
			userSession.setMaxInactiveInterval(30*60);
			
			Cookie userCookie= new Cookie(StringUtils.USER, userName);
			userCookie.setMaxAge(30*60);
			response.addCookie(userCookie);
			
            request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_LOGIN);
            request.setAttribute(StringUtils.PROFILE, currentUser);
    		request.getRequestDispatcher(StringUtils.URL_PROFILE).forward(request, response);
        } else if (loginResult == 0) {
            // Username or password mismatch
        	System.out.println("Mismatch");
            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_LOGIN);
			request.setAttribute(StringUtils.USERNAME, userName);
            request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
        } else if (loginResult == -1) {
            // Username not found
        	System.out.println("No such username");
            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_CREATE_ACCOUNT);
			request.setAttribute(StringUtils.USERNAME, userName);
            request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
        } else {
            // Internal server error
        	System.out.print("Server error");
            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
			request.setAttribute(StringUtils.USERNAME, userName);
            request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
        }
        request.setAttribute(StringUtils.USERNAME, "");
        request.setAttribute(StringUtils.PASSWORD, "");
    }
}