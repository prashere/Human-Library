package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.StoryModel;
import model.UserModel;
import utils.StringUtils;

/**
 * Servlet implementation class FetchStoryServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_FETCH_STORY })
public class FetchStoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController dbController;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchStoryServlet() {
    	this.dbController = new DBController();
    }

    /**
     * Handles HTTP POST requests for retrieving a story.
     * 
     * Retrieves parameters from the request such as story ID and user ID.
     * Fetches the corresponding story information from the database.
     * 
     * @param request  the HttpServletRequest object containing the request parameters
     * @param response the HttpServletResponse object for sending the response
     * @throws ServletException if the servlet encounters difficulty while handling the request
     * @throws IOException      if an I/O error occurs while handling the request
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title =request.getParameter(StringUtils.STORY_ID); 
		int user_id = Integer.parseInt(request.getParameter(StringUtils.USER_ID)); 
		
		StoryModel current_story = dbController.getCurrentStory(title,user_id);
		
		request.setAttribute(StringUtils.STORY, current_story);
		request.setAttribute(StringUtils.CREATOR, user_id);
		request.getRequestDispatcher(StringUtils.URL_EDIT_STORY).forward(request, response);
	}
	
    /**
     * Handles HTTP GET requests for retrieving and displaying a story.
     * 
     * Retrieves parameters from the request such as story title and user ID.
     * Fetches the corresponding story information from the database and the username of the story creator.
     * 
     * @param request  the HttpServletRequest object containing the request parameters
     * @param response the HttpServletResponse object for sending the response
     * @throws ServletException if the servlet encounters difficulty while handling the request
     * @throws IOException      if an I/O error occurs while handling the request
     */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title =request.getParameter(StringUtils.STORY_TITLE); 
		int user_id = Integer.parseInt(request.getParameter(StringUtils.USER_ID)); 
		
		StoryModel current_story = dbController.getCurrentStory(title,user_id);
		
		String creator = dbController.getCurrentUsername(user_id);
		
		request.setAttribute(StringUtils.STORY, current_story);
		request.setAttribute(StringUtils.CREATOR, creator);
		request.getRequestDispatcher(StringUtils.PAGE_STORY_VIEW).forward(request, response); 
	}
	}


