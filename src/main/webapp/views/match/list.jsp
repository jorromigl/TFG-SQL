<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

	<!-- page content -->
			
						
<div class="x_panel">
<display:table name="matches" id="row" requestURI="${requestURI}" pagesize="5" class="table table-hover">

	<spring:message code="match.rival" var="rival" />
	<display:column property="rival" title="${rival}" sortable="${true}" />
	
	<spring:message code="match.location" var="location" />
	<display:column property="location" title="${location}" />
	
	<spring:message code="match.squadra" var="name" />
	<display:column property="squadra.name" title="${name}"  />
	
	<spring:message code="match.moment" var="moment" />
	<display:column property="moment" title="${moment}"  />
	
	<display:column >
		<a href="comment/listByMatch.do?matchId=${row.id}" ><spring:message code="match.comment" /></a>
	</display:column>
	<display:column >
		<a href="comment/create.do?matchId=${row.id}" ><spring:message code="match.comment.create" /></a>
	</display:column>
	
	<security:authorize access="hasRole('PLAYER')">
	<jstl:if test="${isFuture==false and isAll==false}">
	<display:column >
		<a href="summary/player/displayA.do?matchId=${row.id}" ><spring:message code="match.display" /></a>
	</display:column>
	</jstl:if>	
	</security:authorize>
	
	<security:authorize access="hasRole('FAMILY')">
	<jstl:if test="${isFuture==false and isAll==false}">
	<display:column >
		<a href="summary/family/displayA.do?matchId=${row.id}" ><spring:message code="match.display" /></a>
	</display:column>
	</jstl:if>	
	</security:authorize>
	
	<security:authorize access="hasRole('COACH')">
	<jstl:if test="${isFuture==false and isAll==false}">
	<display:column >
	<jstl:if test="${row.summary!= null}">
		<a href="summary/coach/displayA.do?matchId=${row.id}" ><spring:message code="match.display" /></a>
	</jstl:if>
	<jstl:if test="${row.summary== null}">
		<a href="summary/coach/create.do?matchId=${row.id}" ><spring:message code="match.summary.create" /></a>
	</jstl:if>
	</display:column>
	</jstl:if>
	<jstl:if test="${isFuture==true}">
	<display:column >
		<a href="match/coach/edit.do?matchId=${row.id}" ><spring:message code="match.edit" /></a>
	</display:column>
	<display:column >
		<a href="match/coach/delete.do?matchId=${row.id}" ><spring:message code="match.delete" /></a>
	</display:column>
	<display:column >
	
	<jstl:if test="${row.recruitment==null}">
		<a href="recruitment/coach/create.do?matchId=${row.id}" ><spring:message code="match.recruitment.create" /></a>
	</jstl:if>
	</display:column>
	</jstl:if>
	
	<%-- <display:column >
			<a href="coach/displayA.do?coachId=${row.id}" ><spring:message code="coach.display" /></a>
	</display:column>
	
			<display:column >
				<a href="coach/delete.do?coachId=${row.id}" ><spring:message code="coach.delete" /></a>
			</display:column> --%>
	</security:authorize>
			
</display:table>
</div>
	<tag:button code="match.return" url="principal/index.do'" />

						