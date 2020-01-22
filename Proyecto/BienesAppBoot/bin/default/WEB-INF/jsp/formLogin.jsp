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

<title>Login</title>
<spring:url value="" var="urlPublic" />
<spring:url value="/" var="urlRoot" />
<spring:url value="/usuarios/createNewUser" var="urlCreate" />
<spring:url value="/usuarios/changePassword" var="urlChange"></spring:url>

<link href="${urlPublic}/images/logouce.ico.ico" type="image/x-icon"
	rel="shortcut icon" />
<link href="${urlPublic}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
<link href="${urlPublic}/bootstrap/css/signin.css" rel="stylesheet">
<link href="${urlPublic}/css/my-style.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


</head>

<body>

	<jsp:include page="includes/menu.jsp"></jsp:include>


	<div class="container theme-showcase" role="main">
		<hr class="featurette-divider">
		<img src="${urlPublic}/images/login.png" width="136" height="136"
			class="center">
		<form class="form-signin" action="${urlRoot}login" method="post">

			<c:if test="${param.error!= null }">
				<img src="${urlPublic}/images/error.png" width="48" height="48"
					class="center">
				<h4 class=" centrar form-signin-heading" style="color: red">Acceso
					Denegado</h4>
			</c:if>

			<c:if test="${mensaje!=null}">
				<div class='alert alert-success' role="alert">${mensaje}</div>
			</c:if>

			<c:if test="${alerta!=null}">
				<div class='alert alert-danger' role="alert">${alerta}</div>
			</c:if>

			<h3 class="form-signin-heading">Control de Bienes Site |
				Administracion</h3>
			<label for="username" class="sr-only">
				Usuario</label> <input type="text" id="username" name="username"
				class="form-control" placeholder="Usuario" required autofocus
				autocomplete="off"> <br> <label for="password"
				class="sr-only">Contraseña</label> <input type="password"
				id="password" name="password" class="form-control"
				placeholder="Password" required autocomplete="off"> <input
				type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button title="Entrar" class="btn btn-lg btn-primary btn-block"
				type="submit">Entrar</button>

		</form>
	</div>
	<!-- /container -->
	<br>
	<br>
	<div class="container theme-showcase">
		<div class="centrar col-12 forgot">

			<p>
				<a title="Crear Usuario - Registrarse" href="${urlCreate}">Crear
					Usuario - Registrarse</a>
			<p>
				<a title="Olvidaste tu Contraseña ?" href="${urlChange}">Olvidaste
					tu Contraseña ?</a>
		</div>

		<hr class="featurette-divider">
		<br> <br> <br>
		<jsp:include page="includes/footer.jsp"></jsp:include>
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
