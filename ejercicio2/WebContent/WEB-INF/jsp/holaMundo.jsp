<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Hola mundo!</title>
</head>
<body>
	Hola mundo!<br>
	
	Hola <%= request.getAttribute("nombre") %>
</body>
</html>