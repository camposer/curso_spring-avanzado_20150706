<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
	<script src="/ejercicio2/js/jquery.js"></script>
	<meta charset="utf-8">
	<script>
		var consultar = function() {
			$.ajax($("#url").val(), {
				type : 'GET',
				dataType : 'json'
			}).done(function(resp) {
				$("#respuesta").html(JSON.stringify(resp));
			});
		};
	
		var eliminar = function() {
			$.ajax($("#url").val(), {
				type : 'DELETE',
				dataType : 'json'
			}).done(function(resp) {
				$("#respuesta").html(JSON.stringify(resp));
			});
		};
	
		var agregar = function() {
			$.ajax($("#url").val(), {
				type : 'POST',
				data : $("#mensaje").val(),
				contentType : 'application/json', // Tipo de dato enviado
				dataType : 'json'
			}).done(function(resp) {
				$("#respuesta").html(JSON.stringify(resp));
			});
		};
	
		var modificar = function() {
			$.ajax($("#url").val(), {
				type : 'PUT',
				data : $("#mensaje").val(),
				contentType : 'application/json', // Tipo de dato enviado
				dataType : 'json'
			}).done(function(resp) {
				$("#respuesta").html(JSON.stringify(resp));
			});
	
		};
	
		$(document).ready(function() {
			$("#get").click(consultar);
			$("#delete").click(eliminar);
			$("#post").click(agregar);
			$("#put").click(modificar);
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
				<label for="param" class="col-sm-2 control-label">URL</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" placeholder="/ejercicio2/api/personas" id="url" />
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
					<button type="button" class="btn btn-default" id="get">GET</button>
					<button type="button" class="btn btn-default" id="post">POST</button>
					<button type="button" class="btn btn-default" id="put">PUT</button>
					<button type="button" class="btn btn-default" id="delete">DELETE</button>
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
