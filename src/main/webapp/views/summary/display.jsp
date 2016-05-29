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

<div class="x_panel">
	<div class="x_content">
		<br>
		<jstl:if test="${detailsSummary==true}">
			<form:form action="${requestURI}" method="post"
				modelAttribute="summary">

				<form:hidden path="id" />
				<form:hidden path="version" />

				<ul>
					<li><b><spring:message code="display.text" /></b> <jstl:out
							value="${summary.text}"></jstl:out></li>
					<li><b><spring:message code="display.subject" /></b> <jstl:out
							value="${summary.subject}"></jstl:out></li>
				</ul>
		

				<security:authorize access="hasRole('COACH')">
					<div class="ln_solid"></div>
					<div class="form-group">
						<tag:buttonverde code="summary.edit"
							url="summary/coach/edit.do?summaryId=${summary.id}'" />
						<tag:buttonazul code="display.return" url="match/coach/listPast.do'" />
					</div>
				</security:authorize>
				<security:authorize access="hasRole('PLAYER')">
					<div class="ln_solid"></div>
					<div class="form-group">
						<tag:buttonverde code="display.return"
							url="match/player/listPast.do'" />
					</div>
				</security:authorize>
				<security:authorize access="hasRole('FAMILY')">
				<div class="ln_solid"></div>
					<div class="form-group">
					<tag:buttonverde code="display.return" url="match/family/listPast.do'" />
				</div>
				</security:authorize>
			</form:form>
		</jstl:if>


	</div>
</div>
