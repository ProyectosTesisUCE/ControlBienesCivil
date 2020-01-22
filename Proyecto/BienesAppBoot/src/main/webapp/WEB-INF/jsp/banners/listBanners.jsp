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
<title>Listado de imagenes del banner</title>
<spring:url value="" var="urlPublic" />
<spring:url value="/banners/create" var="urlCreate" />
<spring:url value="/banners/edit" var="urlEdit" />
<spring:url value="/banners/delete" var="urlDelete" />
<spring:url value="/banners" var="urlBanners" />
<link href="${urlPublic}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
<link href="${urlPublic}/images/logouce.ico.ico" type="image/x-icon"
	rel="shortcut icon" />
<link href="${urlPublic}/css/my-style.css" rel="stylesheet">
</head>

<body>

	<jsp:include page="../includes/menu.jsp" />

	<div class="container theme-showcase" role="main">


		<c:if test="${mensaje!=null}">
			<div class='alert alert-success' role="alert">${mensaje}</div>
		</c:if>

		<c:if test="${alerta!=null}">
			<div class='alert alert-danger' role="alert">${alerta}</div>
		</c:if>
		
		<div class="col-lg-12">
			<h2 class="text text-center">
				<span class="label label-warning">Listado de imágenes del
					Banner</span> <br>
			</h2>
			<hr class="featurette-divider">
		</div>

		<a href="${urlCreate}" class="btn btn-primary" role="button"
			title="Nuevo Banner"><i class="fa fa-plus" aria-hidden="true"></i>
			Nuevo</a><br> <br> <label>Buscar:</label> <input
			id="searchTerm" type="text" onkeyup="doSearch()" /> <br> <br>

		<div class="table-responsive">
			<table id="datos"
				class="table table-hover table-striped table-bordered">
				<thead>
					<tr>
						<th>Título</th>
						<th>Fecha Publicación</th>
						<th>Nombre Archivo</th>
						<th>Estatus</th>
						<th>Opciones</th>
					</tr>
				</thead>
				<c:forEach items="${banners.content}" var="banner">
					<tr>
						<td>${banner.titulo}</td>
						<td><fmt:formatDate pattern="dd-MM-yyyy"
								value="${banner.fecha}" /></td>
						<td>${banner.archivo}</td>
						<c:choose>
							<c:when test="${banner.estatus eq 'Activo'}">
								<td><span class="label label-success">${banner.estatus}</span></td>
							</c:when>
							<c:otherwise>
								<td><span class="label label-danger">${banner.estatus}</span></td>
							</c:otherwise>
						</c:choose>

						<td><a href="${urlEdit}/${banner.id}"
							class="btn btn-success btn-sm" role="button" title="Editar"><span
								class="glyphicon glyphicon-pencil"></span></a> <a
							href="${urlDelete}/${banner.id}"
							onclick='return confirm("¿Estas seguro?")'
							class="btn btn-danger btn-sm" role="button" title="Eliminar"><span
								class="glyphicon glyphicon-trash"></span></a></td>
					</tr>
				</c:forEach>

			</table>
		</div>
		<nav aria-label="">
			<ul class="pager">
				<li><a
					href="${urlBanners}/indexPaginate?page=${banners.number - 1 }">Anterior</a></li>
				<li><a
					href="${urlBanners}/indexPaginate?page=${banners.number + 1 }">Siguiente</a></li>
			</ul>
		</nav>
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
	<script src="${urlPublic}/js/buscadorTabla.js" type="text/javascript"></script>
</body>
</html>
