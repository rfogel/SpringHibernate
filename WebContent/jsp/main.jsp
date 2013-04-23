<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Página Inicial</title>

<link rel="stylesheet" type="text/css" href="estilo.css" />


<!-- Internet Explorer HTML5 enabling code: -->
<!--[if IE]>
        <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <style type="text/css">
        .clear {
          zoom: 1;
          display: block;
        }
        </style>
        <![endif]-->

</head>

<body>

	<section id="page">

		<header>
			
			<%@ include file="header.jsp" %>

		</header>

		<section id="articles">

			<div class="line"></div>

			<table style="float: right">
				<tr>
					<td width="10" bgcolor="yellow"></td>
					<td>&nbsp;<spring:message code="label.pending"/>&nbsp;</td>
					<td width="10" bgcolor="green"></td>
					<td>&nbsp;<spring:message code="label.forward"/>&nbsp;</td>
					<td width="10" bgcolor="gray"></td>
					<td>&nbsp;<spring:message code="label.analazing"/>&nbsp;</td>
					<td width="10" bgcolor="orange"></td>
					<td>&nbsp;<spring:message code="label.defined"/>&nbsp;</td>
					<td width="10" bgcolor="red"></td>
					<td>&nbsp;<spring:message code="label.rejected"/>&nbsp;</td>
					<td width="10" bgcolor="blue"></td>
					<td>&nbsp;<spring:message code="label.aproved"/>&nbsp;</td>
				</tr>
			</table>
			<br>

			<article id="professor">
				<h3><spring:message code="label.professor"/></h3>

				<div class="line"></div>

				<div class="articleBody clear">

					<c:choose>

						<c:when test="${requestScope.cotaEsgotada != true}">
							<li>Ações:
							<li style="margin-left: 1em"><a style="font-style: italic"
								href="order.html">Submeter Pedido</a></li>
							<br>
							</li>
						</c:when>

						<c:when test="${requestScope.cotaEsgotada == true}">
							<li>Ações:
							<li style="margin-left: 1em"><i>Pedidos Esgotados</i></li>
							<br>
							</li>
						</c:when>

					</c:choose>
					<li>Pedidos Submetidos: <c:choose>
							<c:when test="${requestScope.listOrderByUser == null}">
								<li style="margin-left: 1em"><i>Nenhum pedido
										realizado!</i></li>
							</c:when>
							<c:when test="${requestScope.listOrderByUser != null}">
								<c:forEach items="${requestScope.listOrderByUserPendente}"
									var="lisPed">
									<li style="margin-left: 1em"><a style="color: yellow"
										href='evaluateOrder.html?comando=abrir&id=<c:out value="${lisPed.id}" />'>
											<c:out
												value="${lisPed.nomeArtigo} (${lisPed.nomeConferencia})" />
									</a></li>
								</c:forEach>
								<c:forEach items="${requestScope.listOrderByUserAvaliado}"
									var="lisPed">
									<li style="margin-left: 1em"><a style="color: lime"
										href='evaluateOrder.html?comando=abrir&id=<c:out value="${lisPed.id}" />'>
											<c:out
												value="${lisPed.nomeArtigo} (${lisPed.nomeConferencia})" />
									</a></li>
								</c:forEach>
								<c:forEach items="${requestScope.listOrderByUserEncaminhado}"
									var="lisPed">
									<li style="margin-left: 1em"><a style="color: royalblue"
										href='evaluateOrder.html?comando=abrir&id=<c:out value="${lisPed.id}" />'>
											<c:out
												value="${lisPed.nomeArtigo} (${lisPed.nomeConferencia})" />
									</a></li>
								</c:forEach>
								<c:forEach items="${requestScope.listOrderByUserEncerrado}"
									var="lisPed">
									<li style="margin-left: 1em"><a style="color: #ff0000"
										" href='evaluateOrder.html?comando=abrir&id=<c:out value="${lisPed.id}" />'>
											<c:out
												value="${lisPed.nomeArtigo} (${lisPed.nomeConferencia})" />
									</a></li>
								</c:forEach>
								<c:forEach items="${requestScope.listOrderByUserEmAprovacao}"
									var="lisPed">
									<li style="margin-left: 1em"><a style="color: #BCBCBC"
										href='evaluateOrder.html?comando=abrir&id=<c:out value="${lisPed.id}" />'>
											<c:out
												value="${lisPed.nomeArtigo} (${lisPed.nomeConferencia})" />
									</a></li>
								</c:forEach>
								<c:forEach items="${requestScope.listOrderByUserDefinido}"
									var="lisPed">
									<li style="margin-left: 1em"><a style="color: darkorange"
										href='evaluateOrder.html?comando=abrir&id=<c:out value="${lisPed.id}" />'>
											<c:out
												value="${lisPed.nomeArtigo} (${lisPed.nomeConferencia})" />
									</a></li>
								</c:forEach>
							</c:when>
						</c:choose>
					</li>

				</div>
			</article>

			<c:choose>
				<c:when test="${sessionScope.usertypeId == 2}">
					<article id="membro1">
						<h3><spring:message code="label.firstmember"/></h3>

						<div class="line"></div>

						<div class="articleBody clear">

							<li>Lista de Pedidos: <c:choose>
									<c:when test="${requestScope.listOrderComissaoVazia == true}">
										<li style="margin-left: 1em"><i>Nenhum pedido para
												ser avaliado!</i></li>
									</c:when>
									<c:when test="${requestScope.listOrderComissaoVazia != true}">
										<c:forEach items="${requestScope.listOrderAvaliado}"
											var="lisPed">
											<li style="margin-left: 1em"><a style="color: lime"
												href='evaluateOrder.html?comando=avaliar&id=<c:out value="${lisPed.id}" />'>
													<c:out
														value="${lisPed.nomeArtigo} (${lisPed.nomeConferencia}, ${lisPed.autor})" />
											</a></li>
										</c:forEach>

										<c:forEach items="${requestScope.listOrderEmAprovacao}"
											var="lisPed">
											<li style="margin-left: 1em"><a style="color: #BCBCBC"
												href='evaluateOrder.html?comando=avaliar&id=<c:out value="${lisPed.id}" />'>
													<c:out
														value="${lisPed.nomeArtigo} (${lisPed.nomeConferencia}, ${lisPed.autor})" />
											</a></li>
										</c:forEach>

										<c:forEach items="${requestScope.listOrderDefinidos}"
											var="lisPed">
											<li style="margin-left: 1em"><a
												style="color: darkorange"
												href='evaluateOrder.html?comando=avaliar&id=<c:out value="${lisPed.id}" />'>
													<c:out
														value="${lisPed.nomeArtigo} (${lisPed.nomeConferencia}, ${lisPed.autor})" />
											</a></li>
										</c:forEach>
									</c:when>
								</c:choose>
							</li>

						</div>
					</article>
				</c:when>
				<c:when test="${sessionScope.usertypeId == 3}">
					<article id="membro2">
						<h3><spring:message code="label.secondmember"/></h3>

						<div class="line"></div>

						<div class="articleBody clear">

							<li>Lista de Pedidos: <c:choose>
									<c:when test="${requestScope.listOrderComissaoVazia == true}">
										<li style="margin-left: 1em"><i>Nenhum pedido para
												ser avaliado!</i></li>
									</c:when>
									<c:when test="${requestScope.listOrderComissaoVazia != true}">
										<c:forEach items="${requestScope.listOrderAvaliado}"
											var="lisPed">
											<li style="margin-left: 1em"><a style="color: lime"
												href='evaluateOrder.html?comando=avaliar&id=<c:out value="${lisPed.id}" />'>
													<c:out
														value="${lisPed.nomeArtigo} (${lisPed.nomeConferencia}, ${lisPed.autor})" />
											</a></li>
										</c:forEach>

										<c:forEach items="${requestScope.listOrderEmAprovacao}"
											var="lisPed">
											<li style="margin-left: 1em"><a style="color: #BCBCBC"
												href='evaluateOrder.html?comando=avaliar&id=<c:out value="${lisPed.id}" />'>
													<c:out
														value="${lisPed.nomeArtigo} (${lisPed.nomeConferencia}, ${lisPed.autor})" />
											</a></li>
										</c:forEach>

										<c:forEach items="${requestScope.listOrderDefinidos}"
											var="lisPed">
											<li style="margin-left: 1em"><a
												style="color: darkorange"
												href='evaluateOrder.html?comando=avaliar&id=<c:out value="${lisPed.id}" />'>
													<c:out
														value="${lisPed.nomeArtigo} (${lisPed.nomeConferencia}, ${lisPed.autor})" />
											</a></li>
										</c:forEach>
									</c:when>
								</c:choose>
							</li>

						</div>
					</article>
				</c:when>
				<c:when test="${sessionScope.usertypeId == 4}">
					<article id="presidente">
						<h3><spring:message code="label.president"/></h3>

						<div class="line"></div>

						<div class="articleBody clear">

							<li>Ações:
							<li style="margin-left: 1em"><a style="font-style: italic"
								href="configuration.html">Configuração da Comissão</a></li>
							<li style="margin-left: 1em"><a style="font-style: italic"
								href="userManager.html">Manutenção dos Professores</a></li>
							</li> <br>


							<li>Lista de Pedidos: <c:choose>
									<c:when test="${requestScope.listOrderPresidenteVazia == true}">
										<li style="margin-left: 1em"><i>Nenhum pedido para
												ser avaliado!</i></li>
									</c:when>
									<c:when test="${requestScope.listOrderPresidenteVazia != true}">
										<c:forEach items="${requestScope.listOrderPendente}"
											var="lisPed">
											<li style="margin-left: 1em"><a style="color: yellow"
												href='evaluateOrder.html?comando=avaliar&id=<c:out value="${lisPed.id}" />'>
													<c:out
														value="${lisPed.nomeArtigo} (${lisPed.nomeConferencia}, ${lisPed.autor})" />
											</a></li>
										</c:forEach>

										<c:forEach items="${requestScope.listOrderAvaliado}"
											var="lisPed">
											<li style="margin-left: 1em"><a style="color: lime"
												href='evaluateOrder.html?comando=abrir&id=<c:out value="${lisPed.id}" />'>
													<c:out
														value="${lisPed.nomeArtigo} (${lisPed.nomeConferencia}, ${lisPed.autor})" />
											</a></li>
										</c:forEach>

										<c:forEach items="${requestScope.listOrderEncaminhado}"
											var="lisPed">
											<li style="margin-left: 1em"><a style="color: royalblue"
												href='evaluateOrder.html?comando=relatorio&id=<c:out value="${lisPed.id}" />'>
													<c:out
														value="${lisPed.nomeArtigo} (${lisPed.nomeConferencia}, ${lisPed.autor})" />
											</a></li>
										</c:forEach>

										<c:forEach items="${requestScope.listOrderEncerrado}"
											var="lisPed">
											<li style="margin-left: 1em"><a style="color: #ff0000"
												href='evaluateOrder.html?comando=abrir&id=<c:out value="${lisPed.id}" />'>
													<c:out
														value="${lisPed.nomeArtigo} (${lisPed.nomeConferencia}, ${lisPed.autor})" />
											</a></li>
										</c:forEach>

										<c:forEach items="${requestScope.listOrderEmAprovacao}"
											var="lisPed">
											<li style="margin-left: 1em"><a style="color: #BCBCBC"
												href='evaluateOrder.html?comando=abrir&id=<c:out value="${lisPed.id}" />'>
													<c:out
														value="${lisPed.nomeArtigo} (${lisPed.nomeConferencia}, ${lisPed.autor})" />
											</a></li>
										</c:forEach>

										<c:forEach items="${requestScope.listOrderDefinidos}"
											var="lisPed">
											<c:if test="${lisPed.status == 9}">
												<li style="margin-left: 1em"><a
													style="color: darkorange"
													href='evaluateOrder.html?comando=definirA&id=<c:out value="${lisPed.id}" />'>
														<c:out
															value="${lisPed.nomeArtigo} (${lisPed.nomeConferencia}, ${lisPed.autor})" />
												</a></li>
											</c:if>
											<c:if test="${lisPed.status == 12}">
												<li style="margin-left: 1em"><a
													style="color: darkorange"
													href='evaluateOrder.html?comando=definirR&id=<c:out value="${lisPed.id}" />'>
														<c:out
															value="${lisPed.nomeArtigo} (${lisPed.nomeConferencia}, ${lisPed.autor})" />
												</a></li>
											</c:if>
										</c:forEach>
									</c:when>
								</c:choose>
							</li>
						</div>
					</article>
				</c:when>
			</c:choose>
			<footer>
				<div class="line"></div>
				<p>Copyright 2013 - Rafael Fogel</p>
				<a href="#" class="up">Subir</a>
			</footer>

		</section>

		<!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
        <script src="jquery.scrollTo-1.4.2/jquery.scrollTo-min.js"></script>
        <script src="script.js"></script>-->
</body>
</html>