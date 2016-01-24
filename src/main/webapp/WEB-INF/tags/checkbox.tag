<%@ tag language="java" body-content="empty" %>

<%-- Taglibs --%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<%-- Attributes --%> 

<%@ attribute name="path" required="true" %>
<%@ attribute name="code" required="true" %>
<%@ attribute name="url" required="false" %>
<%@ attribute name="readonly" required="false" %>

<%@ attribute name="id" required="false" %>
<%@ attribute name="onchange" required="false" %>

<jstl:if test="${readonly == null}">
	<jstl:set var="readonly" value="false" />
</jstl:if>



<jstl:if test="${onchange == null}">
	<jstl:set var="onchange" value="javascript: return true;" />
</jstl:if>

<%-- Definition --%>

<div class="form-group" >
	<form:label path="${path}">
	<jstl:if test="${url != null}">
		<a target="_blank" href="${url}"><spring:message code="${code}" /></a>
	</jstl:if>
	<jstl:if test="${url == null}">
		<spring:message code="${code}" />
	</jstl:if>
	</form:label>	
	
	<form:checkbox path="${path}" readonly="${readonly}"/>
	<form:errors path="${path}" cssClass="error" />
</div>
