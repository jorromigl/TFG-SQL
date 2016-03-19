<?xml version="1.0" encoding="UTF-8" ?>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('COACH')">
	<jstl:if test="${detailsCoach==true}">
		<form:form action="${requestURI}" method="post" modelAttribute="coach"
			enctype="multipart/form-data">

			<div class="content">
				<div class="col-md-8">

					<form:hidden path="id" />
					<form:hidden path="version" />


					<tag:textbox code="display.username" path="userAccount.username"
						readonly="true" />
					<tag:textbox code="display.name" path="name" readonly="true" />
					<tag:textbox code="display.surname" path="surname" readonly="true" />
					<tag:textbox code="display.category" path="category.cname"
						readonly="true" />
					<tag:textbox code="display.email" path="email" readonly="true" />
					<tag:textbox code="display.phone" path="phone" readonly="true" />
					<tag:textbox code="display.address" path="address" readonly="true" />

					<br>
					<tag:buttonverde code="display.edit" url="coach/displayB.do'" />

					<tag:buttonazul code="display.cancel" url="principal/index.do'" />
				</div>

				<jstl:if test="${coach.file == null }">
					<div class="col-xs-6 col-md-2">

						<div class="thumbnail">
							<div class=" view view-first">
								<img style="width: 100%; display: block;"
									src="images/user1.png" alt="image">
								<div class="mask">
									<p>
										<spring:message code="coach.change"></spring:message>
									</p>
									<div class="tools tools-bottom">
										<a href="coach/displayC.do"><i class="fa fa-pencil"></i></a>
									</div>
								</div>
							</div>

						</div>


<%-- 						<spring:message code="coach.notImage"></spring:message> --%>
<%-- 						<tag:buttonverde code="display.edit22" url="coach/displayC.do'" /> --%>
					</div>
				</jstl:if>

				<jstl:if test="${coach.file != null }">
					<div class="col-xs-6 col-md-2">

						<div class="thumbnail">
							<div class=" view view-first">
								<img style="width: 100%; display: block;"
									src="coach/showImage.do?coachId=${coach.id}" alt="image">
								<div class="mask">
									<p>
										<spring:message code="coach.change"></spring:message>
									</p>
									<div class="tools tools-bottom">
										<a href="coach/displayC.do"><i class="fa fa-pencil"></i></a>
									</div>
								</div>
							</div>

						</div>

						<%-- 						<tag:buttonverde code="display.edit2" url="coach/displayC.do'" /> --%>
					</div>
				</jstl:if>


			</div>
		</form:form>
	</jstl:if>

	<jstl:if test="${detailsCoach==false}">
		<jstl:if test="${editPhoto==false }">

			<form:form action="${requestURI}" method="post"
				modelAttribute="coach" enctype="multipart/form-data">



				<form:hidden path="id" />
				<form:hidden path="version" />

				<tag:textbox code="display.username" path="username" />
				<tag:password code="display.password" path="password" />
				<tag:password code="display.password2" path="verifyPassword" />
				<tag:textbox code="display.name" path="name" />
				<tag:textbox code="display.surname" path="surname" />
				<tag:textbox code="display.category" path="category.cname" />
				<tag:textbox code="display.email" path="email" />
				<tag:textbox code="display.phone" path="phone" />
				<tag:textbox code="display.address" path="address" />




				<tag:submit code="display.save" name="save1" />
				<tag:button code="display.cancel" url="principal/index.do'" />
			</form:form>
		</jstl:if>

		<jstl:if test="${editPhoto==true }">

			<form:form action="${requestURI}" method="post"
				modelAttribute="coach" enctype="multipart/form-data">

				<form:hidden path="id" />
				<form:hidden path="version" />

				<form:label path="file">
					<spring:message code="coach.file"></spring:message>
				</form:label>
				<form:input path="file" type="file" />
				<form:errors cssClass="error" path="file">

				</form:errors>
				<br>
				<tag:submit code="display.save" name="save2" />
				<tag:button code="display.cancel" url="principal/index.do'" />
			</form:form>
		</jstl:if>
	</jstl:if>
</security:authorize>
<security:authorize access="hasRole('PLAYER')">
	<form:form action="${requestURI}" method="post" modelAttribute="coach"
		enctype="multipart/form-data">


		<form:hidden path="id" />
		<form:hidden path="version" />


		<tag:textbox code="display.username" path="userAccount.username"
			readonly="true" />
		<tag:textbox code="display.name" path="name" readonly="true" />
		<tag:textbox code="display.surname" path="surname" readonly="true" />
		<tag:textbox code="display.category" path="category.cname"
			readonly="true" />
		<tag:textbox code="display.email" path="email" readonly="true" />
		<tag:textbox code="display.phone" path="phone" readonly="true" />
		<tag:textbox code="display.address" path="address" readonly="true" />

		<jstl:if test="${coach.file == null }">
			<spring:message code="coach.notImage"></spring:message>
		</jstl:if>

		<jstl:if test="${coach.file != null }">
			<img style="width: 50px; height: 50px;"
				src="coach/showImage.do?coachId=${coach.id}" />
		</jstl:if>

		<tag:button code="display.cancel" url="principal/index.do'" />
	</form:form>
</security:authorize>
<security:authorize access="hasRole('FAMILY')">
	<form:form action="${requestURI}" method="post" modelAttribute="coach"
		enctype="multipart/form-data">

		<form:hidden path="id" />
		<form:hidden path="version" />


		<tag:textbox code="display.username" path="userAccount.username"
			readonly="true" />
		<tag:textbox code="display.name" path="name" readonly="true" />
		<tag:textbox code="display.surname" path="surname" readonly="true" />
		<tag:textbox code="display.category" path="category.cname"
			readonly="true" />
		<tag:textbox code="display.email" path="email" readonly="true" />
		<tag:textbox code="display.phone" path="phone" readonly="true" />
		<tag:textbox code="display.address" path="address" readonly="true" />

		<jstl:if test="${coach.file == null }">
			<spring:message code="coach.notImage"></spring:message>
		</jstl:if>

		<jstl:if test="${coach.file != null }">
			<img style="width: 50px; height: 50px;"
				src="coach/showImage.do?coachId=${coach.id}" />
		</jstl:if>

		<tag:button code="display.cancel" url="principal/index.do'" />
	</form:form>
</security:authorize>