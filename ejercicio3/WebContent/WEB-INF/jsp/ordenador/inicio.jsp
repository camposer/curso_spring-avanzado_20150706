<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ordenaadores</title>

<link rel="stylesheet" href="/ejercicio3/css/bootstrap.css">
</head>
<body>

	<%@ include file="/WEB-INF/jsp/cabecera.jsp" %>
    
	<div class="container">
		<h1>Ordenadores</h1>

		<form:form cssClass="form-horizontal" 
				action="guardar.do" method="post" commandName="ordenadorForm">
			<form:hidden path="id"/>

			<s:hasBindErrors name="ordenadorForm">		
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
				<label for="serial" class="col-sm-2 control-label">Serial</label>
				<div class="col-sm-10">
					<form:input path="serial" cssClass="form-control" placeholder="Serial" />
				</div>
			</div>

			<div class="form-group">
				<label for="duenio" class="col-sm-2 control-label">Dueño</label>
				<div class="col-sm-10">
					<select name="personaId">
						<option value=""></option>
						<c:forEach var="p" items="${personas}">
							<c:set var="selected" value=""/>
							<c:if test="${p.id == ordenadorForm.personaId}">
								<c:set var="selected" value="selected"/>
							</c:if>
							
							<option value="${p.id}" ${selected}>					
								${p.nombreCompleto}
							</option>
						</c:forEach>
					</select>
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
				<th>Serial</th>
				<th>Dueño</th>
				<th>Mostrar</th>
				<th>Eliminar</th>
			</tr>
			<c:forEach var="o" items="${ordenadores}">
				<tr>
					<td>${o.id}</td>
					<td>${o.nombre}</td>
					<td>${o.serial}</td>
					<td>${o.persona.nombreCompleto}</td>
					<td><a href="mostrar.do?id=${o.id}">mostrar</a></td>
					<td><a href="eliminar.do?id=${o.id}" onclick="return confirm('Eliminar?')">eliminar</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<script src="/ejercicio3/js/jquery.js"></script>
	<script src="/ejercicio3/js/bootstrap.js"></script>
	<script src="/ejercicio3/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript">
		$(function() {
			$('.datepicker').datepicker();
		});
	</script>
</body>
</html>




