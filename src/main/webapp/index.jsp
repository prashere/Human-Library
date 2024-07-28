<%@page import="utils.StringUtils"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    // Get the session and request objects
    HttpSession userSession = request.getSession();
    String currentUser = (String) userSession.getAttribute(StringUtils.USERNAME);
    String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<style>
.house{
 background-color: #fa897b;
  color: white;
}
</style>
<meta charset="ISO-8859-1">
<title>Human Library</title>
<link href="<%=contextPath%>/stylesheets/index.css" rel="stylesheet" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
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
<%
    String paramValue = request.getParameter("isLogout");
	String deleteValue = request.getParameter("isDelete");
    if (paramValue != null && paramValue.equals("true")) {
%>
        <script>
            alert("Successfully logged out");
        </script>
<%
    }
%>
<% 
if (deleteValue != null && deleteValue.equals("true")) {
%>
        <script>
            alert("Successfully deleted profile!");
        </script>
<%
    }
%>
	<div class="main-container">
		<div class="navbar">
			<form class="search-container" method="post" action="<%= contextPath %>/home">
				<button type="submit" id="search">
					<i class="fa-solid fa-search"></i>
				</button>
				<input id="search-input" type="text" name="search-term" placeholder="Search..." />
			</form>
			<%
    			if (currentUser != null) {
			%>
			<form class="profile" method="post" action="<%= contextPath%>/${StringUtils.SERVLET_URL_VIEW_PROFILE}">
			<input type="hidden" value="<%= currentUser %>" name="username">
				<i class="fa-regular fa-user"></i>
				<button type="submit" style="background:none; margin:0; padding:0;border:none" class="username"><%= currentUser %></button>
			</form>
			<%
    			}
			%>
			<div>
				<form
					action="<%
                    // Conditionally set the action URL based on user session
                    if (currentUser != null) {
                        out.print(contextPath + StringUtils.SERVLET_URL_LOGOUT);
                    } else {
                        out.print(contextPath + StringUtils.PAGE_URL_LOGIN);
                    }
                %>"
					method="post" class="login">
					<input type="submit"
						value="<%
                        // Conditionally set the button label based on user session
                        if (currentUser != null) {
                            out.print(StringUtils.LOGOUT);
                        } else {
                            out.print(StringUtils.LOGIN);
                        }
                    %>" />
				</form>
			</div>
		</div>
		<!--navbar ends here-->
		<div class="book-holder">
			<img src="resources/images/main-book-2.png" alt="mainBook" />
		</div>
	</div>
	<div class="background-container">
		<div class="container-left">
			<div id="sidebar"></div>
			<!--sidebar ends here-->
			<div class="divided-container-left">
				<div class="text-container-left">
					<h1>Untangle your thoughts and watch them soar for miles.</h1>
					<p>Join us in shaping a world of empathy and understanding.
						Share your story on The Human Library platform and contribute to a
						community where every voice matters. Together, let's foster
						compassion, respect, and mental well-being through the power of
						storytelling.</p>
				</div>
				<!--text-container-left ends-->
				<div class="recent-posts">
				<c:if test="${!isSearch}">
					<h1>Recent Stories</h1>
					<form method="get" action="<%= contextPath %>/home">
						<button type="submit" class="view-btn">View</button>
					</form>
				</c:if>
					<div class="post-holder">
					<c:if test="${not empty storyLists}">
					<c:forEach var="story" items="${storyLists}">
					<div class="row2">
					<form style="display:in-line" method="get" action="<%=contextPath%>/fetchStoryServlet">
							<div class="curved-rectangle">
							<input type="hidden" name="story-title" value="${story.title}">
							<input type="hidden" name="user_id" value="${story.user_id}">
								<button type="submit" class="title" style="background:none; border:none">${story.title}</button>
								<div class="user-info">
									<span>${story.category}<br>${story.creation_date}</span>
								</div>
							</div>
							</form>
						</div>
					</c:forEach>
					</c:if>
						
					</div>
				</div>
			</div>
		</div>
		<div class="container-right">
			<div class="divided-container-right">
				<div class="text-container-right">
					<h1>
						HUMAN<br />LIBRARY
					</h1>
				</div>
				<!--text-container-right ends-->
			</div>
		</div>
	</div>
	<footer>
		<div class="footer-top-section">
			<div class="footer-section">
				<div class="left-top-footer">
					<div class="logo">
						<i class="fa-solid fa-book-open" id="logo"></i>
					</div>
					<h1>HUMAN LIBRARY</h1>
				</div>
				<div class="left-footer-top-icons">
					<i class="fa-brands fa-instagram"></i> <i
						class="fa-brands fa-facebook-f" id="facebook"></i> <i
						class="fa-brands fa-twitter"></i>
				</div>
			</div>
			<div class="footer-section" id="second-section-footer">
				<h1>REACH OUT</h1>
				<p>
					humanlibrary@gmail.com<br />01-4770441
				</p>
			</div>
			<div class="footer-section" id="third-section-footer">
				<h1>
					ENJOY SAYING! <br /> ENJOY READING!
				</h1>
			</div>
		</div>
		<div class="copyright-holder">@2024 Copyright author</div>
	</footer>
	<%
				String successMsg = (String) request.getAttribute(StringUtils.MESSAGE_SUCCESS);
				%>

	<%-- Check if successMsg is not null and display it in an alert --%>
	<%
				if (successMsg != null) {
				%>
	<script type="text/javascript">
        alert("<%=successMsg%>");
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