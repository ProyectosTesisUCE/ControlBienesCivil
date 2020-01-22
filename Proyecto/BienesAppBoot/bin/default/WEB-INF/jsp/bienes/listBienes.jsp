<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Listado de Bienes</title>
<spring:url value="" var="urlPublic" />
<spring:url value="/bienes/create" var="urlCreate" />
<spring:url value="/bienes/edit" var="urlEdit" />
<spring:url value="/bienes/delete" var="urlDelete" />
<spring:url value="/bienes/search" var="urlSearch" />
<spring:url value="/bienes/personalizado" var="urlPersonalizado" />
<spring:url value="/bienes" var="urlBienes" />
<spring:url value="/bienes/downloadTotal?type=excel" var="urlXLS" />
<spring:url value="/bienes/downloadTotal?type=pdf" var="urlPDF" />
<link href="${urlPublic}/images/logouce.ico.ico" type="image/x-icon"
	rel="shortcut icon" />
<link href="${urlPublic}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
<link href="${urlPublic}/css/my-style.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


</head>

<body>

	<jsp:include page="../includes/menu.jsp"></jsp:include>

	<div class="container theme-showcase" role="main">

		<h3>Listado de Bienes</h3>



		<c:if test="${mensaje!=null}">
			<div class='alert alert-success' role="alert">${mensaje}</div>
		</c:if>

		<c:if test="${alerta!=null}">
			<div class='alert alert-danger' role="alert">${alerta}</div>
		</c:if>

		<div class="btn-toolbar" role="toolbar">
			<div class="btn-group">
				<a href="${urlCreate}" class="btn btn-primary" role="button"
					title="Nuevo Bien"><i class="fa fa-plus" aria-hidden="true"></i>
					Nueva</a>
			</div>

			<div class="btn-group">
				<a href="${urlPDF} " class="btn btn-outline-dark" role="button"
					title="Reporte en Pdf">Reportes Pdf <i class="fa fa-file-pdf-o"
					style="font-size: 24px; color: red"></i></a>
			</div>
			<div class="btn-group">
				<a href="${urlXLS} " class="btn btn-outline-dark" role="button"
					title="Reporte en Excel">Reportes Excel <i
					class="fa fa-file-excel-o" style="font-size: 24px; color: green;"></i></a>
			</div>

			<div class="btn-group">
				<a href="${urlPersonalizado}" class="btn btn-danger" role="button"
					title="Reporte por Periodo">Reporte por Periodo</a>
			</div>


		</div>
		<br>


	</div>
	<!-- /container -->

	<div id="main-container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<form class="form-inline" action="${urlSearch}" method="POST">
					<div class="form-group">
						<input type="text" id="searchTerm" name="campo"
							placeholder="Lector Alta Nueva" onkeyup="doSearch()" required="required">
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<button type="submit" title="Buscar" class="btn btn-primary">Buscar</button>
				</form>
			</div>
			<div class="panel-body">

				<div class="table-responsive">
					<table id="datos"
						class=" table-rwd  table-hover table-striped table-bordered">
						<thead>
							<tr>
								<th>Alta Nueva </th>
								<th>Alta Anterior </th>
								<th>Serie</th>
								<th>Descripción</th>
								<th>Fecha de Ingreso</th>
								<th>Costo</th>
								<th>Vida Util</th>
								<th>Depreciación</th>
								<th>Fecha Garantía</th>
								<th>Color</th>
								<th>Material</th>
								<th>Asignado</th>
								<th>Causionado</th>
								<th>Estado</th>
								<th>Estatus</th>
								<th>Guarda Almacen</th>
								<th>Marca</th>
								<th>Modelo</th>
								<th>Tipo</th>
								<th>Opciones</th>
							</tr>
						</thead>
						<c:forEach items="${bienes.content}" var="bien">
							<tr>
								<td>${bien.alta }</td>
								<td>${bien.anterior }</td>
								<td>${bien.serie}</td>
								<td>${bien.descripcion}</td>
								<td><fmt:formatDate value="${bien.fecha_ingreso}"
										pattern="dd-MM-yyyy" /></td>
								<td>${bien.costo }</td>
								<td>${bien.vida_util }<samp>años</samp></td>
								<td>${bien.depreciacion }<samp>años</samp></td>
								<td><fmt:formatDate value="${bien.garantia}"
										pattern="dd-MM-yyyy" /></td>
								<td>${bien.color}</td>
								<td>${bien.material}</td>
								<td>${bien.detalle.asignado}</td>
								<td>${bien.detalle.causionado}</td>
								<c:choose>
									<c:when test="${bien.detalle.estado eq 'Bueno'}">
										<td><span class="label label-success">${bien.detalle.estado}</span></td>
									</c:when>
									<c:otherwise>
										<td><span class="label label-danger">${bien.detalle.estado}</span></td>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${bien.detalle.estatus eq 'Activo'}">
										<td><span class="label label-success">${bien.detalle.estatus}</span></td>
									</c:when>
									<c:otherwise>
										<td><span class="label label-danger">${bien.detalle.estatus}</span></td>
									</c:otherwise>
								</c:choose>

								<td>${bien.detalle.guarda_almacen}</td>
								<td>${bien.detalle.marca}</td>
								<td>${bien.detalle.modelo}</td>
								<td>${bien.detalle.tipo}</td>

								<td><a href="${urlEdit}/${bien.id}"
									class="btn btn-success btn-sm" role="button" title="Editar"><span
										class="glyphicon glyphicon-pencil"></span></a> <a
									href="${urlDelete}/${bien.id}"
									onclick='return confirm("¿Estas seguro?")'
									class="btn btn-danger btn-sm" role="button" title="Delete"><span
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
					href="${urlBienes}/indexPaginate?page=${bienes.number - 1 }">Anterior</a></li>
				<li><a
					href="${urlBienes}/indexPaginate?page=${bienes.number + 1 }">Siguiente</a></li>
			</ul>
		</nav>
	</div>
	
	
	<!-- /container -->
	<div class="container theme-showcase">

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
