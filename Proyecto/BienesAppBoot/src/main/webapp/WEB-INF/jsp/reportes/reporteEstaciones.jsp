<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="" var="urlPublic"></spring:url>
<spring:url value="/" var="urlRoot"></spring:url>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link href="${urlPublic}/images/logouce.ico.ico" type="image/x-icon"
	rel="shortcut icon" />

<title>Estaciones</title>

<link href="${urlPublic}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">

</head>

<body>


	<jsp:include page="../includes/menu.jsp"></jsp:include>

	<div class="container theme-showcase" role="main">

		<div class="col-lg-12">
			<h2 class="text text-center">
				<span class="label label-primary">Listado de Estaciones</span> <br>
			</h2>
			<hr class="featurette-divider">
		</div>

		<!-- Marketing messaging -->
		<div class="container marketing">

			<div class="row">

				<c:forEach items="${estaciones}" var="estacion">

					<div class="col-xs-12 col-sm-6 col-md-3">
						<img class="img-rounded"
							src="${urlPublic}/logos/${estacion.imagen}"
							alt="Generic placeholder image" width="200" height="150">
						<h4>${estacion.lugar}</h4>
						<h4>

							<span class="label label-default">${estacion.ubicacion}</span>

						</h4>
						<p>
							<a class="btn btn-sm btn-primary"
								href="/reportes/detail?idEstacion=${estacion.id}" role="button">Consulta
								Detalle &raquo;</a>
						</p>
					</div>
				</c:forEach>
			</div>
		</div>

	</div>
	<!-- /container -->

	<hr class="featurette-divider">

	<jsp:include page="../includes/footer.jsp"></jsp:include>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
