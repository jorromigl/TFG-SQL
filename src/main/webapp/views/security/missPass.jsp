<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<security:authorize access="isAnonymous()">
<form:form action="security/missPass.do" modelAttribute="passForm">
	
		
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<tag:textboxstyle code="pass.email" path="email" />
		
	<tag:submitverde code="pass.send" name="send" />
		
	<tag:cancel code="pass.cancel" url="principal/index.do" />
</form:form>
</security:authorize>