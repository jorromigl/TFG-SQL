<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<display:table name="recruitments" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="recruitment.match.rival" var="rival" />
	<display:column property="match.rival" title="${rival}" sortable="${true}" />
	
	<spring:message code="recruitment.match.location" var="location" />
	<display:column property="match.location" title="${location}" />
	
	<spring:message code="recruitment.match.moment" var="moment" />
	<display:column property="match.moment" title="${moment}"  />
	
	<display:column >
		<a href="player/c/listPlayersByRecruitment.do?recruitmentId=${row.id}" ><spring:message code="recruitment.player" /></a>
	</display:column>
	<jstl:if test="${isFuture==true}">
		<display:column >
		<a href="player/c/listPlayersSquadra.do?scuadraId=${squadraId}" ><spring:message code="player.addRecreutment" /></a>
	</display:column>
	</jstl:if>
	
	
			
</display:table>
	
	<tag:button code="recruitment.return" url="principal/index.do'" />

