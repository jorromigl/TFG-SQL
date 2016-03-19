<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<form:form action="message/user/send.do" modelAttribute="m">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="sender" />
	<form:hidden path="folder" />
	<form:hidden path="moment" />

	<jstl:if test="${m.recipient == null}">

		<tag:select items="${users}" itemLabel="userAccount.username"
			code="msg.recipient" path="recipient" />

	</jstl:if>

	<jstl:if test="${m.recipient != null}">

		<form:hidden path="recipient" />
	</jstl:if>

	<tag:textbox code="msg.subject" path="subject" />

	<tag:textarea code="msg.body" path="body" />


	<tag:submitverde name="save" code="msg.send" />
	
	<tag:cancel code="msg.cancel" url="principal/index.do" />

</form:form>
