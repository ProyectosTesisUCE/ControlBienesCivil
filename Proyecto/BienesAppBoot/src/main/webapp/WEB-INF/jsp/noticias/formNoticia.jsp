<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Creación de Notificaciones</title>
<spring:url value="" var="urlPublic" />
<spring:url value="/noticias/save" var="urlForm" />
<spring:url value="/noticias/cancel" var="urlCancel"></spring:url>

<link href="${urlPublic}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
<link href="${urlPublic}/images/logouce.ico.ico" type="image/x-icon"
	rel="shortcut icon" />

</head>

<body>

	<jsp:include page="../includes/menu.jsp"></jsp:include>

	<div class="container theme-showcase" role="main">
		<c:if test="${not empty alerta}">
			<div class='alert alert-danger' role="alert">${alerta}</div>
		</c:if>
		
		<div class="page-header">
			<h3 class="blog-title">
				<span class="label label-info">Datos de la Notificación</span>
			</h3>
		</div>

		<form:form action="${urlForm}" method="POST" modelAttribute="noticia">
			<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<label for="titulo">Título</label>
						<form:hidden path="id" />
						<form:input class="form-control" path="titulo" id="titulo"
							required="required" autocomplete="off" />
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="estatus">Estatus</label>
						<form:select id="estatus" path="estatus" class="form-control">
							<form:option value="Activa">Activa</form:option>
							<form:option value="Inactiva">Inactiva</form:option>
						</form:select>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="form-group">
						<label for="detalle">Detalles</label>
						<form:textarea class="form-control" path="detalle" id="detalle"
							rows="10"></form:textarea>
					</div>
				</div>
			</div>
			<div class="btn-toolbar" role="toolbar">
				<div class="btn-group">
					<button type="submit" title="Guardar" class="btn btn-primary">
						<i class="fa fa-save"></i> Guardar
					</button>
				</div>

				<div class="btn-group">
					<a href="${urlCancel} " class="btn btn-danger" role="button"
						title="Cancelar"><i class="fa fa-window-close"></i> Cancelar</a>
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
	<script src="${urlPublic}/tinymce/tinymce.min.js"></script>
	<script>
		tinymce
				.init({
					selector : '#detalle',
					plugins : "textcolor, table lists code",
					toolbar : " undo redo | bold italic | alignleft aligncenter alignright alignjustify \n\
                    | bullist numlist outdent indent | forecolor backcolor table code"
				});
	</script>
</body>
</html>