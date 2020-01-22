<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Control de Bienes Site | Bienvenido</title>
<spring:url value="" var="urlPublic" />

<link href="${urlPublic}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
<link href="${urlPublic}/images/logouce.ico.ico" type="image/x-icon" rel="shortcut icon"/>
</head>

<body>

	<jsp:include page="../includes/menu.jsp"></jsp:include>

	<div class="container theme-showcase" role="main">

		<div class="panel panel-danger">
			<div class="panel-heading">
				<h3 class="panel-title">Error 404.</h3>
			</div>
			<div class="panel-body">
				<img src="${urlPublic}/images/error.png" width="48" height="48"
					class="center">
				<h4>La página solicitada no existe!</h4>
				<br>
				<button class="btn btn-success" onclick="goBack()">REGRESAR</button>
			</div>
		</div>

		<jsp:include page="../includes/footer.jsp"></jsp:include>

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