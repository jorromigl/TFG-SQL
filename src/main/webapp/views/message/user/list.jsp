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
<display:table pagesize="5" class="table table-hover" name="folder.messages"
	requestURI="${requestURI}" id="row">

	<!-- Attributes -->

	<spring:message code="msg.moment" var="moment" />
	<display:column property="moment" title="${moment}" sortable="true"
		format="{0,date,dd/MM/yyyy HH:mm}" />

	<spring:message code="msg.sender" var="sender" />
	<display:column property="sender.name" title="${sender}"
		sortable="true" />

	<spring:message code="msg.recipient" var="recipient" />
	<display:column property="recipient.name" title="${recipient}"
		sortable="true" />

	<spring:message code="msg.subject" var="subject" />
	<display:column property="subject" title="${subject}" sortable="false" />

	<!-- Action links -->

	<display:column>
		<a href="message/user/display.do?messageId=${row.id}"><spring:message
				code="message.display" /></a>
	</display:column>

</display:table>
</div>



	
		<tag:buttonverde code="message.return" url="folder/user/list.do'" />
