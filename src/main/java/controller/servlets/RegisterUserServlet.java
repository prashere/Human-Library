package controller.servlets;

import java.io.IOException;

import java.time.LocalDate;

import javax.servlet.ServletContext;
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
import utils.ValidationUtil;

/**
 * This Servlet class handles student registration requests. It extracts student
 * information from the registration form submission, performs basic data
 * validation (to be implemented), and attempts to register the student in the
 * database using a `DBController`. The user is redirected to the login page
 * upon successful registration.
 *
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_REGISTER })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class RegisterUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final DBController dbController;

	public RegisterUserServlet() {
		this.dbController = new DBController();
	}

	/**
	 * Handles HTTP POST requests for student registration.
	 *
	 * @param request  The HttpServletRequest object containing registration form
	 *                 data.
	 * @param response The HttpServletResponse object for sending responses.
	 * @throws ServletException if a servlet-specific error occurs.
	 * @throws IOException      if an I/O error occurs.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		// Extract student information from request parameters
		String email = request.getParameter(StringUtils.EMAIL);
		String username = request.getParameter(StringUtils.USERNAME);
		String password = request.getParameter(StringUtils.PASSWORD);
		String re_password = request.getParameter(StringUtils.RE_PASSWORD);
		
		Part imagePart = request.getPart("image");	
		
		if(!password.equals(re_password)) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_PASSWORD_UNMATCHED);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			System.out.print("Password Mismatch");
			return;
		}
		
		UserModel user = new UserModel(username,password,email,imagePart);
		
		
		 //Implement data validation here (e.g., check for empty fields, email format,etc.)
		if(!ValidationUtil.isEmail(user.getEmail())) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_INCORRECT_EMAIL);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			return;
		}
		if(!ValidationUtil.isValidUsername(user.getUsername())) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_INCORRECT_USERNAME);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			return;
		}
		if(!ValidationUtil.isValidPassword(user.getPassword())) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_INCORRECT_PASSWORD);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			return;
		}
		if(!ValidationUtil.hasLength(user.getPassword(),8)) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_INCORRECT_PASSWORD_LEN);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			return;
		}

		// Call DBController to register the student
		int result = dbController.registerUser(user);

		if (result == 1) {
			
//			// Get the image file name from the student object (assuming it was extracted earlier)
			String fileName = user.getImageUrlFromPart();

			// Check if a filename exists (not empty or null)
			if (!fileName.isEmpty() && fileName != null && fileName != "default_profile.jpg") {
			  // Construct the full image save path by combining the directory path and filename
			  String savePath = StringUtils.IMAGE_DIR_USER;
			  System.out.println(savePath + fileName);
			  imagePart.write(savePath + fileName);  // Save the uploaded image to the specified path
			}

			request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_REGISTER);
			response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_LOGIN + "?success=true");
			System.out.print("Success");
		} else if (result == 0) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_REGISTER);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			System.out.print("Error1");
		}else if(result == -3) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_USERNAME);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			System.out.print("Username taken");
		}
		else if(result == -4) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_EMAIL);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			System.out.print("Email taken");
		}
		else {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			System.out.print("Error2");
		}
	}
}