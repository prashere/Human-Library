package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.StoryModel;
import utils.StringUtils;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/home" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController dbController;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
    	this.dbController = new DBController();
    }
    
    /**
     * Handles HTTP GET requests for retrieving all story information.
     * 
     * Retrieves all story information from the database.
     * 
     * @param request  the HttpServletRequest object containing the request parameters
     * @param response the HttpServletResponse object for sending the response
     * @throws ServletException if the servlet encounters difficulty while handling the request
     * @throws IOException      if an I/O error occurs while handling the request
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<StoryModel> stories = dbController.getAllStoryInfo();
		request.setAttribute(StringUtils.STORY_LISTS, stories);
		request.getRequestDispatcher(StringUtils.URL_INDEX).forward(request, response);
	}
    
    /**
     * Handles HTTP POST requests for retrieving and displaying selected story information based on a search term.
     * 
     * Retrieves the search term parameter from the request.
     * Fetches selected story information from the database based on the search term.
     * 
     * @param request  the HttpServletRequest object containing the request parameters
     * @param response the HttpServletResponse object for sending the response
     * @throws ServletException if the servlet encounters difficulty while handling the request
     * @throws IOException      if an I/O error occurs while handling the request
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String searchTerm = request.getParameter(StringUtils.SEARCH_TERM); 
    	
    	ArrayList<StoryModel> stories = dbController.getSelectedStoryInfo(searchTerm);
    	
    	request.setAttribute(StringUtils.IS_SEARCH, true);
		request.setAttribute(StringUtils.STORY_LISTS, stories);
		request.getRequestDispatcher(StringUtils.URL_INDEX).forward(request, response);
    }
    }

