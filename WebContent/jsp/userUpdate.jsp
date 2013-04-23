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
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Atualização de Usuário</title>
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

	<%@ include file="header.jsp" %>
</head>

<body>
	<div class="line"></div>
	<article id="atualizarusuario">
		<h3>Atualização de Usuário</h3>
		<div class="line"></div>
		<form:form commandName="login" method="POST">
			<c:if test="${param.mensagem != null}">
				<table>
					<tr>
						<td><img src="Imagens/exclamacao.png"></td>
						<td><h4>
								&nbsp;
								<c:out value="${param.mensagem}" />
							</h4></td>
					</tr>
				</table>
			</c:if>
			<form:input type="hidden" path="loginId" />
			<table>
				<tr>
					<td width="140">Usuário:</td>
					<td><form:input path="username" /></td>
					<td><form:errors path="username" cssClass="error" /></td>
				</tr>
				<tr>
					<td width="140">Senha:</td>
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
			<c:if test="${param.comando == 'inserir'}">
				<input type="image" name="inserir" src="Imagens/inserirUsuario.png"
					value="submit" />
			</c:if>
			<c:if test="${param.comando == 'editar'}">
				<input type="image" name="editar" src="Imagens/salvarUsuario.png"
					value="submit" />
			</c:if>
		</form:form>
	</article>
	<footer>
		<div class="line"></div>
		<p>Copyright 2010 - Rafael Fogel</p>
		<a href="userManager.html" class="up">Voltar</a>
	</footer>
	</section>

</body>
</html>
