<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="EscuelaFutbol" />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="principal.page.admin" /></a>
				<ul>
					<!-- <li class="arrow"></li> -->
					<li><a href="admin/action-1.do"><spring:message code="principal.page.admin.action.1" /></a></li>
					<li><a href="admin/action-2.do"><spring:message code="principal.page.admin.action.2" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('COACH')">
			<li><a class="fNiv"><spring:message	code="principal.page.coach" /></a>
				<ul>
					<!--  <li class="arrow"></li>-->
					<li><a href="coach/action-1.do"><spring:message code="principal.page.coach.action.1" /></a></li>
					<li><a href="coach/action-2.do"><spring:message code="principal.page.coach.action.2" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="principal.page.login" /></a></li>
			<li><a href="player/register.do"><spring:message code="master.page.register.player" /></a></li>
			<li><a href="family/register.do"><spring:message code="master.page.register.family" /></a></li>		
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="principal.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
				<!-- 	<li class="arrow"></li> -->
					<li><a href="profile/action-1.do"><spring:message code="principal.page.profile.action.1" /></a></li>
					<li><a href="profile/action-2.do"><spring:message code="principal.page.profile.action.2" /></a></li>
					<li><a href="profile/action-3.do"><spring:message code="principal.page.profile.action.3" /></a></li>					
					<li><a href="j_spring_security_logout"><spring:message code="principal.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

