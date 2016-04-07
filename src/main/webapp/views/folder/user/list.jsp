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
<display:table name="folders" id="row" requestURI="${requestURI}" pagesize="5" class="table table-hover">

	<spring:message code="folder.name" var="name" />
	<display:column property="name" title="${name}" sortable="${true}" />
	
	<spring:message code="folder.numberMessages" var="numberMessages" />
	<display:column title="${numberMessages}">
		<jstl:out value="${row.messages.size()}"></jstl:out>
	</display:column>

	<display:column>
		<a href="message/user/list.do?folderId=${row.id}" ><spring:message code="folder.messages" /></a>
	</display:column>
	
	
			
</display:table>
</div>
	
	<tag:buttonverde code="folder.return" url="principal/index.do'" />

