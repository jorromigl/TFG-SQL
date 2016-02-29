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

<form:form action="${requestURI}" modelAttribute="coach">

	<form:hidden path="id" />
	<form:hidden path="version" />


		<ul>
			<li><b><spring:message code="coach.name" /></b> <jstl:out
					value="${name}"></jstl:out></li>
			<li><b><spring:message code="coach.surname" /></b> <jstl:out
					value="${surname}"></jstl:out></li>
		</ul>

	
	<br>
	<br>
	<acme:cancel code="coach.return" url="principal/index.do" />

</form:form>