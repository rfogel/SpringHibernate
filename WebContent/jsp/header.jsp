<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<hgroup>
	<h1>Home Page</h1>
	<h5>
		Olá,
		<c:out value="${USERNAME}" />
	</h5>
	<span style="float: right"> <a href="?lang=en">en</a> | <a href="?lang=pt">pt</a></span> </hgroup>
	<nav class="clear">
	<ul>
		<li><a href="changePassword.html"><spring:message code="label.changepassword" /></a></li>
		<li><a href="userProfile.html"><spring:message code="label.userprofile" /></a></li>
		<li><a href="logout.html"><spring:message code="label.logout" /></a></li>
	</ul>
	</nav>
</body>
</html>