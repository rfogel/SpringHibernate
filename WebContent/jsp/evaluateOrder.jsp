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
    Document   : PedidoForm
    Created on : 25/11/2010, 11:24:23
    Author     : Fogel
--%>

<!DOCTYPE html> <!-- The new doctype -->
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Pedidos</title>
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
		<article id="avaliarpedido">
		<h3>Informações do Pedido</h3>
                 <div class="line"></div>
			<form:form commandName="pedido" method="POST">
                            <input type="hidden" name="comando" value="${param.comando}" />
                            <table>
                                <tr>
                                    <td width="200">Cota: </td>
                                    <td><form:input size="60" path="cota" disabled="true"/></td>
                                </tr>
                                <tr>
                                    <td width="200">Nome do Artigo: </td>
                                    <td><form:input size="60" path="nomeArtigo" disabled="true"/></td>
                                </tr>
                                <tr>
                                    <td width="200">Nome da conferência: </td>
                                    <td><form:input size="60" path="nomeConferencia" disabled="true"/></td>
                                </tr>
                                <tr>
                                      <td width="200">Qualificação da Conferência: </td>
                                    <td><form:input size="60" path="qualificacaoConferencia" disabled="true"/></td>
                                </tr>
                                <tr>
                                    <td width="200">Pedido de financiamento: </td>
                                    <td><form:input size="60" path="financiamento" disabled="true"/></td>
                                </tr>
                                <tr>
                                    <td width="200">Número de Artigos: </td>
                                    <td><form:input size="1" path="numeroArtigos" disabled="true"/></td>
                                </tr>
                                <tr>
                                    <td width="200">Autor(a): </td>
                                    <td><form:input size="60" path="autor" disabled="true"/></td>
                                </tr>
                                <tr>
                                    <td width="200">Status: </td>
                                    <td><form:input size="60" path="statusString" disabled="true"/></td>
                                </tr>
                                
                            </table><br>
                              <c:if test="${param.comando == 'avaliar'}">
                                    <input type="image" src="Imagens/aceitarPedido.png" name="aceitar" value="submit"/>
                                    <input type="image" src="Imagens/rejeitarPedido.png" name="rejeitar" value="submit"/>
                              </c:if>
                              <c:if test="${param.comando == 'definirA'}">
                                    <center><input type="image" src="Imagens/aceitarPedido.png" name="aceitar" value="submit"/></center>
                              </c:if>
                              <c:if test="${param.comando == 'definirR'}">
                                    <center><input type="image" src="Imagens/rejeitarPedido.png" name="rejeitar" value="submit"/></center>
                              </c:if>
                              <c:if test="${param.comando == 'relatorio'}">
                                    <input type="image" target="_blank" src="Imagens/emitirRelatorio.png" name="relatorio" value="submit"/>
                              </c:if>
                              <c:if test="${param.comando == 'abrir'}">
                                    <c:if test="${pedido.status == 1}">
                                          <input type="image" target="_blank" src="Imagens/deletarPedido.png" name="remover" value="submit"/>
                                    </c:if>
                              </c:if>
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
