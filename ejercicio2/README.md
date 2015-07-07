#Ejercicio2

1. Generar el modelo a partir de la BD (ejercicio2.sql)
	- Cumplimentar fichero classpath:META-INF/persistence.xml

2. Implementar los DAO y Servicios correspondientes a los modelos: persona y ordenador.
	- Agregar configuraciones de acceso a datos (entityManager, entityManagerFactory, transactionManager)
	- Agregar implementación genérica de DAO. Debe inyectar el entityManager y agregar la anotación @PersistenceContext
	- Implementar PersonaDaoImpl (con su interfaz correspondiente)
	- Implementar PersonaServiceImpl (con su interfaz correspondiente)

3. Completar la prueba de PersonaDao. Añadir: modificar, eliminar, obtener y listar. Puede guiarse con la prueba anterior. Importante: familiarizarse con el POM y la clase JpaConfig (@EnableTransactionManagement)

4. Implementar el CRUD+ para Persona
	- Implementar la lista (+) de personas (acción: /persona/index.do)
	- Implementar el agregar (C) de personas
	- Implementar el eliminar (D) de personas. Si tiene ordenadores asociados, estos deben borrarse previamente de forma automática.
	- Implementar el mostrar (R) de personas
	- Implementar el modificar (U) de personas

NOTA: Para ejecutar: http://localhost:8080/ejercicio2/persona/index.do

5. Implementar el CRUD+ para Ordenador. Seguir el mismo orden de implementación aplicado en CRUD+ de Persona.
	- BD
	- Modelo
	- DAO
	- Servicio
	- Front

6. Implementar API REST para Persona y Ordenador
