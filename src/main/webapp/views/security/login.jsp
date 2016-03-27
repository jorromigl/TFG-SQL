
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>


<body style="background: #F7F7F7;">
	<a class="hiddenanchor" id="toregister"></a>
	<a class="hiddenanchor" id="tologin"></a>

	<div id="wrapper">
			<section class="login_content">
			<form:form action="j_spring_security_check" modelAttribute="credentials">
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

					<input class="btn btn-success submit" type="submit"
						value="<spring:message code="security.login" />" /> <br> <a
						class="reset_pass" href="security/missPass.do"><spring:message
							code="security.lostYourPassword" /></a>

					<div class="clearfix"></div>
					<div class="separator">

						<p class="change_link">
							<spring:message code="security.newSite" />
							<a href="#bannerformmodal" data-toggle="modal" data-target="#bannerformmodal"class="to_register"> <spring:message
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
						</div>
					</div>
					</form:form>
				<!-- form -->
			</section>
			<!-- content -->
		</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>

<!-- Trigger the modal with a button -->
<!-- <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#bannerformmodal" id="bannerformmodal">Open Modal</button> -->

<!-- Modal -->
<div id="bannerformmodal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"><spring:message code="security.chooseRol" /></h4>
      </div>
      <div class="modal-body">
        <p><spring:message code="security.chooseRolText" /></p>
      </div>
      <div class="modal-footer">
      <tag:buttonstyle code="register.player" url="player/register.do'" />
      <tag:buttonstyle code="register.family" url="family/register.do'" />
      </div>
    </div>

  </div>
</div>


