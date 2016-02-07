<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>



<display:table name="matches" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="match.rival" var="rival" />
	<display:column property="rival" title="${rival}" sortable="${true}" />
	
	<spring:message code="match.location" var="location" />
	<display:column property="location" title="${location}" />
	
	<spring:message code="match.moment" var="moment" />
	<display:column property="moment" title="${moment}"  />
	<jstl:if test="${isFuture==false and isAll==false}">
	<display:column >
		<a href="summary/coach/displayA.do?matchId=${row.id}" ><spring:message code="match.display" /></a>
	</display:column>
	</jstl:if>
	<jstl:if test="${isFuture==true}">
	<display:column >
		<a href="match/coach/edit.do?matchId=${row.id}" ><spring:message code="match.edit" /></a>
	</display:column>
	<display:column >
		<a href="match/coach/delete.do?matchId=${row.id}" ><spring:message code="match.delete" /></a>
	</display:column>
	</jstl:if>
	<%-- <display:column >
			<a href="coach/displayA.do?coachId=${row.id}" ><spring:message code="coach.display" /></a>
	</display:column>
	
			<display:column >
				<a href="coach/delete.do?coachId=${row.id}" ><spring:message code="coach.delete" /></a>
			</display:column> --%>
			
</display:table>
	
	<tag:button code="match.return" url="principal/index.do'" />
