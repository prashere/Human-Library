<%@page import="utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
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
			<form action="<%=contextPath%>/registerUser" method="post"
				enctype="multipart/form-data">
				<h1>Enter details:</h1>
				<input type="email" name="email" placeholder="Email"  required/> <input
					type="text" name="username" placeholder="Username" required/> <input
					type="password" name="password" placeholder="Password" required/> <input
					type="password" placeholder="Re-type Password" name="re-password" required/> 
					<p>Choose Profile Picture</p><input
					type="file" accept="image/*" id="image-upload" name="image" />
				<button type="submit" style="margin-top: 10px">Register</button>
			</form>
			<p class="link-to-register">
				Already have an account!<br /> <a href="<%=contextPath%>/pages/logIn.jsp">Click here</a> to log in!<br>
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

			</p>
		</div>
	</div>
</body>
</body>
</html>