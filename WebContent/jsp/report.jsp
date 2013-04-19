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
        <title>Relatório</title>
		<style type="text/css">
		body {
			color: black;
			background-color: white;
			font-size:0.825em;
			font-family:Arial, Helvetica, sans-serif;}
                article{
                        background-color:white;
                        margin:1em 0;
                        padding:60px;}
                h3{
                    font-family:forte,"Myriad Pro","Helvetica Neue",Helvetica,Arial,Sans-Serif;
                    font-size:2em;
                    font-weight:normal;
                    margin:0 0 1em;}
		</style>
    </head>
	<body>
        <article>
              <br><br>
		<h3><center>Avaliação de Mérito Acadêmico</center></h3>
                <br><br>
                <p>Considerando a avaliação do(s) quesito(s) abaixo relacionados, a Comissão de Avaliação
                      de Mérito resolve atribuir a categoria <c:out value="${requestScope.qualificacao}"/> ao
                      trabalho intitulado <c:out value="${requestScope.nomeArtigo}"/>, a ser apresentado no
                      evento <c:out value="${requestScope.nomeConferencia}"/> pelo(a) professor(a)
                      <c:out value="${requestScope.autor}"/> ou aluno(a) designado(a). </p>
                <br><br>
                <table style="line-height:2.5em">
                    <tr>
                        <td width="200">Cota:</td>
                        <td><c:out value="${requestScope.cota}"/></td>
                    </tr>
                    <tr>
                        <td width="200">Nome do artigo:</td>
                        <td><c:out value="${requestScope.nomeArtigo}"/></td>
                    </tr>
                    <tr>
                        <td width="200">Nome da conferência:</td>
                        <td><c:out value="${requestScope.nomeConferencia}"/></td>
                    </tr>
                    <tr>
                        <td width="200">Qualificação da conferência:</td>
                        <td><c:out value="${requestScope.qualificacao}"/></td>
                    </tr>
                    <tr>
                        <td width="200">Pedido de financiamento:</td>
                        <td><c:out value="${requestScope.financiamento}"/></td>
                    </tr>
                    <tr>
                        <td width="200">Número de artigos:</td>
                        <td><c:out value="${requestScope.numeroArtigos}"/></td>
                    </tr>
                    <tr>
                        <td width="200">Categoria:</td>
                        <td><c:out value="${requestScope.conceito}"/></td>
                    </tr>
                    <tr>
                        <td width="200">Valor proposto:</td>
                        <td><c:out value="${requestScope.moeda}"/>&nbsp;<c:out value="${requestScope.valorSugerido}"/></td>
                    </tr>
                </table><br><br>
                
                <p>Rio de Janeiro, <c:out value="${requestScope.dataHora}"/></p><br><br><br>
                    
               
                <center>_____________________________________________________</center><br>
                <center>Representante da Comissão de Mérito Acadêmico</center>
        </article>
    </body>
</html>
