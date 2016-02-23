<?xml version="1.0" encoding="UTF-8" ?>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<display:table name="squadras" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">


	<spring:message code="squadra.name" var="name" />
	<display:column href="squadra/coach/details.do?squadraId=${row.id}" property="name" title="${name}" sortable="${true}" />

	<display:column >
		<a href="player/c/listPlayersSquadra.do?squadraId=${row.id}" ><spring:message code="squadra.players" /></a>
	</display:column>
	<display:column >
		<a href="player/c/findInItsCategoryAndNotHaveSquadra.do?squadraId=${row.id}" ><spring:message code="squadra.players.add" /></a>
	</display:column>
</display:table>
	
	<tag:button code="squadra.return" url="principal/index.do'" />