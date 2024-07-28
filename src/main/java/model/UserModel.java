package model;

import java.io.File;

import javax.servlet.http.Part;

import utils.StringUtils;

public class UserModel {
	private String username;
	private String password;
	private String email;
	private String bio;
	private String imageUrlFromPart;
	
	public UserModel() {
		
	}
	
	public UserModel(String username, String password, String email,Part imagePart) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.bio = "Click the edit icon under image to write your bio!";
		this.imageUrlFromPart = getImageUrl(imagePart);
	}
	// constructor for the update functionality
	public UserModel(String username,String email,Part imagePart,String bio) {
		this.username = username;
		this.email = email;
		if (imagePart != null) {
	        this.imageUrlFromPart = getImageUrl(imagePart);
	    } else {
	        this.imageUrlFromPart = null;
	    }
		this.bio = bio;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getImageUrlFromPart() {
		return imageUrlFromPart;
	}
	public void setImageUrlFromPart(Part part) {
		this.imageUrlFromPart = getImageUrl(part);
	}

	public void setImageUrlFromDB(String imageUrl) {
		this.imageUrlFromPart = imageUrl;
	}
	/**
	 * This method extracts the image file name from the request part containing the uploaded image.
	 * 
	 * @param part The request part containing the uploaded image data.
	 * @return The extracted image file name, or "download.jpg" if no file name is found.
	 * @throws IOException If an error occurs while accessing the part data.
	 */
	private String getImageUrl(Part part) {
		// Define the directory path to store uploaded user images. This path should be configured elsewhere in the application.
	    String savePath = StringUtils.IMAGE_DIR_USER;

	    // Create a File object representing the directory to store uploaded images.
	    File fileSaveDir = new File(savePath);

	    // Initialize the variable to store the extracted image file name.
	    String imageUrlFromPart = null;

	    // Check if the directory to store uploaded images already exists.
	    if (!fileSaveDir.exists()) {
	        // If the directory doesn't exist, create it.
	    	// user mkdirs() method to create multiple or nested folder
	        fileSaveDir.mkdir();
	    }

	    // Get the Content-Disposition header from the request part. This header contains information about the uploaded file, including its filename.
	    String contentDisp = part.getHeader("content-disposition");

	    // Split the Content-Disposition header into individual parts based on semicolons.
	    String[] items = contentDisp.split(";");

	    // Iterate through each part of the Content-Disposition header.
	    for (String s : items) {
	        // Check if the current part starts with "filename" (case-insensitive trim is used).
	        if (s.trim().startsWith("filename")) {
	            // Extract the filename from the current part.
	            // The filename is assumed to be enclosed in double quotes (").
	            // This part removes everything before the equal sign (=) and the double quotes surrounding the filename.
	            imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
	            break; // Exit the loop after finding the filename
	        }
	    }

	    // If no filename was extracted from the Content-Disposition header, set a default filename.
	    if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
	        imageUrlFromPart = "default_profile.jpg";
	    }

	    // Return the extracted or default image file name.
	    return imageUrlFromPart;
	}
	
	

}
