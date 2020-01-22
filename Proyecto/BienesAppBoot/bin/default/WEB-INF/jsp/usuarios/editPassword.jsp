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
<title>Usuarios</title>
<spring:url value="" var="urlPublic" />
<spring:url value="/usuarios/savePassword" var="urlForm" />
<spring:url value="/usuarios/cancelNewUser" var="urlCancel"></spring:url>
<spring:url value="/usuarios/buscar" var="urlBuscar"></spring:url>
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

		<c:if test="${not empty alerta}">
			<div class='alert alert-danger' role="alert">${alerta}</div>
		</c:if>

		<c:if test="${mensaje!=null}">
			<div class='alert alert-success' role="alert">${mensaje}</div>
		</c:if>

		<h3 class=" blog-title">
			<span class="label label-success">Cambio de Contraseña</span>

		</h3>

		<div class="panel panel-default">
			<div class="panel-heading">
				<form:form action="${urlBuscar}" method="get">

					<label>Cuenta</label>
					<input id="cuenta" name="cuenta1" type="text" placeholder="Cuenta"
						required="required" autocomplete="off">
					<label>E-mail</label>
					<input id="email" name="email1" type="email"
						placeholder="Correo electrónico" required="required"
						autocomplete="off" placeholder="aaaaa@uce.edu.ec"
						pattern="/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3,4})+$/"
						onKeyUp="javascript:validateMail('email')">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<button type="submit" title="Buscar" class="btn btn-primary">Buscar</button>
				</form:form>
			</div>
			<div class="panel-body">

				<form:form action="${urlForm}" method="post"
					modelAttribute="usuario">
					<form:hidden path="id" />

					<br>
					<div class="row">

						<div class="col-sm-6">
							<div class="form-group">
								<label class="centrar" for="pwd">Password</label>
								<form:input type="text" class="form-control" path="pwd"
									id="pwd" required="required" />
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
