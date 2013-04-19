<%@page contentType="text/html"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

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
<title>Login</title>
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
</head>

<body>
	<section id="pagelogin">

		<br>
		<br>
		<article id="login">
			<img style="float: right" src="Imagens/tranca.png">
			<h3>Autenticação</h3>

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
				<table>
					<tr>
						<td width="55">Usuário:</td>
						<td><form:input path="username" /></td>
						<td><form:errors path="username" cssClass="error" /></td>
					</tr>
					<tr>
						<td width="55">Senha:</td>
						<td><form:input type="password" path="password" /></td>
						<td><form:errors path="password" cssClass="error" /></td>
					</tr>
				</table>
				<br>
				<center>
					<input type="image" src="Imagens/entrar.png" value="submit" />
				</center>
			</form:form>
		</article>
		<footer>
			<div class="line"></div>
			<p>Copyright 2010 - Rafael Fogel</p>
		</footer>
	</section>

</body>
</html>
