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
		<form:form action="${requestURI}" method="post"
			modelAttribute="comment">

			<form:hidden path="id" />
			<form:hidden path="version" />
			<form:hidden path="moment" />

			<ul>
				<li><b><spring:message code="comment.text" /></b> <jstl:out
						value="${comment.text}"></jstl:out></li>
				<li><b><spring:message code="comment.subject" /></b> <jstl:out
						value="${comment.subject}"></jstl:out></li>
			</ul>
			<br>
			<div class="ln_solid"></div>
			<div class="form-group">
				
					<tag:buttonverde code="display.return" url="match/listAll.do'" />
				</div>
			</div>

			<security:authorize access="hasRole('ADMIN')">

				<a href="comment/admin/delete.do?commentId=${comment.id}"><spring:message
						code="comment.delete" /></a>

			</security:authorize>
		</form:form>

	</div>
</div>
