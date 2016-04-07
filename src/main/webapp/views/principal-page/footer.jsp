<?xml version="1.0" encoding="UTF-8" ?>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<%-- <jsp:useBean id="date" class="java.util.Date" /> --%>

<!-- <br /> -->

<%-- <p class="text-center">Copyright <fmt:formatDate value="${date}" pattern="yyyy" /> &copy; EscuelaFutbol</p> --%>

<div class="text-center">
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

<div>


	<p class="text-center">
		©2016
		<spring:message code="security.AllRightsReserved" />
		<fmt:formatDate value="${date}" pattern="yyyy" />
	</p>
</div>