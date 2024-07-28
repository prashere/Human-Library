<%@page import="utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String contextPath = request.getContextPath();
String errMsg = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
String successMsg = (String) request.getAttribute(StringUtils.MESSAGE_SUCCESS);
String username = (String) request.getAttribute(StringUtils.USERNAME);
String successParam = request.getParameter(StringUtils.SUCCESS);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Log In</title>
<link rel="stylesheet" href="<%=contextPath%>/stylesheets/logIn.css" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&family=Onest&display=swap"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css2?family=Caveat:wght@400..700&family=Montserrat:ital,wght@0,100..900;1,100..900&family=Onest&display=swap"
	rel="stylesheet" />
</head>
<body>
	<div class="main-container">
		<div class="img-container">
			<img src="<%=contextPath%>/resources/images/login.jpg" alt="Image" />
		</div>
		<div class="form">
			<form action="<%=contextPath + StringUtils.SERVLET_URL_LOGIN%>"
				method="post">
				<h1>Enter details:</h1>
				<input id="username" type="text" name="username" placeholder="Username" required/> 
				<input id="password" type="password" name="password" placeholder="Password" required/>
				<button type="submit">Log In</button>
			</form>
			<p class="link-to-register">
				Have no account yet!<br /> <a href="<%=contextPath%>/pages/register.jsp">Click here</a> to register!
				<%
 if (errMsg != null) {
 %>
				<script type="text/javascript">
        alert("<%=errMsg%>");
				</script>
				<%
				}
				%>
				<%
				if (successParam != null && successParam.equals(StringUtils.TRUE)) {
				%>
				<script type="text/javascript">
					alert("Registration Successful!");
				</script>
				<%
				}
				%>
			</p>
		</div>
	</div>
</body>
</html>