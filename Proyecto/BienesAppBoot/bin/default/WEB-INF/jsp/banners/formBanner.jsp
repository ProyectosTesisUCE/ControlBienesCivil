<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Creacion de imagenes del Banner</title>
<spring:url value="" var="urlPublic" />
<spring:url value="/banners/save" var="urlForm" />
<spring:url value="/banners/cancel" var="urlCancel"></spring:url>
<link href="${urlPublic}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
<link href="${urlPublic}/images/logouce.ico.ico" type="image/x-icon" rel="shortcut icon"/>
</head>

<body>

	<jsp:include page="../includes/menu.jsp" />

	<div class="container theme-showcase" role="main">
		<c:if test="${not empty alerta}">
			<div class='alert alert-danger' role="alert">${alerta}</div>
		</c:if>
		<h3 class="blog-title">
			<span class="label label-success">Datos de la imagen</span>
		</h3>

		<form:form action="${urlForm}" method="post"
			enctype="multipart/form-data" modelAttribute="banner">
			<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<label for="titulo">Titulo</label>

						<form:hidden path="id" />
						<form:input type="text" class="form-control" path="titulo"
							id="titulo" required="required" autocomplete="off" />
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="imagen">Imagen</label> <input type="file"
							id="archivoImagen" name="archivoImagen" />
						<form:hidden path="archivo" />
						<p class="help-block">Tamaño recomendado: 1140 x 250</p>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="estatus">Estatus</label>
						<form:select id="estatus" path="estatus" class="form-control">
							<form:option value="Activo">Activo</form:option>
							<form:option value="Inactivo">Inactivo</form:option>
						</form:select>
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

		<jsp:include page="../includes/footer.jsp" />

	</div>
	<!-- /container -->

	<!-- Bootstrap core JavaScript
      ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>
