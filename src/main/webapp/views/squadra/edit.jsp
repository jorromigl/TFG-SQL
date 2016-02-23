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

<form:form action="${requestURI}" modelAttribute="squadra">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<jstl:if test="${details==false}">
	
		<tag:textbox code="squadra.category.cname" path="category.cname" />
		<tag:textbox code="squadra.name" path="name" />

		<acme:submit code="squadra.save" name="save" />
	</jstl:if>

	<jstl:if test="${details==true}">

		<b><spring:message code="squadra.details" /></b>
		<br />

		<ul>
			<li><b><spring:message code="squadra.category.cname" /></b> <jstl:out
					value="${squadra.category.cname}"></jstl:out></li>
			<li><b><spring:message code="squadra.name" /></b> <jstl:out
					value="${squadra.name}"></jstl:out></li>
		</ul>
		
		
<%-- 	<tag:select code="squadra.players.add" path="player"  id="player" items="players" itemLabel="name"/> --%>

	</jstl:if>
	<br>
	<br>
	<acme:cancel code="squadra.cancel" url="principal/index.do" />

</form:form>