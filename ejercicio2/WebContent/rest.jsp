<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
	<script src="/ejercicio2/js/jquery.js"></script>
	<meta charset="utf-8">
	<script>
		const
		URL_BASE = "/ejercicio2/api/personas";
	
		var obtenerTodos = function() {
			var settings = { // ParÃ¡metros de configuraciÃ³n
				type : 'GET',
				dataType : 'json' // Tipo de dato de retorno
			};
	
			var ajax = $.ajax(URL_BASE, settings); // PeticiÃ³n AJAX
	
			ajax.done(function(productos) { // Callback de respuesta
				$("#respuesta").html(JSON.stringify(productos));
			});
		};
	
		var obtener = function() {
			var id = $("#param").val();
	
			$.ajax(URL_BASE + "/" + id, {
				type : 'GET',
				dataType : 'json'
			}).done(function(producto) {
				$("#respuesta").html(JSON.stringify(producto));
			});
		};
	
		var eliminar = function() {
			var id = $("#param").val();
	
			$.ajax(URL_BASE + "/" + id, {
				type : 'DELETE'
			}).done(function(producto) {
				$("#respuesta").html("Elemento eliminado");
			});
		};
	
		var agregar = function() {
			var mensaje = $("#mensaje").val();
	
			$.ajax(URL_BASE, {
				type : 'POST',
				data : mensaje,
				contentType : 'application/json' // Tipo de dato enviado
			}).done(function(respuesta) {
				$("#respuesta").html("Elemento agregado");
			});
		};
	
		var modificar = function() {
			var mensaje = $("#mensaje").val();
			var id = $("#param").val();
	
			$.ajax(URL_BASE + "/" + id, {
				type : 'PUT',
				data : mensaje,
				contentType : 'application/json' // Tipo de dato enviado
			}).done(function(respuesta) {
				$("#respuesta").html("Elemento modificado");
			});
	
		};
	
		$(document).ready(function() {
			$("#obtenerTodos").click(obtenerTodos);
			$("#obtener").click(obtener);
			$("#eliminar").click(eliminar);
			$("#agregar").click(agregar);
			$("#modificar").click(modificar);
		});
	</script>
	<link rel="stylesheet" href="/ejercicio2/css/bootstrap.css">
		
</head>
<body>
	<%@ include file="/WEB-INF/jsp/cabecera.jsp"%>

	<div class="container">
		<h1>Probador Rest</h1>
		
		<form class="form-horizontal">
			<div class="form-group">
				<label for="param" class="col-sm-2 control-label">Parámetro URL</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" placeholder="Parámetro URL" id="param" />
				</div>
			</div>

			<div class="form-group">
				<label for="param" class="col-sm-2 control-label">Cuerpo petición</label>
				<div class="col-sm-10">
					<textarea class="form-control" placeholder="Cuerpo petición" id="mensaje"></textarea>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" class="btn btn-default" id="obtenerTodos">Obtener todos</button>
					<button type="button" class="btn btn-default" id="obtener">Obtener</button>
					<button type="button" class="btn btn-default" id="agregar">Agregar</button>
					<button type="button" class="btn btn-default" id="modificar">Modificar</button>
					<button type="button" class="btn btn-default" id="eliminar">Eliminar</button>
				</div>
			</div>
		</form>

		<hr>
				
		<div id="respuesta" class="container">
			Respuesta...
		</div>
	</div>
</body>
</html>
