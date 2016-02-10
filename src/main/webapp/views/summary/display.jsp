<?xml version="1.0" encoding="UTF-8" ?>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<jstl:if test="${detailsSummary==true}">
<form:form action="${requestURI}" method="post"  modelAttribute="summary">
		
<!-- 		enctype="multipart/form-data"  PARA FOTOOOOO en la etiqueta form-->	

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	
		<tag:textbox code="display.text" path="text" readonly="true" />
		<tag:textbox code="display.subject" path="subject" readonly="true"/>
		
	
	<%-- <jstl:if test="${incidence.photo!=null}">
		<b><spring:message code="incidence.photo" /></b><br/><br/>
		<img class="img-responsive img-rounded" src="photo/displayImage.do?incidenceId=${incidence.id}" height="350" width="590" />
	</jstl:if> 
	<jstl:if test="${incidence.photo==null}">
		<b><spring:message code="incidence.photo" /></b><br/><br/>
		<img class="img-responsive img-rounded" src="images/no-image.png" height="350" width="590" />
	</jstl:if> --%>
<%-- 	<tag:button code="display.edit" url="player/displayB.do'" /> --%>	
	<tag:button code="display.return" url="match/coach/listPast.do'" />
</form:form>
</jstl:if>

<%-- <jstl:if test="${detailsPlayer==false}">
<form:form action="${requestURI}" method="post"  modelAttribute="player">
		
<!-- 		enctype="multipart/form-data"  PARA FOTOOOOO en la etiqueta form-->	

	<form:hidden path="id"/>
	<form:hidden path="version"/>
		
		<tag:textbox code="display.username" path="registrationForm.username" />
		<tag:password code="display.password" path="registrationForm.password" />
		<tag:password code="display.password2" path="registrationForm.verifyPassword"/>
		<tag:textbox code="display.name" path="registrationForm.name" />
		<tag:textbox code="display.surname" path="registrationForm.surname"/>
		<tag:textbox code="display.category" path="category.cname"/>
		<tag:textbox code="display.date" path="date" readonly="true"/>
		<tag:textbox code="display.email" path="registrationForm.email" />
		<tag:textbox code="display.phone" path="registrationForm.phone"/>
		<tag:textbox code="display.address" path="registrationForm.address"/>
	
	<jstl:if test="${incidence.photo!=null}">
		<b><spring:message code="incidence.photo" /></b><br/><br/>
		<img class="img-responsive img-rounded" src="photo/displayImage.do?incidenceId=${incidence.id}" height="350" width="590" />
	</jstl:if> 
	<jstl:if test="${incidence.photo==null}">
		<b><spring:message code="incidence.photo" /></b><br/><br/>
		<img class="img-responsive img-rounded" src="images/no-image.png" height="350" width="590" />
	</jstl:if>
	<tag:submit code="display.save" name="save1" />
	<tag:button code="display.cancel" url="principal/index.do'" />
</form:form>
</jstl:if> --%>
