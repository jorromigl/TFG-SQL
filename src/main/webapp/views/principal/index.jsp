<?xml version="1.0" encoding="UTF-8" ?>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<security:authorize access="isAnonymous()">
 
	<body style="background: #F7F7F7;">

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">

			<ul class="nav navbar-nav navbar-right">
				<li><a href="security/login.do"><spring:message
							code="principal.page.login" /></a></li>
			</ul>
		</div>
		<!-- banner area starts here -->
		<section class="banner text-center" id="sec1">
			<div class="container">
				<div class="row">

					<h3>
						<spring:message code="texto.uno" />
					</h3>

					<h1>
						<spring:message code="texto.dos" />
					</h1>

					<p>
						<spring:message code="texto.tres" />
					</p>

					<!-- 					<img src="images/camp2.jpg" alt=""><br> -->
					<img src="images/pin.png" alt=""><br>

				</div>
			</div>
		</section>
		<!-- end of banner section -->

	</body>



</security:authorize>
