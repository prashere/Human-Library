<%@page import="model.UserModel"%>
<%@page import="controller.database.DBController"%>
<%@page import="utils.StringUtils"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Edit-Profile</title>
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
	<div class="main-container">
		<div class="container-left">
			<h1 class="edit-heading">Edit Profile</h1>
			<form class="edit-form" method="post" action="<%=contextPath%>/modifyUserServlet" enctype="multipart/form-data"><!-- MAIN EDIT FORM -->
				<c:if test="${not empty profile}">
				<input type="hidden" value="${profile.username}" name="actual-username">
					<input type="text" placeholder="Username"
						value="${profile.username}" name="edit-username" required/>
				    <input type="email" placeholder="Enter email.."
						value="${profile.email}" name="edit-email" required/>
					<input type="text" placeholder="Enter the bio for profile....." name="edit-bio" value ="${profile.bio}" required/>
					<img src="resources/images/user/${profile.imageUrlFromPart}"
						id="previewImage" width="100" alt="Profile Image">
					<input type="file" accept="image/*" id="image-upload" name="image">
					<p> Please choose your original image file if you don't want to change profile picture before Saving</p>

					<button type="submit">Save</button>
				</c:if>
			</form>
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
			
			<form class="change-password-form">
				<!--change password-->
				<button type="button" id="change-password-btn">Change
					Password</button>
				<div id="password-change-form" style="display: none">
					<input type="password" id="old-password"
						placeholder="Enter previous password" /> <input type="password"
						id="new-password" placeholder="Enter new password" /> <input
						type="password" id="confirm-password"
						placeholder="Re-enter new password" />
					<button type="submit" id="confirm-change-btn">Confirm</button>
				</div>
		</div>
		<div class="container-right">
			<div class="vertical-text">Storyteller</div>
		</div>
	</div>
	<script>
		document
				.getElementById("change-password-btn")
				.addEventListener(
						"click",
						function() {
							document.getElementById("password-change-form").style.display = "block";
						});
		// Update preview image when a new file is selected
		document
				.getElementById('image-upload')
				.addEventListener(
						'change',
						function(event) {
							var file = event.target.files[0];
							var reader = new FileReader();

							reader.onload = function(e) {
								document.getElementById('previewImage').src = e.target.result;
							};

							reader.readAsDataURL(file);
						});
	</script>
</body>
</html>
