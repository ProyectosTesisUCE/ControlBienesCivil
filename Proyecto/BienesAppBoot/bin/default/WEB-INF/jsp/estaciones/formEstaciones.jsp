<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<spring:url value="" var="urlPublic"></spring:url>
<spring:url value="/estaciones/save" var="urlForm"></spring:url>
<spring:url value="/estaciones/cancel" var="urlCancel"></spring:url>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="">
<title>Creación de Ubicaciones</title>

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


		<c:if test="${not empty alerta}">
			<div class='alert alert-danger' role="alert">${alerta}</div>
		</c:if>

		<h3 class="blog-title">
			<span class="label label-success">Datos de la Estacion</span>
		</h3>


		<spring:hasBindErrors name="estacion">
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
			enctype="multipart/form-data" modelAttribute="estacion">



			<div class="row justify-content-center ">

				<div class="col-sm-3">
					<div class="form-group">
						<label for="ubicacion">Ubicacion</label>
						<form:hidden path="id" />
						<form:input type="text" class="form-control" path="ubicacion"
							id="ubicacion" placeholder="Edificio de cómputo"
							required="required" />
					</div>
				</div>


				<div class="col-sm-3">
					<div class="form-group">
						<label for="lugar">Lugar</label>
						<form:input type="text" class="form-control" path="lugar"
							id="lugar" required="required" autocomplete="off" />
					</div>
				</div>


				<div class="col-sm-3">
					<div class="form-group">
						<label for="imagen">Imagen</label> <input type="file"
							id="archivoImagen" name="archivoImagen" />
						<form:hidden path="imagen" />
						<p class="help-block">Imagen de estación</p>
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
			<br>
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
