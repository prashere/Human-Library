package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.Part;

import model.LoginModel;
import model.PasswordEncryptionWithAes;
import model.StoryModel;
import model.UserModel;
import utils.StringUtils;

public class DBController {

	/**
	 * Establishes a connection to the database using pre-defined credentials and
	 * driver information.
	 * 
	 * @return A `Connection` object representing the established connection to the
	 *         database.
	 * @throws SQLException           if a database access error occurs.
	 * @throws ClassNotFoundException if the JDBC driver class is not found.
	 */
	public Connection getConnection() throws SQLException, ClassNotFoundException {

		// Load the JDBC driver class specified by the StringUtils.DRIVER_NAME constant
		Class.forName(StringUtils.DRIVER_NAME);

		// Create a connection to the database using the provided credentials
		return DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME,
				StringUtils.LOCALHOST_PASSWORD);
	}

	/**
	 * This method attempts to register a new student in the database.
	 * 
	 * @param student A `StudentModel` object containing the student's information.
	 * @return An integer value indicating the registration status: - 1:
	 *         Registration successful - 0: Registration failed (no rows affected) -
	 *         -1: Internal error (e.g., ClassNotFound or SQLException)
	 * @throws SQLException           if a database access error occurs.
	 * @throws ClassNotFoundException if the JDBC driver class is not found.
	 */
	public int registerUser(UserModel user) {

		try {
			// Prepare a statement using the predefined query for student registration
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_REGISTER_USER);

			// Set the student information in the prepared statement
			stmt.setString(1, user.getUsername());
			stmt.setString(2, PasswordEncryptionWithAes.encrypt(user.getUsername(), user.getPassword()));
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getBio());
			stmt.setString(5, user.getImageUrlFromPart());

			if (checkUsernameIfExists(user.getUsername())) {
				// If username exists, return a special value to indicate login should be
				// stopped
				return -3;
			}
			if (checkEmailIfExists(user.getEmail())) {
				// If email exists, return a special value to indicate login should be stopped
				return -4;
			}

			// Execute the update statement and store the number of affected rows
			int result = stmt.executeUpdate();

			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Registration successful
			} else {
				return 0; // Registration failed (no rows affected)
			}

		} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			return -1; // Internal error
		}
	}
	
	public int createStory(StoryModel story) {
		try {
			// Prepare a statement using the predefined query for story creation
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_CREATE_STORY);

			// Set the story information in the prepared statement
			stmt.setString(1,story.getTitle() );
			stmt.setString(2,story.getDescription() );
			stmt.setString(3,story.getCategory() );
			stmt.setString(4, story.getCreation_date());
			stmt.setInt(5, story.getUser_id());
			System.out.println("QUERY: "+stmt);
			int result = stmt.executeUpdate();

			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Registration successful
			} else {
				return 0; // Registration failed (no rows affected)
			}
			
	}catch (ClassNotFoundException | SQLException ex) {
		// Print the stack trace for debugging purposes
		ex.printStackTrace();
		return -1; // Internal error
	}
	}
	public int getUserId(String username) {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_USER_ID);

			// Set the story information in the prepared statement
			stmt.setString(1,username);
			
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
	            // Retrieve the user_id from the result set
	            int userId = result.getInt("user_id");
	            // Return the user_id
	            return userId;
	        } else {
	            // No user found with the given username
	            return -1; // or throw an exception or handle accordingly
	        }
			
		}catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			return -1; // Internal error
		}
	}

//	/**
//	 * This method attempts to validate a student login by checking the username and
//	 * password against a database.
//	 * 
//	 * @param username The username provided by the user attempting to log in.
//	 * @param password The password provided by the user attempting to log in.
//	 * @return An integer value indicating the login status: - 1: Login successful -
//	 *         0: Username or password mismatch - -1: Username not found in the
//	 *         database - -2: Internal error (e.g., SQL or ClassNotFound exceptions)
//	 * @throws SQLException           if a database access error occurs.
//	 * @throws ClassNotFoundException if the JDBC driver class is not found.
//	 */
	public int getStudentLoginInfo(LoginModel loginModel) {
		// Try-catch block to handle potential SQL or ClassNotFound exceptions
		try {
			// Prepare a statement using the predefined query for login check
			PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_LOGIN_USER_CHECK);

			// Set the username in the first parameter of the prepared statement
			st.setString(1, loginModel.getUsername());

			// Execute the query and store the result set
			ResultSet result = st.executeQuery();

			// Check if there's a record returned from the query
			if (result.next()) {
				// Get the username from the database
				String userDb = result.getString(StringUtils.USERNAME);

				// Get the password from the database
				String encryptedPwd = result.getString(StringUtils.PASSWORD);

				String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, userDb);
				// Check if the username and password match the credentials from the database
				if (userDb.equals(loginModel.getUsername()) && decryptedPwd.equals(loginModel.getPassword())) {
					// Login successful, return 1
					return 1;
				} else {
					// Username or password mismatch, return 0
					return 0;
				}
			} else {
				// Username not found in the database, return -1
				return -1;
			}

			// Catch SQLException and ClassNotFoundException if they occur
		} catch (SQLException | ClassNotFoundException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			// Return -2 to indicate an internal error
			return -2;
		}
	}

	public Boolean checkEmailIfExists(String email) {
		try {
			// Preparing a statement to check if the email exists in the database
			PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_EMAIL);
			st.setString(1, email);
			ResultSet result = st.executeQuery();

			// Checking if any records match the provided email
			if (result.next()) {
				int count = result.getInt(1);
				return count > 0; // If count is greater than 0, email exists
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return false; // Returning false if an error occurs or email doesn't exist
	}

	public Boolean checkUsernameIfExists(String username) {
		try {
			// Preparing a statement to check if the username exists in the database
			PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_USERNAME);
			st.setString(1, username);
			ResultSet result = st.executeQuery();

			// Checking if any records match the provided username
			if (result.next()) {
				int count = result.getInt(1);
				return count > 0; // If count is greater than 0, username exists
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return false; // Returning false if an error occurs or username doesn't exist
	}

	public UserModel getCurrentUser(String username) {
		 try {
		        // Preparing statement to retrieve the user's data
		        PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_PROFILE);
		        st.setString(1, username);
		        ResultSet result = st.executeQuery();
		        UserModel current_user = new UserModel();
		        
		        if(result.next()) {
		        	current_user.setUsername(result.getString("username"));
		        	current_user.setBio(result.getString("bio"));
		        	current_user.setEmail(result.getString("email"));
		        	current_user.setPassword(result.getString("password"));
		        	current_user.setImageUrlFromDB(result.getString("profile_picture"));
		        }
		        return current_user;
		 }catch (SQLException | ClassNotFoundException ex) {
					ex.printStackTrace();
					return null;
				}
	}
	
	public String getCurrentUsername(int user_id) {
		String username=null;
		 try {
		        // Preparing statement to retrieve the user's data
		        PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_USER_NAME);
		        st.setInt(1, user_id);
		        
		        ResultSet result = st.executeQuery();
		        
		        if(result.next()) {
		        	username = result.getString("username");
		        }
		        return username;
		 }catch (SQLException | ClassNotFoundException ex) {
					ex.printStackTrace();
					return null;
				}
	}

	
	public StoryModel getCurrentStory(String title, int user_id) {
		 try {
		        // Preparing statement to retrieve the user's data
		        PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_STORY);
		        st.setString(1, title);
		        st.setInt(2, user_id);
		        
		        ResultSet result = st.executeQuery();
		        StoryModel current_story = new StoryModel();
		        
		        if(result.next()) {
		        	current_story.setTitle(result.getString("title"));
		        	current_story.setDescription(result.getString("description"));
		        	current_story.setCategory(result.getString("category"));
		        }
		        return current_story;
		 }catch (SQLException | ClassNotFoundException ex) {
					ex.printStackTrace();
					return null;
				}
	}
	
	public int modifyUser(UserModel user,String username) {
		String query = StringUtils.QUERY_UPDATE_PROFILE;
		try {
			PreparedStatement stmt = getConnection().prepareStatement(query);
			
			stmt.setString(1, user.getUsername());
			stmt.setString(2,user.getBio());
			stmt.setString(3,user.getImageUrlFromPart());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, username);
			
			System.out.println("SQL Query: " + stmt.toString());
			System.out.println(user.getImageUrlFromPart());
			
			if (!checkUsernameIfExists(user.getUsername())) {
				// If username exists, return a special value to indicate login should be
				// stopped
				return -3;
			}
			// Execute the update statement and store the number of affected rows
			int result = stmt.executeUpdate();
			System.out.println("Result val:: "+result);
			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Update successful
			} else {
				return 0; // Update failed (no rows affected)
			}
		} catch (SQLException | ClassNotFoundException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			// Return -2 to indicate an internal error
			return -2;
		}
	}
	
	public int modifyStory(StoryModel story,int user_id, String title) {
		String query = StringUtils.QUERY_UPDATE_STORY;
		try {
			PreparedStatement stmt = getConnection().prepareStatement(query);
			
			stmt.setString(1, story.getTitle());
			stmt.setString(2,story.getDescription());
			stmt.setString(3,story.getCategory());
			stmt.setInt(4, user_id);
			stmt.setString(5, title);
			
			System.out.println("QUEry : "+stmt);
			
			int result = stmt.executeUpdate();
			if (result > 0) {
				return 1; // Update successful
			} else {
				return 0; // Update failed (no rows affected)
			}
		} catch (SQLException | ClassNotFoundException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			// Return -2 to indicate an internal error
			return -2;
		}
		}
	public ArrayList<StoryModel> getAllStoryInfo() {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_ALL_STORY);
			ResultSet result = stmt.executeQuery();

			ArrayList<StoryModel> stories = new ArrayList<StoryModel>();

			while (result.next()) {
				StoryModel story = new StoryModel();
				story.setTitle(result.getString("title"));
				story.setDescription(result.getString("description"));
				story.setCategory(result.getString("category"));
				story.setUser_id(result.getInt("user_id"));
				story.setCreation_date(result.getString("creation_date"));
							
				stories.add(story);
			}
			return stories;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<StoryModel> getSelectedStoryInfo(String searchTerm) {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_SEARCH_BY_TITLE);
			
			stmt.setString(1, "%" + searchTerm + "%");
			ResultSet result = stmt.executeQuery();

			ArrayList<StoryModel> stories = new ArrayList<StoryModel>();

			while (result.next()) {
				StoryModel story = new StoryModel();
				story.setTitle(result.getString("title"));
				story.setDescription(result.getString("description"));
				story.setCategory(result.getString("category"));
				story.setUser_id(result.getInt("user_id"));
				story.setCreation_date(result.getString("creation_date"));
							
				stories.add(story);
			}
			return stories;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public int deleteUserInfo(String username) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.QUERY_DELETE_USER);
			st.setString(1, username);
			return st.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}
	
	public int deleteStoryInfo(String title,int user_id) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.QUERY_DELETE_STORY);
			st.setString(1, title);
			st.setInt(2, user_id);
			
			return st.executeUpdate();
			
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}
}