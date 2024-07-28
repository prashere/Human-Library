<%@page import="model.UserModel"%>
<%@page import="utils.StringUtils"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
<link rel="stylesheet" href="<%=contextPath%>/stylesheets/profile.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&family=Onest&display=swap"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css2?family=Caveat:wght@400..700&family=Montserrat:ital,wght@0,100..900;1,100..900&family=Onest&display=swap"
	rel="stylesheet" />
<script src="https://kit.fontawesome.com/d3acb3ae7f.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<c:if test="${not empty profile}">
		<!-- if open -->
		<div id="sidebar"></div>
		<!--sidebar ends here-->
		<div class="main-container">
			<div class="user"
				style="background-image: url('resources/images/user/${profile.imageUrlFromPart}');background-size: contain;">
				<div class="edit-container">
					<div class="edit-holder">
						<form class="edit-data-pass" action="<%=contextPath + StringUtils.SERVLET_URL_FETCH_PROFILE%>"
				method="get">
							<!-- form -->
							<input type="hidden" id="hiddenField" name="username"
								value="${profile.username}">
							<button type="submit" id="submitButton" style="margin:0; padding:0; border:none">
								<i class="fa-solid fa-user-pen"></i>
							</button>
						</form>
					</div>
				</div>
			</div>
			<div class="container-left">
				<div class="user-details">
					<h1>${profile.username}</h1>
					<p>${profile.bio}</p>
				</div>
	</c:if>
	<!-- if close -->
	
	</div>
	<div class="container-right">
		<div class="vertical-text">Storyteller</div>

		<form action="<%=contextPath%>/deleteUserServet" method="post" class="delete">
          <input type="hidden" value="${profile.username}" name="delete-username"/>
          <button class="delete-profile">Delete</button>
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
      fetch("<%=contextPath%>/pages/sidebar.jsp")
        .then((response) => response.text())
        .then((data) => (document.getElementById("sidebar").innerHTML = data));
    </script>
</body>
</html>