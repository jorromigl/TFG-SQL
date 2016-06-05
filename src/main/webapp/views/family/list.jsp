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
<display:table name="families" id="row" requestURI="${requestURI}" pagesize="5" class="table table-hover">
	
	<spring:message code="family.name" var="name" />
	<display:column property="name" title="${name}" sortable="${true}" />
	
	<spring:message code="family.surname" var="surname" />
	<display:column property="surname" title="${surname}" sortable="${true}" />
	
	<security:authorize access="hasRole('PLAYER')">
	<display:column >
		<a href="familiers/player/verPerfilFamily.do?familyId=${row.id}" ><spring:message code="family.profile" /></a>
	</display:column>
	</security:authorize>
</display:table>
</div>
	<tag:buttonazul code="family.return" url="principal/index.do'" />

