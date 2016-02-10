<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!-- <div> -->
<!-- 	<a href="principal/index.do"> <img src="images/logo.png" alt="EscuelaFutbol" /></a> -->
<!-- </div> -->


<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="principal.page.admin" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="coach/listAll.do"><spring:message code="master.page.coach.listAll" /></a></li>
				</ul>
				<li><a href="coach/register.do"><spring:message code="master.page.register.coach" /> </a></li>
				
			
		</security:authorize>
		
		<security:authorize access="hasRole('PLAYER')">
			<li><a class="fNiv"><spring:message	code="principal.page.player" /></a>
				
				<li><a href="player/displayA.do"><spring:message code="master.page.display" /> </a></li>
				
			
		</security:authorize>
		
		<security:authorize access="hasRole('COACH')">
			<li><a class="fNiv"><spring:message	code="principal.page.coach" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="match/listAll.do"><spring:message code="master.page.match.listAll" /></a></li>
					<li><a href="match/coach/listFuture.do"><spring:message code="master.page.match.listFuture" /></a></li>	
					<li><a href="match/coach/listPast.do"><spring:message code="master.page.match.listPast" /></a></li>			
				</ul>
			</li>
			<li><a href="match/coach/create.do"><spring:message code="master.page.coach.createMatch" /> </a></li>
		</security:authorize>
		
		
		
		<security:authorize access="isAnonymous()">
		<body style="background: #F7F7F7;">
		<!-- banner area starts here -->
		
		<header class="top-header">
		<div class="container">
			<div class="row header-row">
				<div class="col-md-12">
					
					  <div class="container-fluid">
					    <!-- Brand and toggle get grouped for better mobile display -->
					    <div class="navbar-header">
					      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					        <span class="sr-only">Toggle navigation</span>
					        <span class="icon-bar"></span>
					        <span class="icon-bar"></span>
					        <span class="icon-bar"></span>
					      </button>
					    </div>
					    <!-- Collect the nav links, forms, and other content for toggling -->
					    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					      
					      <ul class="nav navbar-nav navbar-right">
					        <li><a href="security/login.do"><spring:message code="principal.page.login" /></a></li>
					        <li><a href="player/register.do"><spring:message code="master.page.register.player" /></a></li>
					        <li><a href="family/register.do"><spring:message code="master.page.register.family" /></a></li>
					     
				
					      </ul>
					    </div><!-- /.navbar-collapse -->
					  </div><!-- /.container-fluid -->
					
				</div>
			</div>
		</div>
	</header>

	<!-- banner area starts here -->
	<section class="banner text-center" id="sec1">
		<div class="container">
			<div class="row">
<!-- 			<br> -->
<!-- 			<br> -->
<!-- 			<br> -->
				<h3><spring:message code="texto.uno" /></h3>

				<h1><spring:message code="texto.dos" /></h1>

				<p><spring:message code="texto.tres" /> </p>
				
<!-- 					<img src="images/camp2.jpg" alt=""><br> -->
					<img src="images/pin.png" alt=""><br>

			</div>
		</div>
	</section><!-- end of banner section -->
				</body>
		

			

		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
					
					<li><a href="j_spring_security_logout"><spring:message code="principal.page.logout" /> </a></li>
			
		</security:authorize>
	</ul>
</div>

<div class="text-center">
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

