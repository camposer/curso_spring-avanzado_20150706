<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Personas</title>

<link rel="stylesheet" href="/ejercicio2/css/bootstrap.css">
</head>
<body>

	<%@ include file="/WEB-INF/jsp/cabecera.jsp" %>
    
	<div class="container">
		<h1>Personas</h1>

		<form:form cssClass="form-horizontal" 
				action="guardar.do" method="post" commandName="personaForm">
			<form:hidden path="id"/>

			<s:hasBindErrors name="personaForm">		
				<div class="alert alert-danger" role="alert">
					<form:errors path="*"/>
				</div>	
			</s:hasBindErrors>

			<div class="form-group">
				<label for="nombre" class="col-sm-2 control-label">Nombre</label>
				<div class="col-sm-10">
					<form:input path="nombre" cssClass="form-control" placeholder="Nombre" />
				</div>
			</div>

			<div class="form-group">
				<label for="apellido" class="col-sm-2 control-label">Apellido</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="apellido" name="apellido" value="${personaForm.apellido}"
						placeholder="Apellido">
				</div>
			</div>

			<div class="form-group">
				<label for="fechaNacimiento" class="col-sm-2 control-label">Fecha
					de nacimiento</label>
				
				<div class="col-sm-10">
					<form:input path="fechaNacimiento" cssClass="form-control datepicker active" placeholder="Fecha de nacimiento" readonly="readonly" data-date-format="yyyy-mm-dd" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Guardar</button>
				</div>
			</div>
		</form:form>

		<hr>

		<table class="table table-striped">
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Apellido</th>
				<th>Fecha de nacimiento</th>
				<th>Ordenadores</th>
				<th>Mostrar</th>
				<th>Eliminar</th>
			</tr>
			<c:forEach var="p" items="${personas}">
				<tr>
					<td>${p.id}</td>
					<td>${p.nombre}</td>
					<td>${p.apellido}</td>
					<td>${p.fechaNacimiento}</td>
					<td>
						<c:forEach var="o" items="${p.ordenadores}">
							${o.nombre} - ${o.serial}<br>
						</c:forEach>
					</td>
					<td><a href="mostrar.do?id=${p.id}">mostrar</a></td>
					<td><a href="eliminar.do?id=${p.id}" onclick="return confirm('Eliminar?')">eliminar</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<script src="/ejercicio2/js/jquery.js"></script>
	<script src="/ejercicio2/js/bootstrap.js"></script>
	<script src="/ejercicio2/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript">
		$(function() {
			$('.datepicker').datepicker();
		});
	</script>
</body>
</html>




