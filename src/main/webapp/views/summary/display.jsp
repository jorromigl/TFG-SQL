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

<jstl:if test="${detailsSummary==true}">
	<form:form action="${requestURI}" method="post"
		modelAttribute="summary">

		<form:hidden path="id" />
		<form:hidden path="version" />

		<tag:textbox code="display.text" path="text" readonly="true" />
		<tag:textbox code="display.subject" path="subject" readonly="true" />
		
		<security:authorize access="hasRole('COACH')">
		<tag:button code="summary.edit" url="summary/coach/edit.do?summaryId=${summary.id}'" />
		<tag:button code="display.return" url="match/coach/listPast.do'" />
		</security:authorize>
		<security:authorize access="hasRole('PLAYER')">
		<tag:button code="display.return" url="match/player/listPast.do'" />
		</security:authorize>
	</form:form>
</jstl:if>