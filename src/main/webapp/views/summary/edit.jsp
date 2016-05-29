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
		<form:form action="summary/coach/edit.do" modelAttribute="summary" id="demo-form2" data-parsley-validate=""
			class="form-horizontal form-label-left">
			<form:hidden path="id" />
			<form:hidden path="version" />
			<form:hidden path="coach" />
			<form:hidden path="match" />

			<tag:textbox2 code="summary.subject" path="subject" />
			<tag:textarea2 code="summary.text" path="text" />
			
			<div class="ln_solid"></div>
			<div class="form-group">
				<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
					<tag:submitverde code="summary.save" name="save" />
					<%-- <jstl:if test="${match.id != 0}">
		<tag:submit code="match.delete" name="delete" />
	</jstl:if> --%>

					<tag:buttonazul code="summary.cancel" url="match/coach/listPast.do'" />
				</div>
			</div>
		</form:form>
	</div>
</div>
