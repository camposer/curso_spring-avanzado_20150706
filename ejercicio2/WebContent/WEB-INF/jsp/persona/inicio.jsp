<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Personas</title>

<link rel="stylesheet" href="/ejercicio2/css/bootstrap.css">
</head>
<body>
	<div class="container">
		<h1>Personas</h1>
		
		<form class="form-horizontal">
			<div class="form-group">
				<label for="nombre" class="col-sm-2 control-label">Nombre</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="nombre"
						placeholder="Nombre">
				</div>
			</div>
			
			<div class="form-group">
				<label for="apellido" class="col-sm-2 control-label">Apellido</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="apellido"
						placeholder="Apellido">
				</div>
			</div>
			
			<div class="form-group">
				<label for="fechaNacimiento" class="col-sm-2 control-label">Fecha de nacimiento</label>
				<div class="col-sm-10">
					<input type="date" class="form-control" id="fechaNacimiento"
						placeholder="Fecha de nacimiento">
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Guardar</button>
				</div>
			</div>
		</form>
	
		<hr>
		
		<table class="table table-striped">
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Apellido</th>
				<th>Fecha de nacimiento</th>
				<th>Mostrar</th>
				<th>Eliminar</th>
			</tr>
			<tr>
				<td>1</td>
				<td>Juan</td>
				<td>Pérez</td>
				<td>2012-05-02</td>
				<td><a href="#">mostrar</a></td>
				<td><a href="#">eliminar</a></td>
			</tr>
			<tr>
				<td>1</td>
				<td>Juan</td>
				<td>Pérez</td>
				<td>2012-05-02</td>
				<td><a href="#">mostrar</a></td>
				<td><a href="#">eliminar</a></td>
			</tr>
			<tr>
				<td>1</td>
				<td>Juan</td>
				<td>Pérez</td>
				<td>2012-05-02</td>
				<td><a href="#">mostrar</a></td>
				<td><a href="#">eliminar</a></td>
			</tr>
		</table>
	</div>

	<script src="/ejercicio2/js/bootstrap.js"></script>
</body>
</html>




