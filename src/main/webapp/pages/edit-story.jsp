<%@page import="utils.StringUtils"%>
<%@page import="model.StoryModel"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
String contextPath = request.getContextPath();
HttpSession userSession = request.getSession();
String currentUser = (String) userSession.getAttribute(StringUtils.USERNAME);

StoryModel curr_story = (StoryModel)request.getAttribute("story");
String category="creativity";
if (curr_story != null) {
     category = curr_story.getCategory();
}
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Create Story</title>
    <link rel="stylesheet" href="<%= contextPath %>/stylesheets/sidebar.css" />
    <link rel="stylesheet" href="<%= contextPath %>/stylesheets/create.css" />
    <link
      href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&family=Onest&display=swap"
      rel="stylesheet"
    />
    <link
      href="https://fonts.googleapis.com/css2?family=Caveat:wght@400..700&family=Montserrat:ital,wght@0,100..900;1,100..900&family=Onest&display=swap"
      rel="stylesheet"
    />
    <script
      src="https://kit.fontawesome.com/d3acb3ae7f.js"
      crossorigin="anonymous"
    ></script>
  </head>
  <body>
    <div id="sidebar"></div>
    <!--sidebar ends here -->
    <div class="main-container">
      <div class="top-img-container">
        <img src="<%= contextPath %>/resources/images/cat.png" alt="cat" />
      </div>
      <div class="bottom-container">
        <form action="<%= contextPath %>/${StringUtils.SERVLET_URL_MODIFY_STORY}" method="post" id="create-post">
        <input type="hidden" name="user_id" value="${creator}">
        <input type="hidden" name="prev_title" value="${story.title}">
        <input type="hidden" name="username" value="<%= currentUser %>">
          <input type="text" name="title" placeholder="Title....." value="${story.title}" required/>
          <select name="topics" required>
            <option value="" selected disabled hidden>Select category</option>
             <%
		    if ("Inspiration".equals(category)) {
		    %>
            <option value="inspiration" selected>Inspiration</option>
            <%
		    }else{
		    %>
		    <option value="inspiration">Inspiration</option>
            <%
		    }
		    %>
		    
		    <%
		    if ("relationships".equals(category)) {
		    %>
            <option value="relationships" selected>Relationships</option>
            <%
		    }else{
		    %>
		    <option value="relationships">Relationships</option>
		    <%} %>
		    
		    <%
		    if ("creativity".equals(category)) {
		    %>
            <option value="creativity" selected>Creativity</option>
            <%
		    }else{
		    %>
		    <option value="creativity">Creativity</option>
		    <%} %>
		    
		    <%
		    if ("identity".equals(category)) {
		    %>
            <option value="identity" selected>Identity</option>
            <%
		    }else{
		    %>
		    <option value="identity">Identity</option>
		    <% } %>
            
             <%
		    if ("life-transitions".equals(category)) {
		    %>
            <option value="life-transitions" selected>Life Transitions</option>
            <%
		    }else{
		    %>
		    <option value="life-transitions">Life Transitions</option>
		    <% } %>
		    
          </select>
          <textarea
            name="thoughts"
            placeholder="Write your thoughts..."
          required>
          ${story.description}
          </textarea>
          <button type="submit" class="publish">Save</button>
        </form>
      </div>
    </div>
    
  </body>
</html>
