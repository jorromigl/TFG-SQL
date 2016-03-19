<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<form:form action="match/coach/edit.do" modelAttribute="match">
	<form:hidden path="id" />
	<form:hidden path="version" />
	

	<tag:textbox code="match.rival" path="rival" />
	<tag:textbox code="match.location" path="location" />
	<tag:select code="match.squadra" path="squadra" items="${squadras}" itemLabel= "name" />
	<tag:textbox code="match.moment" path="moment" />
	
	
	<tag:submitverde code="match.save" name="save" />
	<jstl:if test="${match.id != 0}">
		<tag:submitverde code="match.delete" name="delete" />
	</jstl:if>
		
	<tag:cancel code="match.cancel" url="principal/index.do" />
</form:form>
