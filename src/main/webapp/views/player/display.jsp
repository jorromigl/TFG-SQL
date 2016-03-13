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


<security:authorize access="hasRole('PLAYER')">
	<jstl:if test="${detailsPlayer==true}">
		<form:form action="${requestURI}" method="post"
			modelAttribute="player" enctype="multipart/form-data">

			<%-- <!-- 		enctype="multipart/form-data"  PARA FOTOOOOO en la etiqueta form-->	 --%>

			<form:hidden path="id" />
			<form:hidden path="version" />


			<tag:textbox code="display.username" path="userAccount.username" readonly="true" />
			<tag:textbox code="display.name" path="name" readonly="true" />
			<tag:textbox code="display.surname" path="surname" readonly="true" />
			<tag:textbox code="display.category" path="category.cname" readonly="true" />
			<tag:textbox code="display.date" path="date" readonly="true" />
			<tag:textbox code="display.email" path="email" readonly="true" />
			<tag:textbox code="display.phone" path="phone" readonly="true" />
			<tag:textbox code="display.address" path="address" readonly="true" />


			<jstl:if test="${player.file == null }">
				<spring:message code="player.notImage"></spring:message>
				
			</jstl:if>

			<jstl:if test="${player.file != null }">
				<img style="width: 50px; height: 50px;"
					src="player/showImage.do?playerId=${player.id}" />
			</jstl:if>

			<jstl:if test="${viewProfileOther==false}">
				<tag:button code="display.edit" url="player/displayB.do'" />
				<tag:button code="display.edit2" url="player/displayC.do'" />
			</jstl:if>
			<tag:button code="display.cancel" url="principal/index.do'" />

		</form:form>
	</jstl:if>

	<jstl:if test="${detailsPlayer==false }">
		
		<jstl:if test="${editPhoto==false }">
		<form:form action="${requestURI}" method="post"
			modelAttribute="player" enctype="multipart/form-data">


			<form:hidden path="id" />
			<form:hidden path="version" />
			<tag:textbox code="display.username" path="username" />
			<tag:password code="display.password" path="password" />
			<tag:password code="display.password2" path="verifyPassword" />
			<tag:textbox code="display.name" path="name" />
			<tag:textbox code="display.surname" path="surname" />
			<tag:textbox code="display.category" path="category.cname" />
			<tag:textbox code="display.date" path="date" readonly="true" />
			<tag:textbox code="display.email" path="email" />
			<tag:textbox code="display.phone" path="phone" />
			<tag:textbox code="display.address" path="address" />
			
			
			<br>


			<tag:submit code="display.save" name="save1" />
			<tag:button code="display.cancel" url="principal/index.do'" />
		</form:form>
		</jstl:if>
	
		<jstl:if test="${editPhoto==true}">
			<form:form action="${requestURI}" method="post"
				modelAttribute="player" enctype="multipart/form-data">


				<form:hidden path="id" />
				<form:hidden path="version" />

				<spring:message code="player.ModifyImage"></spring:message>
				
				<form:label path="file">
					<spring:message code="player.file"></spring:message>
				</form:label>
				<form:input path="file" type="file" />
				<form:errors cssClass="error" path="file">
				
				</form:errors> 
				<br>
	
	
				<tag:submit code="display.save" name="save2" />
				<tag:button code="display.cancel" url="principal/index.do'" />
			</form:form>
			</jstl:if>
		</jstl:if>
		
	
</security:authorize>
<security:authorize access="hasRole('COACH')">
	<form:form action="${requestURI}" method="post" modelAttribute="player"
		enctype="multipart/form-data">

		<form:hidden path="id" />
		<form:hidden path="version" />


		<tag:textbox code="display.username" path="userAccount.username" readonly="true" />
		<tag:textbox code="display.name" path="name" readonly="true" />
		<tag:textbox code="display.surname" path="surname" readonly="true" />
		<tag:textbox code="display.category" path="category.cname"
			readonly="true" />
		<tag:textbox code="display.date" path="date" readonly="true" />
		<tag:textbox code="display.email" path="email" readonly="true" />
		<tag:textbox code="display.phone" path="phone" readonly="true" />
		<tag:textbox code="display.address" path="address" readonly="true" />
		
		<jstl:if test="${player.file == null }">
			<spring:message code="player.notImage"></spring:message>
		</jstl:if>

		<jstl:if test="${player.file != null }">
			<img style="width: 50px; height: 50px;"
				src="player/showImage.do?playerId=${player.id}" />
		</jstl:if>

	
		<tag:button code="display.cancel" url="principal/index.do'" />
	</form:form>
</security:authorize>

<security:authorize access="hasRole('FAMILY')">
	<form:form action="${requestURI}" method="post" modelAttribute="player"
		enctype="multipart/form-data">


		<form:hidden path="id" />
		<form:hidden path="version" />


		<tag:textbox code="display.username" path="userAccount.username" readonly="true" />
		<tag:textbox code="display.name" path="name" readonly="true" />
		<tag:textbox code="display.surname" path="surname" readonly="true" />
		<tag:textbox code="display.category" path="category.cname"
			readonly="true" />
		<tag:textbox code="display.date" path="date" readonly="true" />
		<tag:textbox code="display.email" path="email" readonly="true" />
		<tag:textbox code="display.phone" path="phone" readonly="true" />
		<tag:textbox code="display.address" path="address" readonly="true" />
		
		<jstl:if test="${player.file == null }">
			<spring:message code="player.notImage"></spring:message>
		</jstl:if>

		<jstl:if test="${player.file != null }">
			<img style="width: 50px; height: 50px;"
				src="player/showImage.do?playerId=${player.id}" />
		</jstl:if>

		<tag:button code="display.cancel" url="principal/index.do'" />
	</form:form>
</security:authorize>

