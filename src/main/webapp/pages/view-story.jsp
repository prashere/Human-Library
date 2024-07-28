<%@page import="utils.StringUtils"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%
String contextPath = request.getContextPath();
 HttpSession userSession = request.getSession();
 String currentUser = (String) userSession.getAttribute(StringUtils.USERNAME);
 String creator = (String)request.getAttribute("creator");
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Story</title>
    <link rel="stylesheet" href="<%= contextPath %>/stylesheets/sidebar.css" />
    <link rel="stylesheet" href="<%= contextPath %>/stylesheets/view-story.css" />
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
    <!--sidebar ends here-->
    <div class="main-container">
    <c:if test="${not empty story}">
      <h1>${story.title}<span class="date">${story.creation_date}</span></h1>
      <div class="user">
        <form class="profile" method="post" action="<%= contextPath%>/${StringUtils.SERVLET_URL_VIEW_PROFILE}">
			<input type="hidden" value="${creator}" name="username">
				<i class="fa-regular fa-user"></i>
				<button type="submit" style="background:none; margin:0; padding:0;border:none" class="username">${creator}</button>
			</form>
      </div>
      <p>${story.category}</p>
      <div class="stat-holder">
        <i class="fa-regular fa-heart"></i>
        <span>100</span>
      </div>
      <p class="content">
        ${story.description}
      </p>
      
       <%
    if ((currentUser).equals(creator)) {
%>
<div class="forms">
      	<form class="edit" style="display:inline;" method="post" action="<%= contextPath%>/${StringUtils.SERVLET_URL_FETCH_STORY}">
      	<input type="hidden" name="story-id" value="${story.title}">
      	<input type="hidden" name="user_id" value="${story.user_id}">
      		<button type="submit">Edit</button>
      	</form>
      	<form class="delete" method="post" action="<%= contextPath%>/${StringUtils.SERVLET_URL_DELETE_STORY}" style="display:inline;">
      	<input type="hidden" name="story-id" value="${story.title}">
      	<input type="hidden" name="user_id" value="${story.user_id}">
      		<button type="submit">Delete</button>
      	</form>
      	</div>
      <%
    }
%>
      
      </c:if>
    </div>
    <script>
      fetch("<%= contextPath %>/pages/sidebar.jsp")
        .then((response) => response.text())
        .then((data) => (document.getElementById("sidebar").innerHTML = data));
    </script>
</body>
</html>