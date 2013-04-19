<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:choose>
	<c:when test="${sessionScope.username == null}">
		<% response.sendRedirect("Login.html"); %>
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

<!DOCTYPE html> <!-- The new doctype -->
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Manutenção dos Professores</title>
		<link rel="stylesheet" type="text/css" href="estilo.css" />
		<style type="text/css">
		body {
			color: white;
			background-color: black;
			font-size:0.825em;
			font-family:Arial, Helvetica, sans-serif;}
                .error {
                        color: #ff0000;}
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
		<article id="pedido">
		<h3>Submissão de Pedido</h3>
                 <div class="line"></div>
			<form:form commandName="pedidoData" method="POST">
                            <input type="hidden" name="comando" value="${param.comando}" />
                            <table>
                                  <tr>
                                    <td>Cota:</td>
                                    <td><form:select path="cota">
                                          <form:option value="NONE" label="--- Selecione ---"/>
                                          <form:options items="${listaCota}" />
                                          </form:select>
                                    </td>
                                    <td><form:errors path="cota" cssClass="error" /></td>
                                </tr>
                                <tr>
                                    <td>Nome do Artigo:</td>
                                    <td><form:input path="nomeArtigo"/></td>
                                    <td><form:errors path="nomeArtigo" cssClass="error" /></td>
                                </tr>
                                <tr>
                                    <td>Nome da conferência:</td>
                                    <td><form:input path="nomeConferencia"/></td>
                                    <td><form:errors path="nomeConferencia" cssClass="error" /></td>
                                </tr>
                                <tr>
                                    <td>Qualificação da Conferência:</td>
                                    <td><form:select path="qualificacaoConferencia">
                                          <form:option value="NONE" label="--- Selecione ---"/>
                                          <form:options items="${listaQualificacao}" />
                                          </form:select>
                                    </td>
                                    <td><form:errors path="qualificacaoConferencia" cssClass="error" /></td>
                                </tr>
                                <tr>
                                    <td>Pedido de financiamento:</td>
                                    <td><form:select path="financiamento">
                                          <form:option value="NONE" label="--- Selecione ---"/>
                                          <form:options items="${listaFinanciamento}" />
                                          </form:select>
                                    </td>
                                    <td><form:errors path="financiamento" cssClass="error" /></td>
                                </tr>
                                <tr>
                                    <td>Número de Artigos:</td>
                                    <td><form:input path="numeroArtigos"/></td>
                                    <td><form:errors path="numeroArtigos" cssClass="error" /></td>
                                </tr>
                            </table><br>
                            <input type="image" src="Imagens/submeterpedido.gif" value="submit"/>
                        </form:form>
                </article>
		<footer> 

                    <div class="line"></div>
                    <p>Copyright 2010 - Rafael Fogel</p> 
                    <a href="TemplateForm.html" class="up">Voltar</a> 

               </footer>

		</section>

    </body>
</html>
