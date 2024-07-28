package model;

public class StoryModel {
	private String title;
	private String description;
	private String category;
	private String creation_date;
	private int user_id; 
	public StoryModel(String title, String description, String category, String creation_date,int user_id) {
		super();
		this.title = title;
		this.description = description;
		this.category = category;
		this.creation_date = creation_date;
		this.user_id = user_id;
	}
	public StoryModel() {
		
	}
	public StoryModel(String title, String description, String category) {
		this.title = title;
		this.description = description;
		this.category = category;
	}
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	
	

}
