<%@page contentType="text/html"%>


<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:choose>
	<c:when test="${sessionScope.username == null}">
		<%
			response.sendRedirect("login.html");
		%>
	</c:when>
	<c:otherwise>
		<c:set var="USERNAME" value="${sessionScope.username}" scope="session" />
	</c:otherwise>
</c:choose>

<%-- 
    Document   : PedidoForm
    Created on : 25/11/2010, 11:24:23
    Author     : Fogel
--%>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Perfil do Usuário</title>
<link rel="stylesheet" type="text/css" href="estilo.css" />
<style type="text/css">
body {
	color: white;
	background-color: black;
	font-size: 0.825em;
	font-family: Arial, Helvetica, sans-serif;
}

.error {
	color: yellow;
}
</style>
<section id="page">

	<%@ include file="header.jsp" %>
</head>
<body>
	<div class="line"></div>
	<article id="perfil">

		<h3>Perfil do Usuário</h3>
		<div class="line"></div>
		<form:form commandName="user" method="POST">
			<table>
				<tr>
					<td width="220">Nome:</td>
					<td><form:input size="40" path="name" /></td>
					<td><form:errors path="name" cssClass="error" /></td>
				</tr>
				<tr>
					<td width="220">Email:</td>
					<td><form:input size="40" path="email" /></td>
					<td><form:errors path="email" cssClass="error" /></td>
				</tr>
				<tr>
					<td width="220">Nome do Projeto de Pesquisa:</td>
					<td><form:input size="40" path="projectName" /></td>
				</tr>
				<tr>
					<td width="220">Validade do Projeto:</td>
					<td width="80"><form:input size="20" path="projectExpireDate" />
					<td><form:errors path="projectExpireDate" cssClass="error" /></td>
				</tr>
				<tr>
					<td width="220">Tipo do Professor:</td>
					<td><form:select path="role.roleId">
							<form:option value="NONE" label="--- Selecione ---" />
							<form:options items="${listaTipoProfessor}" itemValue="roleId" itemLabel="name" />
						</form:select></td>
					<td><form:errors path="role.roleId" cssClass="error" /></td>
				</tr>

			</table>
			<br>
			<input type="image" src="Imagens/salvarPerfil.png" value="submit" />
		</form:form>
	</article>
	<footer>
		<div class="line"></div>
		<p>Copyright 2013 - Rafael Fogel</p>
		<a href="main.html" class="up">Inicio</a>
	</footer>

	</section>

</body>
</html>
