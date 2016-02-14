<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<display:table name="comments" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="comment.subject" var="subject" />
	<display:column property="subject" title="${subject}" sortable="${true}" />
	
	<spring:message code="comment.text" var="text" />
	<display:column property="text" title="${text}" />
	
	<spring:message code="comment.user" var="fullName" />
	<display:column property="user.fullName" title="${fullName}" />
	
	<spring:message code="comment.moment" var="moment" />
	<display:column property="moment" title="${moment}"  />

	<display:column >

		<a href="comment/display.do?commentId=${row.id}" ><spring:message code="comment.display" /></a>

	</display:column>
	
			
</display:table>
	
	<tag:button code="comment.return" url="principal/index.do'" />

