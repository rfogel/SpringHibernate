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


<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.error {
	color: yellow;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Manutenção de Professores</title>

<link rel="stylesheet" type="text/css" href="estilo.css" />
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
			<li><a href="changePassword.html">Alterar Senha</a></li>
			<li><a href="userProfile.html">Editar Perfil</a></li>
			<li><a href="logout.html">Sair</a></li>
		</ul>
	</nav>
</head>

<body>

	<div class="line"></div>

	<section id="articles">

		<article id="usuario">

			<h3>Lista de Professores</h3>

			<div class="line"></div>

			<div class="articleBody clear">
				<c:if test="${param.mensagem != null}">
					<table>
						<tr>
							<c:if test="${param.status == 'ok'}">
								<td><img src="Imagens/ok.png"></td>
							</c:if>
							<c:if test="${param.status == 'erro'}">
								<td><img src="Imagens/exclamacao.png"></td>
							</c:if>
							<td><h4 style="color: yellow">
									&nbsp;
									<c:out value="${param.mensagem}" />
								</h4></td>
						</tr>
					</table>
					<br>
				</c:if>
				<table style="border-style: none" border="1" cellspacing="0">
					<tr>
						<td width="25" height="40" style="border-style: none"></td>
						<th width="100" height="40"
							style="border-style: ridge; border-color: #fcfcfc">Login</th>
						<th width="100" height="40"
							style="border-style: ridge; border-color: #fcfcfc">Nome</th>
						<th width="160" height="40"
							style="border-style: ridge; border-color: #fcfcfc">Email</th>
					</tr>
					<c:forEach items="${requestScope.listaUser}" var="lisUser">
						<form:form commandName="login" method="POST">
							<input type="hidden" name="loginId" value="${lisUser.userId}" />
							<tr>
								<td width="25" style="border-style: none;"><input
									type="image" name="remover" src="Imagens/deletar.png"
									value="submit" /></td>
								<td width="150"
									style="border-style: ridge; border-color: #fcfcfc">&nbsp;<a
									href='userUpdate.html?comando=editar&userId=<c:out value="${lisUser.userId}"/>'>
									<c:out value="${lisUser.login.username}" /></a></td>
								<td width="350"
									style="border-style: ridge; border-color: #fcfcfc">&nbsp;<c:out
										value="${lisUser.name}" /></td>
								<td width="400"
									style="border-style: ridge; border-color: #fcfcfc">&nbsp;<c:out
										value="${lisUser.email}" /></td>
							</tr>
						</form:form>
					</c:forEach>
				</table>
				<br> <br>

				<div class="linha"></div>
				<br>
				<table>
					<tr>
						<td width="25"><img src="Imagens/adicionar.png"></td>
						<td><a href="userUpdate.html?comando=inserir">Inserir
								novo professor </a></td>
					</tr>
				</table>
			</div>
		</article>
		<footer>
			<div class="line"></div>
			<p>Copyright 2013 - Rafael Fogel</p>
			<a href="main.html" class="up">Inicio</a>
		</footer>

	</section>

</body>
</html>