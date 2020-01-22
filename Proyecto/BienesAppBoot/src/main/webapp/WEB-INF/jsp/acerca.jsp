<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>My Control Site | Bienvenido</title>
<spring:url value="" var="urlPublic" />
<link href="${urlPublic}/images/logouce.ico.ico" type="image/x-icon" rel="shortcut icon"/>
<link href="${urlPublic}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">

</head>

<body>

	<jsp:include page="includes/menu.jsp"></jsp:include>

	<div class="container theme-showcase" role="main">

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">ACERCA DE ESTA APLICACIÓN</h3>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-sm-3">
						<p class="text-center">
							<img class="img-rounded" src="${urlPublic}/images/logoUce.jpg"
								alt="Generic placeholder image" height="220">
						</p>
					</div>
					<div class="col-sm-9">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">APLICACIÓN WEB PARA EL CONTROL Y
									ADMINISTRACIÓN DE BIENES DE LOS LABORATORIOS DE CÓMPUTO DE LA
									CARRERA DE INGENIERÍA CIVIL</h3>
							</div>
							<div class="panel-body">

								<div class="alert alert-danger" role="alert">El sistema
									web de control de bienes de los laboratorios de computo de
									ingeniería civil agilitará el proceso de generación de reportes
									para distintas áreas de acuerdo con las necesidades requeridas.
									El acceso al sistema se lo manejará por medio de credenciales
									de usuario y contraseña que serán registradas dentro del
									sistema al momento de la recolección de datos. La
									administración del sistema y usuarios les corresponderá única y
									exclusivamente a los usuarios administradores.</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

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