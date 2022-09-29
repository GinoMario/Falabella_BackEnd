PROYECTO DE PRUEBAS API TESTING FALABELLA

RETO: A partir del siguiente swagger:
https://petstore.swagger.io
Crear 8 casos de prueba funcionales para cualquier servicio que allí encuentres.

Este proyecto está implementado con:
    restassured + cucumber bdd + java
el cual testea la funcionalidad de las apis rest de Pets Store

Para ejecutar el proyecto, deberá abrir la terminal de intellijIdea y ejecutar la siguiente linea:

    mvn verify test

AL culminar la ejecución se generará un reporte en la siguiente ruta:

    /extendReport/report.html