
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<security:authorize access="isAuthenticated()">
	<!-- Boostrap -->





	<div class="col-md-3 left_col">
		<div class="left_col scroll-view">

			<div class="navbar nav_title" style="border: 0;">
				<a href="index.html" class="site_title"> <span><spring:message
							code="master.page.FootballScholl" /></span></a>
			</div>
			<div class="clearfix"></div>

			<!-- menu prile quick info -->
			<div class="profile">
				<div class="profile_pic">
					<img src="images/logo.png" alt="..." class="img-circle profile_img">
				</div>
				<div class="profile_info">
					<span>Welcome,</span>
					<h2>Anthony Fernando</h2>
				</div>
			</div>
			<!-- /menu prile quick info -->

			<br />

			<!-- sidebar menu -->
			<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">

				<div class="menu_section">
					<h3>
						<spring:message code="master.page.Options" />
					</h3>


					<ul class="nav side-menu">

						<security:authorize access="hasRole('COACH')">

							<li><a href="match/listAll.do"><spring:message
										code="master.page.match.listAll" /></a></li>
							<li><a href="match/coach/listFuture.do"><spring:message
										code="master.page.match.listFuture" /></a></li>
							<li><a href="match/coach/listPast.do"><spring:message
										code="master.page.match.listPast" /></a></li>
							<li><a href="match/coach/create.do"><spring:message
										code="master.page.coach.createMatch" /> </a></li>
							<li><a href="player/c/listPlayerSameCategoryCoach.do"><spring:message
										code="master.page.coach.listPlayerCategory" /> </a></li>
							<li><a href="recruitment/coach/listPast.do"><spring:message
										code="master.page.coach.recruitmentsListPast" /> </a></li>
							<li><a href="recruitment/coach/listFuture.do"><spring:message
										code="master.page.coach.recruitmentsListFuture" /> </a></li>
							<li><a href="squadra/coach/create.do"><spring:message
										code="master.page.coach.createSquadra" /> </a></li>
							<li><a href="squadra/coach/mysquadra.do"><spring:message
										code="master.page.coach.mySquadra" /> </a></li>
							<li><a href="folder/user/list.do"><spring:message
										code="master.page.user.myFolder" /> </a></li>
							<li><a href="coach/displayA.do"><spring:message
										code="master.page.display" /> </a></li>

						</security:authorize>

						<security:authorize access="hasRole('PLAYER')">

							<li><a href="player/displayA.do"><spring:message
										code="master.page.display" /> </a></li>
							<li><a href="match/listAll.do"><spring:message
										code="master.page.match.listAll" /></a></li>
							<li><a href="match/player/listFuture.do"><spring:message
										code="master.page.match.listFuture" /> </a></li>
							<li><a href="match/player/listPast.do"><spring:message
										code="master.page.match.listPast" /> </a></li>
							<li><a href="player/listPlayerSameCategory.do"><spring:message
										code="master.page.coach.listPlayerCategory" /> </a></li>
							<li><a href="folder/user/list.do"><spring:message
										code="master.page.user.myFolder" /> </a></li>

						</security:authorize>
						
						<security:authorize access="hasRole('FAMILY')">

							<li><a href="match/listAll.do"><spring:message
										code="master.page.match.listAll" /></a></li>
							<li><a href="match/family/listFuture.do"><spring:message
										code="master.page.match.listFuture" /> </a></li>
							<li><a href="match/family/listPast.do"><spring:message
										code="master.page.match.listPast" /> </a></li>
							
						</security:authorize>

						<security:authorize access="hasRole('ADMIN')">

							<li><a href="coach/listAll.do"><spring:message
										code="master.page.coach.listAll" /></a></li>

							<li><a href="coach/register.do"><spring:message
										code="master.page.register.coach" /> </a></li>

						</security:authorize>

					</ul>
				</div>

			</div>
			<!-- /sidebar menu -->

			<!-- /menu footer buttons -->
			<div class="sidebar-footer hidden-small">
				<a data-toggle="tooltip" data-placement="top" title="Settings">
					<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
				</a> <a data-toggle="tooltip" data-placement="top" title="FullScreen">
					<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
				</a> <a data-toggle="tooltip" data-placement="top" title="Lock"> <span
					class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
				</a> <a data-toggle="tooltip" data-placement="top" title="Logout"> <span
					class="glyphicon glyphicon-off" aria-hidden="true"></span>
				</a>
			</div>
			<!-- /menu footer buttons -->
		</div>
	</div>







	<!-- end boostrap -->
</security:authorize>




	<a class="fNiv"></a>
	<!-- 	<ul id="jMenu"> -->
	<!-- Do not forget the "fNiv" class for the first level links !! -->