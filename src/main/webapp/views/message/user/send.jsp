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
		<form:form action="message/user/send.do" modelAttribute="m"
			id="demo-form2" data-parsley-validate=""
			class="form-horizontal form-label-left">

			<form:hidden path="id" />
			<form:hidden path="version" />
			<form:hidden path="sender" />
			<form:hidden path="folder" />
			<form:hidden path="moment" />

			<jstl:if test="${m.recipient == null}">

				<tag:selectstyle items="${users}" itemLabel="userAccount.username"
					code="msg.recipient" path="recipient" />

			</jstl:if>

			<jstl:if test="${m.recipient != null}">

				<form:hidden path="recipient" />
			</jstl:if>

			<tag:textbox2 code="msg.subject" path="subject" />

			<tag:textarea2 code="msg.body" path="body" />
			<div class="ln_solid"></div>
			<div class="form-group">
				<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">

					<tag:submitverde name="save" code="msg.send" />

					<tag:buttonazul code="msg.cancel" url="principal/index.do'" />

				</div>
			</div>
		</form:form>
	</div>
</div>