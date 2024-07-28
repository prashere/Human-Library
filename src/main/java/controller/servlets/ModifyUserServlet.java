package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.DBController;
import model.UserModel;
import utils.StringUtils;

/**
 * Servlet implementation class ModifyUserServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_UPDATE_PROFILE })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)

public class ModifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController dbController;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyUserServlet() {
    	 this.dbController = new DBController();
    }
    
    /**
     * Handles HTTP POST requests for modifying user profile information.
     * 
     * Extracts user information such as username, new username, bio, email, and image from request parameters.
     * Creates a new UserModel object with the modified user details.
     * Updates the user profile in the database.
     * 
     * @param request  the HttpServletRequest object containing the request parameters
     * @param response the HttpServletResponse object for sending the response
     * @throws ServletException if the servlet encounters difficulty while handling the request
     * @throws IOException      if an I/O error occurs while handling the request
     */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Extract student information from request parameters
				String username = request.getParameter(StringUtils.ACTUAL_USERNAME);
				String new_username = request.getParameter(StringUtils.EDIT_USERNAME);
				String bio = request.getParameter(StringUtils.EDIT_BIO);
				String email = request.getParameter(StringUtils.EDIT_EMAIL);
				Part imagePart = request.getPart("image");	
				
				
				UserModel user = new UserModel(new_username,email,imagePart,bio);
				
				int result = dbController.modifyUser(user,username);
				if (result == 1) {
					
//					// Get the image file name from the student object (assuming it was extracted earlier)
					String fileName = user.getImageUrlFromPart();

					// Check if a filename exists (not empty or null)
					if (!fileName.isEmpty() && fileName != null && fileName != "default_profile.jpg") {
					  // Construct the full image save path by combining the directory path and filename
					  String savePath = StringUtils.IMAGE_DIR_USER;
					  imagePart.write(savePath + fileName);  // Save the uploaded image to the specified path
					}
					
					UserModel currentUser = dbController.getCurrentUser(new_username);
//					UserModel currentUser = dbController.getCurrentUser(user.getUsername());
					request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_UPDATE);
					request.setAttribute(StringUtils.PROFILE, currentUser);
					System.out.println("Bio ::"+bio);
		    		request.getRequestDispatcher(StringUtils.URL_PROFILE).forward(request, response);
					System.out.print("Update Success");
				} else if (result == 0) {
					request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_UPDATE);
					request.getRequestDispatcher(StringUtils.URL_EDIT_PROFILE).forward(request, response);
					System.out.print("Update Error1");
				}else if(result == -3) {
					request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_UPDATE_USERNAME);
					request.getRequestDispatcher(StringUtils.URL_EDIT_PROFILE).forward(request, response);
					System.out.print("Update Username doesn't exists!!");
				}
				else if(result == -4) {
					request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_EMAIL);
					request.getRequestDispatcher(StringUtils.URL_EDIT_PROFILE).forward(request, response);
					System.out.print("Update Email taken");
				}
				else {
					request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
					request.getRequestDispatcher(StringUtils.URL_EDIT_PROFILE).forward(request, response);
					System.out.print("Update Error2");
				}	
	}

}
