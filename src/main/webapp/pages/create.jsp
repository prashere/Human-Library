<%@page import="utils.StringUtils"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
 <%
String contextPath = request.getContextPath();
HttpSession userSession = request.getSession();
String currentUser = (String) userSession.getAttribute(StringUtils.USERNAME);
%>
<!DOCTYPE html>
<html>
<head>
<style>
.plus{
 background-color: #fa897b;
  color: white;
}
</style>
<meta charset="ISO-8859-1">
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
        <form action="<%= contextPath %>/${StringUtils.SERVLET_URL_CREATE_STORY}" method = "post" id="create-post"><!-- FORM -->
          <input type="text" name="title" placeholder="Title....." />
          <input type="hidden" name="username" value="<%= currentUser %>">
          <select name="topics">
            <option value="" selected disabled hidden>Select category</option>
            
            <option value="inspiration">Inspiration</option>
            <option value="relationships">Relationships</option>
            <option value="creativity">Creativity</option>
            <option value="identity">Identity</option>
            <option value="life-transitions">Life Transitions</option>
          </select>
          <textarea
            name="thoughts"
            placeholder="Write your thoughts..."
          ></textarea>
          <button type="submit" class="publish">Publish</button>
        </form>
      </div>
    </div>
    <%
				String errMsg = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
				String successMsg = (String) request.getAttribute(StringUtils.MESSAGE_SUCCESS);
				%>
				</script>

				<%-- Check if errMsg is not null and display it in an alert --%>
				<%
				if (errMsg != null) {
				%>
				<script type="text/javascript">
       	alert("<%=errMsg%>");
    </script>
				<%
				}
				%>

				<%-- Check if successMsg is not null and display it in an alert --%>
				<%
				if (successMsg != null) {
				%>
				<script type="text/javascript">
        alert("<%=successMsg%>
					");
				</script>
				<%
				}
				%>
    
    <script>
      fetch("<%= contextPath %>/pages/sidebar.jsp")
        .then((response) => response.text())
        .then((data) => (document.getElementById("sidebar").innerHTML = data));
    </script>
  </body>
</body>
</html>