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
<display:table name="recruitments" id="row" requestURI="${requestURI}"
	pagesize="5" class="table table-hover">

	<spring:message code="recruitment.match.rival" var="rival" />
	<display:column property="match.rival" title="${rival}"
		sortable="${true}" />

	<spring:message code="recruitment.match.location" var="location" />
	<display:column property="match.location" title="${location}" />
	
	<spring:message code="recruitment.match.squadra" var="name" />
	<display:column property="match.squadra.name" title="${name}" />
	
	<spring:message code="recruitment.match.moment" var="moment" />
	<display:column property="match.moment" title="${moment}" />

	<security:authorize access="hasRole('COACH')">
	
	<jstl:if test="${isFuture==false}">
		<display:column>
			<a href="player/c/listPlayersByRecruitmentPast.do?recruitmentId=${row.id}"><spring:message
					code="recruitment.player" /></a>
		</display:column>
	</jstl:if>
		
		<jstl:if test="${isFuture==true}">
			<display:column>
				<a href="player/c/listPlayersSquadraToRecruit.do?squadraId=${row.match.squadra.id}&recruitmentId=${row.id}"><spring:message
						code="recruitment.addPlayer" /></a>
			</display:column>
			
			<display:column>
			<a href="player/c/listPlayersByRecruitmentFuture.do?recruitmentId=${row.id}"><spring:message
					code="recruitment.player" /></a>
		</display:column>

		</jstl:if>
	</security:authorize>


	<security:authorize access="hasRole('PLAYER')">
		<display:column>
			<a href="player/listPlayersByRecruitment.do?recruitmentId=${row.id}"><spring:message
					code="recruitment.player" /></a>
		</display:column>

	</security:authorize>
	
	<security:authorize access="hasRole('FAMILY')">

		<display:column>
			<a href="player/f/listPlayersByRecruitment.do?recruitmentId=${row.id}"><spring:message
					code="recruitment.player" /></a>
		</display:column>

	</security:authorize>



</display:table>
</div>
<tag:buttonverde code="recruitment.return" url="principal/index.do'" />

