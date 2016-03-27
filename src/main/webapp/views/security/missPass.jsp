<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>


<security:authorize access="isAnonymous()">
	<body style="background: #F7F7F7;">
		<a class="hiddenanchor" id="toregister"></a>
		<a class="hiddenanchor" id="tologin"></a>
		<div id="wrapper">
			<section class="login_content">

				<form:form action="security/missPass.do" modelAttribute="passForm">
					<h2>
						<spring:message code="security.missPass" />
					</h2>


					<form:hidden path="id" />
					<form:hidden path="version" />

					<tag:textboxstyle code="pass.email" path="email" />

					<tag:submitverde code="pass.send" name="send" />

					<tag:buttonazul code="pass.cancel" url="principal/index.do'" />
				</form:form>

				<div>
					<h2>

						<i class="fa fa fa-futbol-o" style="font-size: 26px;"></i>
						<spring:message code="security.footballSchool" />
					</h2>
				</div>
			</section>
			<!-- content -->
		</div>
	</body>
</security:authorize>
