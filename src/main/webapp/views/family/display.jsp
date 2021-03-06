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

<security:authorize access="hasRole('FAMILY')">
	<jstl:if test="${detailsFamily==true}">
		<form:form action="${requestURI}" method="post"
			modelAttribute="family" enctype="multipart/form-data">
			<div class="content">
				<div class="col-md-12">
					<div class="x_panel">


						<form:hidden path="id" />
						<form:hidden path="version" />
						<form:hidden path="player" />


						<div class="col-md-3 col-sm-3 col-xs-12 profile_left">
							<jstl:if test="${family.file == null }">
								<div class="thumbnail">
									<div class=" view view-first">
										<img style="width: 100%; display: block;"
											src="images/user1.png" alt="image">
										<div class="mask">
											<p>
												<spring:message code="family.change"></spring:message>
											</p>
											<div class="tools tools-bottom">
												<a href="family/displayC.do"><i class="fa fa-pencil"></i></a>
											</div>
										</div>
									</div>

								</div>
							</jstl:if>

							<jstl:if test="${family.file != null }">

								<div class="avatar-view">
									<div class=" view view-first">
										<img style="width: 100%; display: block;"
											src="family/showImage.do?familyId=${family.id}" alt="image">
										<div class="mask">
											<p>
												<spring:message code="family.change"></spring:message>
											</p>
											<div class="tools tools-bottom">
												<a href="family/displayC.do"><i class="fa fa-pencil"></i></a>
											</div>
										</div>
									</div>
								</div>

							</jstl:if>
						</div>


						<h3 class="title">
							<jstl:out value="${family.name}"></jstl:out>
							<jstl:out value="${family.surname}"></jstl:out>
						</h3>
						<H4>
							<jstl:out value="${family.userAccount.username}"></jstl:out>
						</H4>

						<h5 class="fa fa-envelope">
							<jstl:out value="${family.email}"></jstl:out>
						</h5>
						<br>
						<h5 class="fa fa-phone">
							<jstl:out value="${family.phone}"></jstl:out>
						</h5>
						<br>
						<h5 class="fa fa-home ">
							<jstl:out value="${family.address}"></jstl:out>
						</h5>

						<br>
					</div>
					<tag:buttonverde code="display.edit" url="family/displayB.do'" />
					<tag:buttonazul code="display.cancel" url="principal/index.do'" />
				</div>

			</div>

		</form:form>
	</jstl:if>

	<jstl:if test="${detailsFamily==false}">
		<jstl:if test="${editPhoto==false }">
			<div id="wrapper">
				<section class="login_content">
					<form:form action="${requestURI}" method="post"
						modelAttribute="family">

						<form:hidden path="id" />
						<form:hidden path="version" />
						<form:hidden path="player" />

						<tag:textboxstyle code="display.username" path="username" />
						<tag:passwordstyle code="display.password" path="password" />
						<tag:passwordstyle code="display.password2" path="verifyPassword" />
						<tag:textboxstyle code="display.name" path="name" />
						<tag:textboxstyle code="display.surname" path="surname" />
						<tag:textboxstyle code="display.email" path="email" />
						<tag:textboxstyle code="display.phone" path="phone" />
						<tag:textboxstyle code="display.address" path="address" />

						<tag:submitverde code="display.save" name="save1" />
						<tag:buttonazul code="display.cancel" url="principal/index.do'" />
					</form:form>
		</jstl:if>

		<jstl:if test="${editPhoto==true }">
			<form:form action="${requestURI}" method="post"
				modelAttribute="family" enctype="multipart/form-data">

				<form:hidden path="id" />
				<form:hidden path="version" />

				<form:label path="file">
					<spring:message code="family.file"></spring:message>
				</form:label>
				<form:input path="file" type="file" />
				<form:errors cssClass="error" path="file">

				</form:errors>
				<br>
				<div class="alert alert-success" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong><spring:message code="family.sizePhoto"></spring:message></strong>
				</div>
				<br>
				<tag:submit code="display.save" name="save2" />
				<tag:buttonazul code="display.cancel" url="principal/index.do'" />
			</form:form>
		</jstl:if>
	</jstl:if>
</security:authorize>
<security:authorize access="hasRole('PLAYER')">
	<form:form action="${requestURI}" method="post" modelAttribute="family"
		enctype="multipart/form-data">
		<div class="content">
			<div class="col-md-12">
				<div class="x_panel">

					<form:hidden path="id" />
					<form:hidden path="version" />

					<div class="col-md-3 col-sm-3 col-xs-12 profile_left">
						<jstl:if test="${family.file == null }">
							<div class="thumbnail">
								<div class=" view view-first">
									<img style="width: 100%; display: block;"
										src="images/user1.png" alt="image">
								</div>

							</div>
						</jstl:if>

						<jstl:if test="${family.file != null }">

							<div class="avatar-view">
								<div class=" view view-first">
									<img style="width: 100%; display: block;"
										src="family/showImage.do?familyId=${family.id}" alt="image">
								</div>
							</div>

						</jstl:if>
					</div>


					<h3 class="title">
						<jstl:out value="${family.name}"></jstl:out>
						<jstl:out value="${family.surname}"></jstl:out>
					</h3>
					<H4>
						<jstl:out value="${family.userAccount.username}"></jstl:out>
					</H4>

					<h5 class="fa fa-envelope">
						<jstl:out value="${family.email}"></jstl:out>
					</h5>
					<br>
					<h5 class="fa fa-phone">
						<jstl:out value="${family.phone}"></jstl:out>
					</h5>
					<br>
					<h5 class="fa fa-home ">
						<jstl:out value="${family.address}"></jstl:out>
					</h5>

					<br>
				</div>
				<tag:buttonazul code="display.cancel" url="principal/index.do'" />
			</div>
		</div>
	</form:form>
</security:authorize>