#Ejercicio3

1. Crear un advice (tipo around) que deje una traza de log cuando se ejecute cualquier método del DAO de persona.
	- Debe implementar una clase que gestione las trazas (logger) y otra que implemente el advice.
	- El logger debe recibir como parámetro la ubicación del log
	- El logger debe imprimir al menos nombre del método, parámetros y marca de tiempo

VER: http://www.mkyong.com/spring/spring-aop-examples-advice/

2. Implementar una anotación CustomLog que utilice el CustomLogger desarrollado en 1.

VER: http://blog.javaforge.net/post/76125490725/spring-aop-method-interceptor-annotation
