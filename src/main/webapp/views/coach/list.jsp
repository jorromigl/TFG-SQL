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
<display:table name="coachs" id="row" requestURI="${requestURI}" pagesize="5" class="table table-hover">

	<spring:message code="coach.fullName" var="fullName" />
	<display:column property="fullName" title="${fullName}" sortable="${true}" />
	
	<spring:message code="coach.category" var="cname" />
	<display:column property="category.cname" title="${cname}" sortable="${true}" />
	
	<spring:message code="coach.phone" var="phone" />
	<display:column property="phone" title="${phone}"  />
	
	<display:column >
			<a href="coach/displayA.do?coachId=${row.id}" ><spring:message code="coach.display" /></a>
	</display:column>
	
	<display:column >
			<a href="coach/delete.do?coachId=${row.id}" ><spring:message code="coach.delete" /></a>
	</display:column>
		
</display:table>
</div>
	
	<tag:button code="coach.return" url="principal/index.do'" />

<div>
<%-- <display:table name="coachs" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag"> --%>

<%-- 	<spring:message code="coach.fullName" var="fullName" /> --%>
<%-- 	<display:column property="fullName" title="${fullName}" sortable="${true}" /> --%>
	
<%-- 	<spring:message code="coach.category" var="cname" /> --%>
<%-- 	<display:column property="category.cname" title="${cname}" sortable="${true}" /> --%>
	
<%-- 	<spring:message code="coach.phone" var="phone" /> --%>
<%-- 	<display:column property="phone" title="${phone}"  /> --%>
	
<%-- 	<display:column > --%>
<%-- 			<a href="coach/displayA.do?coachId=${row.id}" ><spring:message code="coach.display" /></a> --%>
<%-- 	</display:column> --%>
	
<%-- 	<display:column > --%>
<%-- 			<a href="coach/delete.do?coachId=${row.id}" ><spring:message code="coach.delete" /></a> --%>
<%-- 	</display:column> --%>
		
<%-- </display:table> --%>
	
<%-- 	<tag:button code="coach.return" url="principal/index.do'" /> --%>
</div>