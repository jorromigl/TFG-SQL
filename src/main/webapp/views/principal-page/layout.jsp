<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<base
	href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="shortcut icon" href="favicon.ico" />



<script src="../assets/js/ie8-responsive-file-warning.js"></script>


<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->

<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>


<!-- Bootstrap core CSS -->

<link href="styles/style.css" rel="stylesheet">
<link href="styles/bootstrap.min.css" rel="stylesheet">

<link href="styles/font-awesome.min.css" rel="stylesheet">
<link href="styles/animate.min.css" rel="stylesheet">



<!-- Custom styling plus plugins -->
<link href="styles/custom.css" rel="stylesheet">
<link href="styles/green.css" rel="stylesheet">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<script type="text/javascript" src="scripts/jquery.min.js"></script>


<!-- END Bootstrap core CSS -->

<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript" src="scripts/jquery-ui.js"></script>
<script type="text/javascript" src="scripts/jmenu.js"></script>


<link rel="stylesheet" href="styles/common.css" type="text/css">
<link rel="stylesheet" href="styles/jmenu.css" media="screen"
	type="text/css" />
<link rel="stylesheet" href="styles/displaytag.css" type="text/css">

<title><tiles:insertAttribute name="title" ignore="true" /></title>

<script type="text/javascript">
	$(document).ready(function() {
		$("#jMenu").jMenu();
	});

	function askSubmission(msg, form) {
		if (confirm(msg))
			form.submit();
	}
</script>

<script type="text/javascript">
	function relativeRedir(loc) {
		var b = document.getElementsByTagName('base');
		if (b && b[0] && b[0].href) {
			if (b[0].href.substr(b[0].href.length - 1) == '/'
					&& loc.charAt(0) == '/')
				loc = loc.substr(1);
			loc = b[0].href + loc;
		}
		window.location.replace(loc);
	}
</script>

</head>
<security:authorize access="isAuthenticated()">
<a class="fNiv"></a>

	<body class="nav-md">
		<div class="container body">


			<div class="main_container">
				<div>
					<tiles:insertAttribute name="header" />
				</div>

				<div>
					<tiles:insertAttribute name="sidebar" />
				</div>

				<div class="right_col" role="main">

					<div class="page-title">
						<tiles:insertAttribute name="title" />

					</div>
					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<h1></h1>
							<tiles:insertAttribute name="body" />
							<jstl:if test="${message != null}">
								<br />
								<span class="message"><spring:message code="${message}" /></span>
							</jstl:if>
						</div>
					</div>
				</div>
				<div>
					<tiles:insertAttribute name="footer" />
				</div>
			</div>
		</div>

	</body>
</security:authorize>

<security:authorize access="isAnonymous()">
<a class="fNiv"></a>

	<body class="nav-md">

		<div>
			<tiles:insertAttribute name="header" />
		</div>
					<h1><tiles:insertAttribute name="title" /></h1>
					<tiles:insertAttribute name="body" />
					<jstl:if test="${message != null}">
						<br />
						<span class="message"><spring:message code="${message}" /></span>
					</jstl:if>
		
		<div>
			<tiles:insertAttribute name="footer" />
		</div>


	</body>
</security:authorize>
</html>
