<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<spring:url value="" var="urlPublic"></spring:url>
<spring:url value="/asignaciones/save" var="urlForm"></spring:url>
<spring:url value="/asignaciones/cancel" var="urlCancel"></spring:url>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="">
<title>Asignar Bienes</title>

<link href="${urlPublic}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link href="${urlPublic}/images/logouce.ico.ico" type="image/x-icon"
	rel="shortcut icon" />
</head>

<body>

	<jsp:include page="../includes/menu.jsp"></jsp:include>

	<div class="container theme-showcase" role="main">

		<h3 class="blog-title">
			<span class="label label-success">Datos Biens - Estacion</span>
		</h3>

		<spring:hasBindErrors name="bien">
			<div class='alert alert-danger' role='alert'>
				Por favor corrija los siguientes errores:
				<ul>
					<c:forEach var="error" items="${errors.allErrors}">
						<li><spring:message message="${error}" /></li>
					</c:forEach>
				</ul>
			</div>
		</spring:hasBindErrors>

		<form:form action="${urlForm}" method="post"
			enctype="multipart/form-data" modelAttribute="bienes_Estaciones">

			<div class="row">
				<div class="col-sm-3">
					<div class="form-group">
						<label for="id_bien" class="control-label">Alta Nueva del
							Bien</label>
						<form:hidden path="id" />

						<form:input type="text" path="bien.alta" id="id_bien"
							readonly="readonly" class="form-control" />
						<form:hidden path="bien.alta" />
						<!--<form:select id="id_bien" path="bien.id" class="form-control"
							items="${bienes}" itemLabel="alta" itemValue="id" /> -->
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="id_estacion" class="control-label">Lugar</label>
						<form:select id="id_estacion" path="estacion.id"
							class="form-control" items="${estaciones}" itemLabel="lugar"
							itemValue="id" />
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="registro">Fecha registro</label>
						<form:input type="text" class="form-control" path="registro"
							id="registro" readonly="readonly" />
					</div>
				</div>

			</div>

			<div class="btn-toolbar" role="toolbar">
				<div class="btn-group">
					<button type="submit" title="Guardar" class="btn btn-primary">Guardar</button>
				</div>

				<div class="btn-group">
					<a href="${urlCancel} " class="btn btn-danger" role="button"
						title="Cancelar">Cancelar</a>
				</div>

			</div>
		</form:form>

		<hr class="featurette-divider">

		<jsp:include page="../includes/footer.jsp"></jsp:include>

	</div>
	<!-- /container -->

	<!-- Bootstrap core JavaScript
		================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</body>
</html>