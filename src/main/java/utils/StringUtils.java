package utils;
import java.io.File;

public class StringUtils {

	// Start: DB Connection
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String LOCALHOST_URL = "jdbc:mysql://localhost:3306/human_library";
	public static final String LOCALHOST_USERNAME = "root";
	public static final String LOCALHOST_PASSWORD = "";

	public static final String IMAGE_ROOT_PATH = "eclipse-workspace\\HumanLibrary\\src\\main\\webapp\\resources\\images\\";
	public static final String IMAGE_DIR_USER = "E:/" + IMAGE_ROOT_PATH + "user\\";
	// End: DB Connection

	// Start: Queries
	public static final String QUERY_REGISTER_USER = "INSERT INTO user ("
			+ "username, password, email, bio, profile_picture) "
			+ "VALUES (?, ?, ?, ?, ?)";
	public static final String QUERY_CREATE_STORY = "INSERT INTO story ("
			+ "title,description,category,creation_date,user_id) "
			+ "VALUES (?, ?, ?, ?,?)";

	public static final String QUERY_LOGIN_USER_CHECK = "SELECT * FROM user WHERE username = ?";
	public static final String QUERY_GET_ALL_STORY = "SELECT * FROM story";
	public static final String QUERY_SEARCH_BY_TITLE = "SELECT * FROM story WHERE title LIKE ?";
	public static final String QUERY_GET_USER_ID = "SELECT id FROM student_info WHERE user_name = ?";
	public static final String QUERY_DELETE_USER = "DELETE FROM user WHERE username = ?";
	public static final String QUERY_DELETE_STORY = "DELETE FROM story WHERE title = ? and user_id = ?";
	public static final String QUERY_USERNAME = "SELECT COUNT(*) FROM user WHERE username = ?";
	public static final String QUERY_PROFILE = "SELECT * FROM user WHERE username = ?";
	public static final String QUERY_USER_NAME = "SELECT username FROM user WHERE user_id = ?";
	public static final String QUERY_STORY = "SELECT * FROM story WHERE title=? and user_id=?";
	public static final String QUERY_USER_ID = "SELECT user_id FROM user WHERE username=?";
	public static final String QUERY_EMAIL = "SELECT COUNT(*) FROM user WHERE email = ?";
	public static final String QUERY_UPDATE_PROFILE = "UPDATE user SET username = ?,bio =?,profile_picture = ?,email=? WHERE username = ?";
	public static final String QUERY_UPDATE_STORY = "UPDATE story SET title = ?,description =?,category = ? WHERE user_id = ? and title=?";
// End: Queries

	// Start: Parameter names
	public static final String USERNAME = "username";
	public static final String PREV_TITLE = "prev_title";
	public static final String STORY_ID = "story-id";
	public static final String STORY_TITLE = "story-title";
	public static final String SEARCH_TERM = "search-term";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String BIRTHDAY = "birthday";
	public static final String GENDER = "gender";
	public static final String EMAIL = "email";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String SUBJECT = "subject";
	public static final String PASSWORD = "password";
	public static final String RE_PASSWORD = "re-password";
	public static final String RETYPE_PASSWORD = "retypePassword";
	public static final String IMAGE = "image";
	public static final String DELETE_USERNAME ="delete-username";
	public static final String STORY = "story";
	public static final String CREATOR = "creator";
	public static final String TITLE = "title";
	public static final String TOPICS = "topics";
	public static final String THOUGHTS = "thoughts";
	// End: Parameter names
	
	public static final String EDIT_USERNAME = "edit-username";
	public static final String ACTUAL_USERNAME = "actual-username";
	public static final String EDIT_BIO = "edit-bio";
	public static final String EDIT_EMAIL = "edit-email";

	// Start: Validation Messages
	// Register Page Messages
	public static final String MESSAGE_SUCCESS_REGISTER = "Successfully Registered!";
	public static final String MESSAGE_ERROR_REGISTER = "Please correct the form data.";
	public static final String MESSAGE_ERROR_UPDATE = "Problem updating profile information.";
	public static final String MESSAGE_ERROR_USERNAME = "Username is already registered.";
	public static final String MESSAGE_ERROR_UPDATE_USERNAME = "No such username";
	public static final String MESSAGE_ERROR_EMAIL = "Email is already registered.";
	public static final String MESSAGE_ERROR_PHONE_NUMBER = "Phone number is already registered.";
	public static final String MESSAGE_ERROR_PASSWORD_UNMATCHED = "Password is not matched.";
	public static final String MESSAGE_ERROR_INCORRECT_EMAIL = "Please enter valid email address";
	public static final String MESSAGE_ERROR_INCORRECT_DATA = "Please correct all the fields.";
	public static final String MESSAGE_ERROR_INCORRECT_USERNAME = "Usernames can only contain alphabets and underscores";
	public static final String MESSAGE_ERROR_INCORRECT_PASSWORD = "Passwords must have 1 Uppercase, 1 special character and 1 number minimum";
	public static final String MESSAGE_ERROR_INCORRECT_PASSWORD_LEN = "Password must contain 8 characters";
	public static final String MESSAGE_SUCCESS_UPDATE = "Successfully Profile Updated!";

	// Login Page Messages
	public static final String MESSAGE_SUCCESS_LOGIN = "Successfully LoggedIn!";
	public static final String MESSAGE_ERROR_LOGIN = "Either username or password is not correct!";
	public static final String MESSAGE_ERROR_CREATE_ACCOUNT = "Account for this username is not registered! Please create a new account.";
	public static final String MESSAGE_ERROR_CREATE_STORY = "Problem creating Story";
	// Other Messages
	public static final String MESSAGE_ERROR_SERVER = "An unexpected server error occurred.";
	public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
	public static final String MESSAGE_ERROR_DELETE = "Cannot delete the user!";
	
	public static final String MESSAGE_SUCCESS = "successMessage";
	public static final String MESSAGE_ERROR = "errorMessage";
	// End: Validation Messages

	// Start: JSP Route
	public static final String PAGE_URL_LOGIN = "/pages/logIn.jsp";
	public static final String PAGE_STORY_VIEW = "/pages/view-story.jsp";
	public static final String PAGE_STORY_CREATE = "/pages/create.jsp";
	public static final String PAGE_URL_REGISTER = "/pages/register.jsp";
	public static final String PAGE_URL_WELCOME = "/pages/welcome.jsp";
	public static final String PAGE_URL_FOOTER = "pages/footer.jsp";
	public static final String PAGE_URL_HEADER = "pages/header.jsp";
	public static final String URL_LOGIN = "/login.jsp";
	public static final String URL_INDEX = "/index.jsp";
	public static final String URL_PROFILE = "/pages/profile.jsp";
	public static final String URL_EDIT_PROFILE = "/pages/edit-profile.jsp";
	public static final String URL_EDIT_STORY = "/pages/edit-story.jsp";
	// End: JSP Route

	// Start: Servlet Route
	public static final String SERVLET_URL_LOGIN = "/login";
	public static final String SERVLET_URL_REGISTER = "/registerUser";
	public static final String SERVLET_URL_LOGOUT = "/logout";
	public static final String SERVLET_URL_HOME = "/home";
	public static final String SERVLET_URL_MODIFY_USER = "/modifyUser";
	public static final String SERVLET_URL_FETCH_PROFILE  = "/fetchProfileServlet";
	public static final String SERVLET_URL_UPDATE_PROFILE  ="/modifyUserServlet";
	public static final String SERVLET_URL_DELETE_PROFILE = "/deleteUserServet";
	public static final String SERVLET_URL_VIEW_PROFILE = "/profileServlet";
	public static final String SERVLET_URL_CREATE_STORY ="/createStoryServlet";
	public static final String SERVLET_URL_FETCH_STORY  ="/fetchStoryServlet";
	public static final String SERVLET_URL_MODIFY_STORY = "/modifyStoryServlet";
	public static final String SERVLET_URL_DELETE_STORY = "/deleteStoryServlet" ;
	// End: Servlet Route

	// Start: Normal Text
	public static final String USER = "user";
	public static final String USER_ID = "user_id";
	public static final String SUCCESS = "success";
	public static final String TRUE = "true";
	public static final String JSESSIONID = "JSESSIONID";
	public static final String LOGIN = "Login";
	public static final String LOGOUT = "Logout";
	public static final String STUDENT_MODEL = "studentModel";
	public static final String STORY_LISTS = "storyLists";
	public static final String IS_SEARCH = "isSearch";
	public static final String PROFILE = "profile";
	public static final String USER_NAME = "user_name";
	public static final String BIO = "bio";
	public static final String SLASH= "/";
	public static final String DELETE_ID= "deleteId";
	public static final String UPDATE_ID= "updateId";
	
	
	// End: Normal Text
}