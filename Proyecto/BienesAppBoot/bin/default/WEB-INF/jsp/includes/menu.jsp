<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<spring:url value="/" var="urlRoot"></spring:url>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Fixed navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">

		<sec:authorize access="isAnonymous()">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${urlRoot}">My Control Site</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">

					<li><a href="${urlRoot}about">Acerca <i class="fa fa-question-circle-o" aria-hidden="true" ></i></a></li>
					<li><a href="${urlRoot}formLogin"><i class="fa fa-sign-in" aria-hidden="true" style="color: blue"></i> Login</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</sec:authorize>

		<sec:authorize access="hasAnyAuthority('ADMINISTRADOR')">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${urlRoot}admin/index">My Control Site |
					Administración</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="${urlRoot}bienes/indexPaginate?page=0">Bienes</a></li>
					<li><a href="${urlRoot}estaciones/indexPaginate?page=0">Estaciones</a></li>
					<li><a href="${urlRoot}asignaciones/indexPaginate?page=0">Asignaciones</a></li>
					<li><a href="${urlRoot}noticias/indexPaginate?page=0">Notificaciones</a></li>
					<li><a href="${urlRoot}banners/indexPaginate?page=0">Banners</a></li>
					<li><a href="${urlRoot}usuarios/indexPaginate?page=0">Usuarios</a></li>
					<li><a href="${urlRoot}admin/logout"><i class="fa fa-sign-out" aria-hidden="true" style="color: red"></i> Salir</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</sec:authorize>

		<sec:authorize access="hasAnyAuthority('ESTANDAR')">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${urlRoot}admin/index">My Control Site |
					Administración</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="${urlRoot}noticias/indexPaginate?page=0">Notificaciones</a></li>
					<li><a href="${urlRoot}banners/indexPaginate?page=0">Banners</a></li>
					<li><a href="${urlRoot}admin/logout"><i class="fa fa-sign-out" aria-hidden="true" style="color: red"></i> Salir</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</sec:authorize>
	</div>
</nav>
</html>