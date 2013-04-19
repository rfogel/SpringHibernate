<%@page contentType="text/html"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:choose>
	<c:when test="${sessionScope.username == null}">
		<% response.sendRedirect("login.html"); %>
	</c:when>
	<c:otherwise>
		<c:set var="USERNAME" value="${sessionScope.username}" scope="session" />
	</c:otherwise>
</c:choose>

<%-- 
    Document   : login
    Created on : 06/11/2010, 16:13:42
    Author     : Fogel
--%>

<!DOCTYPE html>
<!-- The new doctype -->
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Alterar Senha</title>
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

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}

h4 {
	font-family: "Myriad Pro", "Helvetica Neue", Helvetica, Arial,
		Sans-Serif;
	font-size: 1.0em;
	font-weight: normal;
	color: yellow;
}
</style>

<section id="page">

	<hgroup>
		<h1>Home Page</h1>
		<h5>
			Olá,
			<c:out value="${USERNAME}" />
		</h5>
	</hgroup>
	<nav class="clear">
		<ul>
			<li><a href="AlterarSenha.html">Alterar Senha</a></li>
			<li><a href="Perfil.html">Editar Perfil</a></li>
			<li><a href="Logout.html">Sair</a></li>
		</ul>
	</nav>
</head>

<body>

	<div class="line"></div>

	<article id="alterarsenha">
		<h3>Alterar Senha</h3>
		<div class="line"></div>
		<form:form commandName="login" method="POST">
			<table>
				<input type="hidden" name="idLogin" value="${sessionScope.id_login}" />
				<tr>
					<td width="140">Usuário:</td>
					<td><form:input path="username" disabled="true" /></td>
				</tr>
				<tr>
					<td width="140">Nova senha:</td>
					<td><form:input type="password" path="password" /></td>
					<td><form:errors path="password" cssClass="error" /></td>
				</tr>
				<tr>
					<td width="140">Confirmação da senha:</td>
					<td><form:input type="password" path="newPassword" /></td>
					<td><form:errors path="newPassword" cssClass="error" /></td>
				</tr>

			</table>
			<br>
			<input type="image" src="Imagens/salvar.png" value="submit" />
		</form:form>
	</article>
	<footer>
		<div class="line"></div>
		<p>Copyright 2010 - Rafael Fogel</p>
		<a href="TemplateForm.html" class="up">Inicio</a>
	</footer>
	</section>

</body>
</html>
