<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="recruitment/coach/edit.do"
	modelAttribute="recruitment">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="match" />
	<form:hidden path="players" />

	
		<spring:message code="addPlayers.Recruitment" />
		<br>
		<br>
		

	<acme:submit code="recruitment.save" name="save" />
	<acme:cancel code="recruitment.cancel" url="/match/listFuture.do" />

</form:form>