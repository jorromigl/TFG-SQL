<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<form:form action="summary/coach/edit.do" modelAttribute="summary">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="match" />
	
	<tag:textbox code="summary.subject" path="subject" />
	<tag:textarea code="summary.text" path="text" />	
	
	<tag:submit code="summary.save" name="save" />
	<%-- <jstl:if test="${match.id != 0}">
		<tag:submit code="match.delete" name="delete" />
	</jstl:if> --%>
		
	<tag:cancel code="summary.cancel" url="match/coach/listPast.do" />
</form:form>
