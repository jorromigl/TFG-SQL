<?xml version="1.0" encoding="UTF-8" ?>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<div class="x_panel">
<display:table name="players" id="row" requestURI="${requestURI}" pagesize="5" class="table table-hover">

	<spring:message code="player.name" var="name" />
	<display:column property="name" title="${name}" />
	
	<spring:message code="player.surname" var="surname" />
	<display:column property="surname" title="${surname}" />
	
	<spring:message code="player.category" var="cname" />
	<display:column property="category.cname" title="${cname}" />
	
	<jstl:if test="${row.file == null }">
	<display:column>
		<spring:message code = "player.notImage"></spring:message>
	</display:column>
	</jstl:if>
	
	<jstl:if test="${row.file != null }">
	<display:column>
		<img style="width: 50px; height: 50px;" src="player/showImage.do?playerId=${row.id}"/>
	</display:column>
	</jstl:if>
	
	
	<security:authorize access="hasRole('PLAYER')">
	<display:column >
		<a href="player/verPerfilJugador.do?playerId=${row.id}" ><spring:message code="player.profile" /></a>
	</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('COACH')">
	<display:column >
		<a href="player/c/verPerfilJugador.do?playerId=${row.id}" ><spring:message code="player.profile" /></a>
	</display:column>
	<jstl:if test="${mysquadra==false}">
	<display:column >
		<a href="player/c/AddPlayers.do?playerId=${row.id}&squadraId=${squadraId}" ><spring:message code="player.addSquadra" /></a>
	</display:column>
	</jstl:if>
	<jstl:if test="${mysquadra==true}">
		<a href="player/c/listPlayersSquadra" ><spring:message code="player.mysquadra" /></a>
	</jstl:if>
	<jstl:if test="${recruit==true}">
	<display:column >
		<a href="player/c/AddPlayersRecruitment.do?playerId=${row.id}&recruitmentId=${recruitmentId}" ><spring:message code="player.addRecruitment" /></a>
	</display:column>
	</jstl:if>
	<jstl:if test="${recruitment==true}">
	<display:column >
		<a href="player/c/deletePlayerRecruitment.do?playerId=${row.id}&recruitmentId=${recruitmentId}" ><spring:message code="player.deletePlayerRecruitment" /></a>
	</display:column>
	</jstl:if>
	</security:authorize>
	<security:authorize access="hasRole('FAMILY')">
	<display:column >
		<a href="player/f/verPerfilJugador.do?playerId=${row.id}" ><spring:message code="player.profile" /></a>
	</display:column>
	</security:authorize>
</display:table>
</div>	
	<tag:buttonverde code="match.return" url="principal/index.do'" />