<%@page contentType="text/html"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:choose>
	<c:when test="${sessionScope.username == null}">
		<% response.sendRedirect("login.html"); %>
	</c:when>
	<c:otherwise>
		<c:set var="USERNAME" value="${sessionScope.username}" scope="session" />
	</c:otherwise>
</c:choose>
<%-- 
    Document   : ConfiguracaoForm
    Created on : 24/11/2010, 21:18:55
    Author     : Fogel
--%>

<!DOCTYPE html> <!-- The new doctype -->
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Configuração da Comissão</title>
		<link rel="stylesheet" type="text/css" href="estilo.css" />
		<style type="text/css">
		body {
			color: white;
			background-color: black;
			font-size:0.825em;
			font-family:Arial, Helvetica, sans-serif;}
                .error {
                      color:yellow;}

                .errorblock{
                    color: #000;
                    background-color: #ffEEEE;
                    border: 3px solid #ff0000;
                    padding:8px;
                    margin:16px;}

		</style>
                <section id="page">

                <hgroup>
                    <h1>Home Page</h1>
                    <h5>Olá, <c:out value="${USERNAME}" /></h5>
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
          
		<article id="configuracao">
		<h3>Configuração da Comissão de Mérito</h3>
                 <div class="line"></div>
			<form:form commandName="comissao" method="POST">
                            <table>
                                <tr>
                                    <td width="150">Login do Presidente:</td>
                                    <td><form:select path="loginPresidente">
                                          <form:option value="NONE" label="--- Selecione ---"/>
                                          <form:options items="${listaLogin}" />
                                          </form:select>
                                    </td>
                                    <td><form:errors path="loginPresidente" cssClass="error" /></td>
                                </tr>
                                <tr>
                                    <td width="150">Login do 1º Membro:</td>
                                    <td><form:select path="loginPrimeiroMembro">
                                          <form:option value="NONE" label="--- Selecione ---"/>
                                          <form:options items="${listaLogin}" />
                                          </form:select>
                                    </td>
                                    <td><form:errors path="loginPrimeiroMembro" cssClass="error" /></td>
                                </tr>
                                <tr>
                                    <td width=150">Login do 2º Membro:</td>
                                    <td><form:select path="loginSegundoMembro">
                                          <form:option value="NONE" label="--- Selecione ---"/>
                                          <form:options items="${listaLogin}" />
                                          </form:select>
                                    </td>
                                    <td><form:errors path="loginSegundoMembro" cssClass="error" /></td>
                                </tr>
                            </table><br>
                            <input type="image" src="Imagens/salvar.png" value="submit"/>
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
