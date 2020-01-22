<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<spring:url value="" var="urlPublic"></spring:url>
<spring:url value="/bienes/save" var="urlForm"></spring:url>
<spring:url value="/bienes/cancel" var="urlCancel"></spring:url>
<spring:url value="/bienes/busquedaPrevia" var="urlBusquedaPrevia" />

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Creación de Bienes</title>

<link href="${urlPublic}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
<link href="${urlPublic}/images/logouce.ico.ico" type="image/x-icon"
	rel="shortcut icon" />

<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

</head>

<body>

	<jsp:include page="../includes/menu.jsp"></jsp:include>

	<div class="container theme-showcase" role="main">


		<div class="page-header">
			<form class="form-inline" action="${urlBusquedaPrevia}" method="POST">
				<div class="form-group">
					<input type="text" id="searchTerm" name="campo"
						class="form-control" placeholder="Lector Alta Nueva"
						onkeyup="doSearch()" required="required">
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<button type="submit" title="Buscar" class="btn btn-primary">
					<i class="fa fa-search"></i> Buscar
				</button>
			</form>


		</div>

		<c:if test="${not empty alerta}">
			<div class='alert alert-danger' role="alert">${alerta}</div>
		</c:if>

		<c:if test="${mensaje!=null}">
			<div class='alert alert-success' role="alert">${mensaje}</div>
		</c:if>

		<div class="page-header">
			<h3 class="blog-title">
				<span class="label label-default">Datos del Bien</span>
			</h3>
		</div>

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

		<form:form action="${urlForm}" method="post" modelAttribute="bien">
			<div class="row">
				<div class="col-sm-3">
					<div class="form-group">
						<label for="alta">Alta Nueva</label>
						<form:hidden path="id" />
						<form:hidden path="detalle.id" />
						<form:input type="text" class="form-control" path="alta" id="alta"
							autocomplete="off" required="required"
							placeholder="Al no tener, Escribir la Descripción" />
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="anterior">Alta Anterior</label>
						<form:input type="text" class="form-control" path="anterior"
							id="anterior" autocomplete="off" required="required"
							placeholder="Al no tener, Escribir la Descripción" />
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="serie">Serie</label>
						<form:input type="text" class="form-control" path="serie"
							id="serie" required="required" />
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="descripcion">Descripción</label>
						<form:input type="text" class="form-control" path="descripcion"
							id="descripcion" required="required" />
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="costo">Costo</label>
						<form:input type="text" class="form-control" path="costo"
							id="costo" required="required" autocomplete="off"
							pattern="^[0-9]+([.][0-9]+)?$" />
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="vida_util" class="control-label">Vida Útil</label>
						<form:select id="vida_util" path="vida_util" class="form-control">
							<form:option value="1">1 Años</form:option>
							<form:option value="2">2 Años</form:option>
							<form:option value="3">3 Años</form:option>
							<form:option value="5">5 Años</form:option>
							<form:option value="10">10 Años</form:option>
						</form:select>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="garantia">Fin de Garantia</label>
						<form:input type="text" class="form-control" path="garantia"
							id="garantia" required="required" autocomplete="off" />
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="color">Color</label>
						<form:input type="text" class="form-control" path="color"
							id="color" required="required"
							placeholder="Plomo - Azul - Negro -?"
							pattern="|^[a-zA-Z]+(\s*[a-zA-Z]*)*[a-zA-Z]+$|" />
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="material">Material</label>
						<form:input type="text" class="form-control" path="material"
							id="material" required="required"
							placeholder="Plástico - Metal - ?"
							pattern="^[a-zA-ZÀ-ÿ\u00f1\u00d1]+(\s*[a-zA-ZÀ-ÿ\u00f1\u00d1]*)*[a-zA-ZÀ-ÿ\u00f1\u00d1]+$" />
					</div>
				</div>

			</div>


			<!-- Para la entidad Detalle -->
			<div class="page-header">
				<h3 class="blog-title">
					<span class="label label-default">Detalles</span>
				</h3>
			</div>

			<div class="row">
				<div class="col-sm-3">
					<div class="form-group">
						<label for="asignado" class="control-label">Quien Usa</label>
						<form:select id="asignado" path="detalle.asignado"
							class="form-control" items="${usuario}">
						</form:select>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="marca">Marca</label>
						<form:input type="text" class="form-control" path="detalle.marca"
							id="marca" required="required" placeholder="Hp - Accer - ?"
							pattern="|^[a-zA-Z]+(\s*[a-zA-Z]*)*[a-zA-Z]+$|" />
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="modelo">Modelo</label>
						<form:input type="text" class="form-control" path="detalle.modelo"
							id="modelo" required="required" />
					</div>
				</div>


				<div class="col-sm-3">
					<div class="form-group">
						<label for="estado" class="control-label">Estado</label>
						<form:select id="estado" path="detalle.estado"
							class="form-control">
							<form:option value="Bueno"> Bueno</form:option>
							<form:option value="Malo"> Malo</form:option>
						</form:select>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="estatus" class="control-label">Estatus</label>
						<form:select id="estatus" path="detalle.estatus"
							class="form-control">
							<form:option value="Activo"> Activo</form:option>
							<form:option value="Baja"> Baja</form:option>
						</form:select>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="tipo" class="control-label">Tipo</label>
						<form:select id="tipo" path="detalle.tipo" class="form-control"
							items="${tipo}">
						</form:select>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="guarda_almacen">Guarda Almacén</label>
						<form:input type="text" class="form-control"
							path="detalle.guarda_almacen" id="guarda_almacen"
							required="required" placeholder="Solo Letras"
							pattern="^[a-zA-ZÀ-ÿ\u00f1\u00d1]+(\s*[a-zA-ZÀ-ÿ\u00f1\u00d1]*)*[a-zA-ZÀ-ÿ\u00f1\u00d1]+$" />
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="causionado">Caucionado</label>
						<form:input type="text" class="form-control"
							path="detalle.causionado" id="causionado" required="required"
							placeholder="Solo Letras"
							pattern="^[a-zA-ZÀ-ÿ\u00f1\u00d1]+(\s*[a-zA-ZÀ-ÿ\u00f1\u00d1]*)*[a-zA-ZÀ-ÿ\u00f1\u00d1]+$" />
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
	<script>
		$(function() {
			$("#garantia").datepicker({
				dateFormat : 'dd-mm-yy'
			});
		});
	</script>

</body>
</html>
