
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form:form action="j_spring_security_check" modelAttribute="credentials">
<body style="background: #F7F7F7;">
	<a class="hiddenanchor" id="toregister"></a>
	<a class="hiddenanchor" id="tologin"></a>

	<div id="wrapper">
		<div id="login" class="animate form">
			<section class="login_content">
				<form action="">
					<h1>
						
						<spring:message code="security.loginForm" />
					</h1>
					<div>
						<spring:message code="security.username" var="variable1" />
						<form:input class="form-control" path="username"
							placeholder="${variable1}" />
						<form:errors class="error" path="username" />
					</div>

					<br />
					<div>
						<spring:message code="security.password" var="variable2" />
						<form:password class="form-control" path="password"
							placeholder="${variable2}" />
						<form:errors class="error" path="password" />
					</div>
					
					<br />

					<jstl:if test="${showError == true}">
						<div class="error">
							<spring:message code="security.login.failed" />
						</div>
					</jstl:if>

					<input class="btn btn-default submit" type="submit"
						value="<spring:message code="security.login" />" /> <br> <a
						class="reset_pass" href="#"><spring:message
							code="security.lostYourPassword" /></a>

					<div class="clearfix"></div>
					<div class="separator">

						<p class="change_link">
							<spring:message code="security.newSite" />
							<a href="#toregister" class="to_register"> <spring:message
									code="security.createAccount" />
							</a>
						</p>
						<div class="clearfix"></div>
						<br />
						<div>
							<h1>
							
								<i  class="fa fa fa-futbol-o" style="font-size: 26px;"></i>
								<spring:message code="security.footballSchool" />
							</h1>

							<p>
								©2016
								<spring:message code="security.AllRightsReserved" />
								<fmt:formatDate value="${date}" pattern="yyyy" />
							</p>
						</div>
					</div>
				</form>
				<!-- form -->
			</section>
			<!-- content -->
		</div>
	</div>
</body>
</form:form>

