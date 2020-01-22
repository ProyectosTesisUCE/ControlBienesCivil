<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Listado de Asignaciones</title>

<!-- Asignando las peticiones en variables -->
<spring:url value="" var="urlPublic" />
<spring:url value="/asignaciones/create" var="urlAsignacion" />
<spring:url value="/asignaciones/edit" var="urlEdit" />
<spring:url value="/asignaciones/listarTodos" var="urlListarTodos"/>
<spring:url value="/asignaciones/delete" var="urlDelete" />
<spring:url value="/asignaciones" var="urlAsignaciones" />
<spring:url value="/asignaciones/search" var="urlSearch" />
<spring:url value="/asignaciones/downloadTotalDetalle?type=excel" var="urlXLSDetalle"></spring:url>
<spring:url value="/asignaciones/downloadTotalDetalle?type=pdf" var="urlPDFDetalle" />

<link href="${urlPublic}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
<link href="${urlPublic}/images/logouce.ico.ico" type="image/x-icon"
	rel="shortcut icon" />
<link href="${urlPublic}/css/my-style.css" rel="stylesheet">
</head>

<body>

	<jsp:include page="../includes/menu.jsp"></jsp:include>

	<div class="container theme-showcase" role="main">

		<c:if test="${mensaje!=null}">
			<div class='alert alert-success' role="alert">${mensaje}</div>
		</c:if>

		<c:if test="${alerta!=null}">
			<div class='alert alert-danger' role="alert">${alerta}</div>
		</c:if>

		<div class="col-lg-12">
			<h2 class="text text-center">
				<span class="label label-success">Listado de Asignaciones</span> <br>
			</h2>
			<hr class="featurette-divider">
		</div>

		<a href="${urlAsignacion}" class="btn btn-primary" role="button"
			title="Nueva Asignacion"><i class="fa fa-plus" aria-hidden="true"></i>
			Nueva Asignación</a><br> <br>


		<div class="btn-toolbar" role="toolbar">

			<div class="btn-group">
				<a href="${urlPDFDetalle} " class="btn btn-outline-dark"
					role="button" title="Reporte en Pdf"> Reportes Pdf <i
					class="fa fa-file-pdf-o" style="font-size: 24px; color: red"></i></a>
			</div>
			<div class="btn-group">
				<a href="${urlXLSDetalle} " class="btn btn-outline-dark"
					role="button" title="Reporte en Excel">Reportes Excel <i
					class="fa fa-file-excel-o" style="font-size: 24px; color: green;"></i></a>
			</div>

		</div>
		<br>

		<div class="panel panel-default">
			<div class="panel-heading">
				<form class="form-inline" action="${urlSearch}" method="POST">
					<div class="form-group">
						<input type="text" id="searchTerm" name="campo"
							placeholder="Lector Alta Nueva.." onkeyup="doSearch()"
							required="required">
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<button type="submit" title="Buscar" class="btn btn-primary">
						<i class="fa fa-search"></i> Buscar
					</button>
					<a href="${urlListarTodos} " class="btn btn-outline-dark"
					role="button" title="Reporte en Excel">Listar Todo </a>
				</form>

			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table id="datos"
						class="table table-hover table-striped table-bordered">
						<thead>
							<tr>
								<th>Alta Nueva</th>
								<th>Bien</th>
								<th>Ubicación</th>
								<th>Lugar</th>
								<th>Ingreso</th>
								<th>Actualización</th>
								<th>Opciones</th>
							</tr>
						</thead>
						<c:forEach items="${asignaciones.content}" var="asignacion">
							<tr>
								<td>${asignacion.bien.alta}</td>
								<td>${asignacion.bien.descripcion}</td>
								<td>${asignacion.estacion.ubicacion}</td>
								<td>${asignacion.estacion.lugar}</td>
								<td><fmt:formatDate value="${asignacion.registro}"
										pattern="dd-MM-yyyy" /></td>
								<td><fmt:formatDate value="${asignacion.actualizacion}"
										pattern="dd-MM-yyyy" /></td>

								<td><a href="${urlEdit}/${asignacion.id}"
									class="btn btn-success btn-sm" role="button" title="Editar"><span
										class="glyphicon glyphicon-pencil"></span></a> <a
									href="${urlDelete}/${asignacion.id}"
									class="btn btn-danger btn-sm" role="button" title="Eliminar"><span
										class="glyphicon glyphicon-trash"></span></a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		<nav aria-label="">
			<ul class="pager">
				<li><a
					href="${urlAsignaciones}/indexPaginate?page=${asignaciones.number - 1 }">Anterior</a></li>
				<li><a
					href="${urlAsignaciones}/indexPaginate?page=${asignaciones.number + 1 }">Siguiente</a></li>
			</ul>
		</nav>
		<hr class="featurette-divider">

		<jsp:include page="../includes/footer.jsp"></jsp:include>

	</div>
	<!-- /container -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

	<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
	<script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${urlPublic}/js/buscadorTabla.js" type="text/javascript"></script>
</body>
</html>
