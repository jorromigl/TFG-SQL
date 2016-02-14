<?xml version="1.0" encoding="UTF-8" ?>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<form:form action="${requestURI}" method="post"  modelAttribute="comment">
		
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="moment" />
	
		<tag:textbox code="comment.text" path="text" readonly="true" />
		<tag:textbox code="comment.subject" path="subject" readonly="true"/>
	
	
	<tag:button code="display.return" url="match/listAll.do'" />
	<security:authorize access="hasRole('ADMIN')">
	
		<a href="comment/admin/delete.do?commentId=${comment.id}" ><spring:message code="comment.delete" /></a>

	</security:authorize>
</form:form>

