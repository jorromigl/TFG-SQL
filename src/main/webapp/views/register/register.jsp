<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<security:authorize access="isAnonymous()">
	<body style="background: #F7F7F7;">
		<a class="hiddenanchor" id="toregister"></a>
		<a class="hiddenanchor" id="tologin"></a>
</security:authorize>

<div id="wrapper">
	<section class="login_content">


		<jstl:if test="${isPlayer==true && isCoach== false}">
			<form:form action="player/register.do" modelAttribute="playerForm" enctype = "multipart/form-data">
				<h1>
					<spring:message code="register.registerPlayer" />
				</h1>

				<form:hidden path="id" />
				<form:hidden path="version" />

				<tag:textboxstyle code="register.username" path="username" />
				<tag:passwordstyle code="register.password" path="password" />
				<tag:passwordstyle code="register.password2" path="verifyPassword" />
				<tag:textboxstyle code="register.name" path="name" />
				<tag:textboxstyle code="register.surname" path="surname" />
				<tag:textboxstyle code="register.category1" path="category.cname" />
				<tag:textboxstyle code="register.date" path="date" />
				<tag:textboxstyle code="register.email" path="email" />
				<tag:textboxstyle code="register.phone" path="phone" />
				<tag:textboxstyle code="register.address" path="address" />
				
				<form:label path="file"> <spring:message code ="player.file"></spring:message></form:label>
				<form:input path="file" type = "file" />
				<form:errors cssClass= "error" path ="file"> </form:errors>

				<tag:checkbox path="available" url="law/law.do"
					code="register.available" />
				<br />
				<tag:submitstyle code="register.save" name="save" />

			</form:form>
		</jstl:if>
		<jstl:if test="${isPlayer==false  && isCoach== false}">
			<form:form action="family/register.do" modelAttribute="familyForm">
				<h1>
					<spring:message code="register.registerFamily" />
				</h1>

				<form:hidden path="id" />
				<form:hidden path="version" />

				<tag:textboxstyle code="register.username" path="username" />
				<tag:passwordstyle code="register.password" path="password" />
				<tag:passwordstyle code="register.password2" path="verifyPassword" />
				<tag:textboxstyle code="register.name" path="name" />
				<tag:textboxstyle code="register.surname" path="surname" />
				<tag:textboxstyle code="register.email" path="email" />
				<tag:textboxstyle code="register.phone" path="phone" />
				<tag:textboxstyle code="register.address" path="address" />

				<tag:select code="register.players" path="player" items="${players}"
					itemLabel="fullName" />

				<tag:checkbox path="available" url="law/law.do"
					code="register.available" />
				<br />
				<tag:submitstyle code="register.save" name="save" />

			</form:form>
		</jstl:if>
		<!-- form -->


		<jstl:if test="${isPlayer==false && isCoach== true}">
			<form:form action="coach/register.do" modelAttribute="coachForm">
				<h1>
					<spring:message code="register.registerCoach" />
				</h1>

				<form:hidden path="id" />
				<form:hidden path="version" />


				<tag:textboxstyle code="register.username" path="username" />
				<tag:passwordstyle code="register.password" path="password" />
				<tag:passwordstyle code="register.password2" path="verifyPassword" />
				<tag:textboxstyle code="register.name" path="name" />
				<tag:textboxstyle code="register.surname" path="surname" />
				<tag:textboxstyle code="register.category1" path="category.cname" />
				<tag:textboxstyle code="register.email" path="email" />
				<tag:textboxstyle code="register.phone" path="phone" />
				<tag:textboxstyle code="register.address" path="address" />

				<tag:checkbox path="available" url="law/law.do"
					code="register.available" />
				<br />
				<tag:submitstyle code="register.save" name="save" />

			</form:form>
		</jstl:if>
		<tag:buttonstyle code="register.cancel" url="principal/index.do'" />
		<br />

	</section>
	<!-- content -->
</div>


</body>

