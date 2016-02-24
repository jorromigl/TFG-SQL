<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>



<form:form modelAttribute="messageDisplay">
	
	<tag:textbox code="msg.moment" path="moment" readonly="true" />
	
	<tag:textbox code="msg.sender" path="sender.name" readonly="true" />

	<tag:textbox code="msg.recipient" path="recipient.name" readonly="true" />
	
	<tag:textbox code="msg.subject" path="subject" readonly="true" />
	
	<tag:textarea code="msg.body" path="body" readonly="true" />
		
	<security:authentication var="user" property="principal.id" />
	
	<jstl:if test="${messageDisplay.recipient.userAccount.id==user}">
	<input type="button" value="<spring:message code="msg.reply.link" />"
	onclick="javascript: location.replace('message/user/reply.do?messageId=${messageDisplay.id}')" />
		</jstl:if>
	

	

		<tag:cancel code="message.return" url='/message/user/list.do?folderId=${messageDisplay.folder.id}'/>
	
	
		<jstl:if test="${messageDisplay.recipient.userAccount.id==user || messageDisplay.sender.userAccount.id==user}">
			
				<input type="button" value="<spring:message code="msg.delete"  />"
				
				onclick="if(confirm('<spring:message code="msg.delete.del" />'))
				javascript: location.replace('message/user/delete.do?messageId=${messageDisplay.id}');
				return false" />
			
		</jstl:if>
	
	
</form:form>
