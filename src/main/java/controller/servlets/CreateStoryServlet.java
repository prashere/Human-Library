package controller.servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.StoryModel;
import utils.StringUtils;

/**
 * Servlet implementation class CreateStoryServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_CREATE_STORY })
public class CreateStoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private final DBController dbController;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateStoryServlet() {
    	 this.dbController = new DBController();
    }
    
    /**
     * Handles HTTP POST requests for creating a new story. Retrieves parameters from the request such as title, category, description, and username.
     * Validates the existence of the user and creates a new story if the user exists. 
     * 
     * @param request  the HttpServletRequest object containing the request parameters
     * @param response the HttpServletResponse object for sending the response
     * @throws ServletException if the servlet encounters difficulty while handling the request
     * @throws IOException      if an I/O error occurs while handling the request
     */

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String title = request.getParameter(StringUtils.TITLE);
	     String category = request.getParameter(StringUtils.TOPICS);
	     String description = request.getParameter(StringUtils.THOUGHTS);
	     String username = request.getParameter(StringUtils.USERNAME);
	     System.out.println("USERNAME::" + username);
	     
	     int user_id = dbController.getUserId(username);
	     if(user_id == -1) {
	    	 request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_CREATE_STORY);
	         request.getRequestDispatcher(StringUtils.PAGE_STORY_CREATE).forward(request, response); 
	         return;
	     }
	     
	     LocalDateTime currentDateTime = LocalDateTime.now();
	        
	     // Format the date and time
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	     String creationDate = currentDateTime.format(formatter);
	     
	     StoryModel story = new StoryModel(title, description, category, creationDate,user_id);
	     
	     int resp = dbController.createStory(story);
	     
	     if (resp == 1) {
	    	 request.setAttribute(StringUtils.CREATOR,username);
	    	 request.setAttribute(StringUtils.STORY, story);
	         request.getRequestDispatcher(StringUtils.PAGE_STORY_VIEW).forward(request, response); 
	     }
	     else if(resp == 0) {
	    	 request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_CREATE_STORY);
	         request.getRequestDispatcher(StringUtils.PAGE_STORY_CREATE).forward(request, response); 
	     }
	     else {
	    	 request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
	         request.getRequestDispatcher(StringUtils.PAGE_STORY_CREATE).forward(request, response);
	     }


}
	}
