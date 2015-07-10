<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ejercicio3 - Spring Avanzado</title>

<link rel="stylesheet" href="/ejercicio3/css/bootstrap.css">
</head>
<body>
 	<%@ include file="/WEB-INF/jsp/cabecera.jsp" %>

	<div class="container">
		<h1>Ejercicio 2 - Spring Avanzado</h1>
		
		<p>Ejercicio que utiliza AOP</p>
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




