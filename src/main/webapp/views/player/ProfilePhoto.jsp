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

<form:form action="${requestURI}" method="post"
			modelAttribute="player" enctype="multipart/form-data">


			<form:hidden path="id" />
			<form:hidden path="version" />
<%-- 
				<img style="width: 50px; height: 50px;"
					src="player/showImage.do?playerId=${player.id}" /> --%>
				<spring:message code="player.ModifyImage"></spring:message>
				
				<form:label path="file">
					<spring:message code="player.file"></spring:message>
				</form:label>
				<form:input path="file" type="file" />
				<form:errors cssClass="error" path="file">
				
				</form:errors>

			<tag:submit code="display.save" name="save2" />
			<tag:button code="display.cancel" url="principal/index.do'" />
		</form:form>
