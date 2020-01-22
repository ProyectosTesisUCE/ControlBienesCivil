<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="">
<title>Edición Usuarios</title>
<spring:url value="" var="urlPublic" />
<spring:url value="/usuarios/save" var="urlForm" />
<spring:url value="/usuarios/cancel" var="urlCancel"></spring:url>
<link href="${urlPublic}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
<link href="${urlPublic}/images/logouce.ico.ico" type="image/x-icon"
	rel="shortcut icon" />

<link href="${urlPublic}/css/my-style.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

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

		<div class="page-header">
			<h3 class="blog-title">
				<span class="label label-danger">Edición Usuario</span>
			</h3>
		</div>

		<div class="panel panel-default">

			<div class=" panel-body">
				<form:form action="${urlForm}" method="post"
					modelAttribute="usuario">
					<form:hidden path="id" />
					<br>
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label for="perfil" class="control-label">PERFÍL</label> <select
									id="perfil" name="perfil" class="form-control">
									<option value="ADMINISTRADOR">ADMINISTRADOR</option>
									<option value="ESTANDAR">ESTANDAR</option>
								</select>
							</div>
						</div>

						<div class="col-sm-6">
							<div class="form-group">
								<label for="activo" class="control-label">Estatus</label>
								<form:select id="activo" path="activo" class="form-control">
									<form:option value="1">Activo</form:option>
									<form:option value="0">Inactivo</form:option>
								</form:select>
							</div>
						</div>


					</div>

					<br>
					<div class="row">

						<div class="col-sm-6">
							<div class="form-group">
								<label for="cuenta">Cuenta</label>
								<form:input type="text" class="form-control" path="cuenta"
									id="cuenta" required="required" autocomplete="off" />
							</div>
						</div>

						<div class="col-sm-6">
							<div class="form-group">
								<label for="email">Email</label>
								<form:input type="email" class="form-control" path="email"
									id="email" placeholder="Correo electrónico" required="required"
									autocomplete="off"
									pattern="/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3,4})+$/"
									onKeyUp="javascript:validateMail('email')" />
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
			</div>
		</div>
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
	<script src="${urlPublic}/js/validarEmail.js" type="text/javascript"></script>

</body>
</html>
