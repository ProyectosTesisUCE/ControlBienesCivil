<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>My Control Site | Administración</title>
<spring:url value="" var="urlPublic" />
<spring:url value="/" var="urlRoot" />
<link href="${urlPublic}/images/logouce.ico.ico" type="image/x-icon"
	rel="shortcut icon" />
<link href="${urlPublic}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
<link href="${urlPublic}/css/my-style.css" rel="stylesheet">


</head>

<body>

	<jsp:include page="includes/menu.jsp"></jsp:include>


	<div class="container theme-showcase" role="main">

		<hr class="featurette-divider">
		<div class="jumbotron center">
			<div class="titulo">
				<h3>Administración del Sistema</h3>
			</div>

			<p style="text-transform: uppercase">
				Bienvenido(a) <a class="glyphicon glyphicon-user"></a>
				<sec:authentication property="principal.username" />

			</p>
			<p style="text-transform: uppercase">
				Usuario:
				<sec:authentication property="principal.authorities" />
			</p>
		</div>

		<hr class="featurette-divider">

		<jsp:include page="includes/footer.jsp"></jsp:include>
	</div>
	<!-- /container -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script>
	<script>
		function goBack() {
			window.history.back();
		}
	</script>
</body>
</html>
