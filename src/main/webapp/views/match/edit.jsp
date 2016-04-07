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
		<form:form action="match/coach/edit.do" modelAttribute="match"
			id="demo-form2" data-parsley-validate=""
			class="form-horizontal form-label-left" novalidate="">

			<form:hidden path="id" />
			<form:hidden path="version" />


			<tag:textbox2 code="match.rival" path="rival" />
			<tag:textbox2 code="match.location" path="location" />
			<tag:selectstyle code="match.squadra" path="squadra" items="${squadras}"
				itemLabel="name" />
			<tag:textbox2 code="match.moment" path="moment" />

			<div class="ln_solid"></div>
			<div class="form-group">
				<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
					<tag:submitverde code="match.save" name="save" />
					<jstl:if test="${match.id != 0}">
						<tag:submitverde code="match.delete" name="delete" />
					</jstl:if>

					<tag:buttonazul code="match.cancel" url="principal/index.do'" />
				</div>
			</div>



		</form:form>

	</div>
</div>


