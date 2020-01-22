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
<title>Listado de Usuarios</title>
<spring:url value="" var="urlPublic" />
<spring:url value="/usuarios/edit" var="urlEdit" />
<spring:url value="/usuarios/delete" var="urlDelete" />
<spring:url value="/usuarios" var="urlUsuarios" />
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
				<span class="label label-danger">Listado de Usuarios</span> <br>
			</h2>
			<hr class="featurette-divider">
		</div>

		<label>Buscar:</label> <input id="searchTerm" type="text"
			onkeyup="doSearch()" /> <br> <br>

		<div class="table-responsive">
			<table id="datos"
				class="table table-hover table-striped table-bordered">
				<thead>
					<tr>
						<th>Nombres</th>
						<th>Apellidos</th>
						<th>Cuenta</th>
						<th>E-mail</th>
						<th>Estatus</th>

						<th>Opciones</th>
					</tr>
				</thead>
				<c:forEach items="${usuarios.content}" var="usuario">
					<tr>
						<td>${usuario.nombre}</td>
						<td>${usuario.apellido}</td>
						<td>${usuario.cuenta}</td>
						<td>${usuario.email}</td>
						<c:choose>
							<c:when test="${usuario.activo eq '1'}">
								<td><span class="label label-success">Activo</span></td>
							</c:when>
							<c:otherwise>
								<td><span class="label label-danger">Inactivo</span></td>
							</c:otherwise>
						</c:choose>


						<td><a href="${urlEdit}/${usuario.id}"
							class="btn btn-success btn-sm" role="button" title="Editar"><span
								class="glyphicon glyphicon-pencil"></span></a> <a
							href="${urlDelete}/${usuario.id}"
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
					href="${urlUsuarios}/indexPaginate?page=${usuarios.number - 1 }">Anterior</a></li>
				<li><a
					href="${urlUsuarios}/indexPaginate?page=${usuarios.number + 1 }">Siguiente</a></li>
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
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${urlPublic}/js/buscadorTabla.js" type="text/javascript"></script>
</body>
</html>
