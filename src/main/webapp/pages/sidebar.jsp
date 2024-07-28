<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%
String contextPath = request.getContextPath();
%>

<div class="logo">
	 <i class="fa-solid fa-book-open" id="logo"></i>
</div>
<div class="icons">
	<a href = "<%=contextPath%>/index.jsp"><i class="fa-solid fa-house house"></i> </a>
	<a href="<%=contextPath%>/pages/create.jsp">
		<i class="fa-solid fa-plus plus"></i>
	</a>
</div>
<div class="options">
	<i class="fa-solid fa-bars"></i>
</div>
