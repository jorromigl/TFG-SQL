<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="date" class="java.util.Date" />

<br />

<p class="text-center">Copyright <fmt:formatDate value="${date}" pattern="yyyy" /> &copy; EscuelaFutbol</p>
