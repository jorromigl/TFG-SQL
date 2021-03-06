<?xml version="1.0" encoding="UTF-8" ?>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>


<div class="x_panel">
	<div class="x_content">
		<br>
		<form:form action="squadra/coach/edit.do" modelAttribute="squadra"
			enctype="multipart/form-data" id="demo-form2"
			data-parsley-validate="" class="form-horizontal form-label-left">

			<form:hidden path="id" />
			<form:hidden path="version" />

			<jstl:if test="${details==false}">

				<tag:textbox2 code="squadra.category.cname" path="category.cname" />
				<tag:textbox2 code="squadra.name" path="name" />
				<div class="ln_solid"></div>
				<div class="form-group">
					<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
						<tag:submitverde code="squadra.save" name="save" />
						<tag:buttonazul code="squadra.cancel" url="principal/index.do'" />
					</div>
				</div>
			</jstl:if>

			<jstl:if test="${details==true}">

				<b><spring:message code="squadra.details" /></b>
				<br />

				<ul>
					<li><b><spring:message code="squadra.category.cname" /></b> <jstl:out
							value="${squadra.category.cname}"></jstl:out></li>
					<li><b><spring:message code="squadra.name" /></b> <jstl:out
							value="${squadra.name}"></jstl:out></li>
				</ul>

				<security:authorize access="hasRole('PLAYER')">

					<a
						href="squadra/player/listPlayersSquadra.do?squadraId=${squadra.id}"><spring:message
							code="players.squadra" /></a>

					<br>
					<a
						href="squadra/player/viewCoachSquadra.do?squadraId=${squadra.id}"><spring:message
							code="coach.squadra" /></a>
							
				

				</security:authorize>

				<security:authorize access="hasRole('FAMILY')">

					<a
						href="squadra/family/listPlayersSquadra.do?squadraId=${squadra.id}"><spring:message
							code="players.squadra" /></a>

					<br>
					<a
						href="squadra/family/viewCoachSquadra.do?squadraId=${squadra.id}"><spring:message
							code="coach.squadra" /></a>

				</security:authorize>
				<br>
			<br>
				<%-- 	<tag:select code="squadra.players.add" path="player"  id="player" items="players" itemLabel="name"/> --%>
				<tag:buttonazul code="squadra.return" url="principal/index.do'" />
			</jstl:if>
			<br>
			<br>
		

		</form:form>

	</div>
</div>

