<?xml version="1.0" encoding="UTF-8" ?>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<div class="x_panel">
	<div class="x_content">
		<br>
		<form:form action="comment/edit.do" modelAttribute="comment"
			id="demo-form2" data-parsley-validate=""
			class="form-horizontal form-label-left">

			<form:hidden path="id" />
			<form:hidden path="version" />
			<form:hidden path="user" />
			<form:hidden path="match" />
			<form:hidden path="moment" />

			<tag:textbox2 code="comment.subject" path="subject" />
			<tag:textarea2 code="comment.text" path="text" />

			<div class="ln_solid"></div>
			<div class="form-group">
				<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
					<tag:submitverde code="comment.save" name="save" />
					<tag:buttonazul code="comment.cancel" url="principal/index.do'" />

				</div>
			</div>


		</form:form>


	</div>
</div>
