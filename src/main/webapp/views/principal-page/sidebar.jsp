
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
						<a href="index.html" class="site_title"><i class="fa fa-paw"></i>
							<span>Escuela Futbol</span></a>
					</div>
					<div class="clearfix"></div>

					<!-- menu prile quick info -->
					<div class="profile">
						<div class="profile_pic">
							<img src="images/logo.png" alt="..."
								class="img-circle profile_img">
						</div>
						<div class="profile_info">
							<span>Welcome,</span>
							<h2>Anthony Fernando</h2>
						</div>
					</div>
					<!-- /menu prile quick info -->

					<br />

					<!-- sidebar menu -->
					<div id="sidebar-menu"
						class="main_menu_side hidden-print main_menu">

						<div class="menu_section">
							<h3>General</h3>
							<ul class="nav side-menu">
								<li><a href="match/listAll.do"><spring:message
													code="master.page.match.listAll" /></a><i class="fa fa-home"></i> <spring:message
											code="master.page.display" /> <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu " style="display: none">
										<li><a href="match/listAll.do"><spring:message
													code="master.page.match.listAll" /></a></li>
										<li><a href="index2.html">Dashboard2</a></li>
										<li><a href="index3.html">Dashboard3</a></li>
									</ul></li>
								<li><a><i class="fa fa-edit"></i> Forms <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu" style="display: none">
										<li><a href="form.html">General Form</a></li>
										<li><a href="form_advanced.html">Advanced Components</a></li>
										<li><a href="form_validation.html">Form Validation</a></li>
										<li><a href="form_wizards.html">Form Wizard</a></li>
										<li><a href="form_upload.html">Form Upload</a></li>
										<li><a href="form_buttons.html">Form Buttons</a></li>
									</ul></li>
								<li><a><i class="fa fa-desktop"></i> UI Elements <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu" style="display: none">
										<li><a href="general_elements.html">General Elements</a></li>
										<li><a href="media_gallery.html">Media Gallery</a></li>
										<li><a href="typography.html">Typography</a></li>
										<li><a href="icons.html">Icons</a></li>
										<li><a href="glyphicons.html">Glyphicons</a></li>
										<li><a href="widgets.html">Widgets</a></li>
										<li><a href="invoice.html">Invoice</a></li>
										<li><a href="inbox.html">Inbox</a></li>
										<li><a href="calender.html">Calender</a></li>
									</ul></li>
								<li><a><i class="fa fa-table"></i> Tables <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu" style="display: none">
										<li><a href="tables.html">Tables</a></li>
										<li><a href="tables_dynamic.html">Table Dynamic</a></li>
									</ul></li>
								<li><a><i class="fa fa-bar-chart-o"></i> Data
										Presentation <span class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu" style="display: none">
										<li><a href="chartjs.html">Chart JS</a></li>
										<li><a href="chartjs2.html">Chart JS2</a></li>
										<li><a href="morisjs.html">Moris JS</a></li>
										<li><a href="echarts.html">ECharts </a></li>
										<li><a href="other_charts.html">Other Charts </a></li>
									</ul></li>
							</ul>
						</div>
						<div class="menu_section">
							<h3>Live On</h3>
							<ul class="nav side-menu">
								<li><a><i class="fa fa-bug"></i> Additional Pages <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu" style="display: none">
										<li><a href="e_commerce.html">E-commerce</a></li>
										<li><a href="projects.html">Projects</a></li>
										<li><a href="project_detail.html">Project Detail</a></li>
										<li><a href="contacts.html">Contacts</a></li>
										<li><a href="profile.html">Profile</a></li>
									</ul></li>
								<li><a><i class="fa fa-windows"></i> Extras <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu" style="display: none">
										<li><a href="page_404.html">404 Error</a></li>
										<li><a href="page_500.html">500 Error</a></li>
										<li><a href="plain_page.html">Plain Page</a></li>
										<li><a href="login.html">Login Page</a></li>
										<li><a href="pricing_tables.html">Pricing Tables</a></li>

									</ul></li>
								<li><a><i class="fa fa-laptop"></i> Landing Page <span
										class="label label-success pull-right">Coming Soon</span></a></li>
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
						</a> <a data-toggle="tooltip" data-placement="top" title="Logout">
							<span class="glyphicon glyphicon-off" aria-hidden="true"></span>
						</a>
					</div>
					<!-- /menu footer buttons -->
				</div>
			</div>


		
	



	<!-- end boostrap -->
</security:authorize>



<div>

	<a class="fNiv"></a>
	<!-- 	<ul id="jMenu"> -->
	<!-- Do not forget the "fNiv" class for the first level links !! -->
	<security:authorize access="hasRole('ADMIN')">

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">

			<ul class="nav navbar-nav navbar-right">
				<li class="arrow"></li>
				<li><a href="coach/listAll.do"><spring:message
							code="master.page.coach.listAll" /></a></li>

				<li><a href="coach/register.do"><spring:message
							code="master.page.register.coach" /> </a></li>
			</ul>
		</div>
	</security:authorize>

	<security:authorize access="hasRole('PLAYER')">


		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">

			<ul class="nav navbar-nav navbar-right">
				<li class="arrow"></li>
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
			</ul>
		</div>
	</security:authorize>

	<security:authorize access="hasRole('COACH')">
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">

			<ul class="nav navbar-nav navbar-right">
				<li class="arrow"></li>
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
			</ul>

		</div>
	</security:authorize>