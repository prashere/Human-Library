package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.DBController;
import model.StoryModel;
import model.UserModel;
import utils.StringUtils;

/**
 * Servlet implementation class ModifyStoryServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_MODIFY_STORY })
public class ModifyStoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController dbController;
  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyStoryServlet() {
    	this.dbController = new DBController();
    }

    /**
     * Handles HTTP POST requests for modifying a story.
     * 
     * Retrieves parameters from the request such as title, category, description, username, previous title, and user ID.
     * Creates a new StoryModel object with the modified story details.
     * Modifies the story in the database based on the provided information.
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
	     
	     String prev_title = request.getParameter(StringUtils.PREV_TITLE);
	     
	     int user_id = Integer.parseInt(request.getParameter(StringUtils.USER_ID)); 
		
		StoryModel current_story = new StoryModel(title, description, category);
		
		int result = dbController.modifyStory(current_story,user_id,prev_title);
		
		if (result==1) {
			StoryModel story = dbController.getCurrentStory(title,user_id);
			request.setAttribute(StringUtils.CREATOR,username);
	    	request.setAttribute(StringUtils.STORY, story);
	        request.getRequestDispatcher(StringUtils.PAGE_STORY_VIEW).forward(request, response);
		}
		else if(result == 0) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_CREATE_STORY);
			request.getRequestDispatcher(StringUtils.URL_EDIT_STORY).forward(request, response);
			System.out.println("ERROR 0");
		}
		else if(result == -2){
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
			request.getRequestDispatcher(StringUtils.URL_EDIT_STORY).forward(request, response);
			System.out.println("Server error");
		}
	}

}
