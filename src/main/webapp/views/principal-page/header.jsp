<?xml version="1.0" encoding="UTF-8" ?>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>




<security:authorize access="isAuthenticated()">


	<!-- <div> -->
	<!-- 	<a href="principal/index.do"> <img src="images/logo.png" alt="EscuelaFutbol" /></a> -->
	<!-- </div> -->
	<!-- top navigation -->
	<div class="top_nav">

		<div class="nav_menu">
			<nav class="" role="navigation">
				<div class="nav toggle">
					<a id="menu_toggle"><i class="fa fa-bars"></i></a>
				</div>

				<ul class="nav navbar-nav navbar-right">

					<security:authorize access="isAuthenticated()">

						<li><a href="j_spring_security_logout"><spring:message
									code="principal.page.logout" /> </a></li>

					</security:authorize>


					<li class=""><a href="javascript:;"
						class="user-profile dropdown-toggle" data-toggle="dropdown"
						aria-expanded="false"> <sec:authentication
								property="principal.username" /> <span
							class=" fa fa-angle-down"></span>

					</a>
						<ul
							class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">
							<security:authorize access="hasRole('COACH')">
							<li><a href="coach/displayA.do"><spring:message
										code="master.page.display" /> </a></li>				
						</security:authorize>
							<security:authorize access="hasRole('PLAYER')">
							<li><a href="player/displayA.do"><spring:message
										code="master.page.display" /> </a></li>					
						</security:authorize>
						<security:authorize access="hasRole('FAMILY')">
							<li><a href="family/displayA.do"><spring:message
										code="master.page.family.profile" /> </a></li>				
						</security:authorize>
						</ul></li>

					<li role="presentation" class="dropdown"><a
						href="javascript:;" class="dropdown-toggle info-number"
						data-toggle="dropdown" aria-expanded="false"> </a>
						<ul id="menu1"
							class="dropdown-menu list-unstyled msg_list animated fadeInDown"
							role="menu">
							<li><a> <span class="image"> <img
										src="images/img.jpg" alt="Profile Image" />
								</span> <span> <span>John Smith</span> <span class="time">3
											mins ago</span>
								</span> <span class="message"> Film festivals used to be
										do-or-die moments for movie makers. They were where... </span>
							</a></li>
							<li><a> <span class="image"> <img
										src="images/img.jpg" alt="Profile Image" />
								</span> <span> <span>John Smith</span> <span class="time">3
											mins ago</span>
								</span> <span class="message"> Film festivals used to be
										do-or-die moments for movie makers. They were where... </span>
							</a></li>
							<li><a> <span class="image"> <img
										src="images/img.jpg" alt="Profile Image" />
								</span> <span> <span>John Smith</span> <span class="time">3
											mins ago</span>
								</span> <span class="message"> Film festivals used to be
										do-or-die moments for movie makers. They were where... </span>
							</a></li>
							<li><a> <span class="image"> <img
										src="images/img.jpg" alt="Profile Image" />
								</span> <span> <span>John Smith</span> <span class="time">3
											mins ago</span>
								</span> <span class="message"> Film festivals used to be
										do-or-die moments for movie makers. They were where... </span>
							</a></li>
							<li>
								<div class="text-center">
									<a> <strong>See All Alerts</strong> <i
										class="fa fa-angle-right"></i>
									</a>
								</div>
							</li>
						</ul></li>
				</ul>
			</nav>
		</div>

	</div>
	<!-- /top navigation -->




</security:authorize>

<!-- </ul> -->


