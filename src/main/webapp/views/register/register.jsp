<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF8"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

	<form:form action="player/register.do" modelAttribute="playerForm">

		<form:hidden path="id" />
		<form:hidden path="version" />
		
		<tag:textbox code="register.username" path="registrationForm.username" />
		<tag:password code="register.password" path="registrationForm.password" />
		<tag:password code="register.password2" path="registrationForm.verifyPassword" />
		<tag:textbox code="register.name" path="registrationForm.name" />
		<tag:textbox code="register.surname" path="registrationForm.surname" />
		<tag:textbox code="register.email" path="registrationForm.email" />
		<tag:textbox code="register.phone" path="registrationForm.phone" />
		<tag:textbox code="register.address" path="registrationForm.address" />
		<%-- <br/>
		<tag:checkbox path="available" url="law/law.do" code="register.text" />
		<br/> --%>
		<tag:submit code="register.save" name="save" />
	
	</form:form>
	
	<tag:button code="register.cancel" url="principal/index.do'" />
