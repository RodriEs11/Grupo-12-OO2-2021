# Requisitos
- Spring Suite Tool 4
- Maven
- Tener creado un schema en el servidor MySql llamado 'grupo-12-bbd-oo2-2021'

# Instrucciones
- Abrir una terminal en el mismo directorio y colocar la siguiente instrucción
> *mvn clean* 
- Abrir el proyecto en Spring Suite Tool 4
- Hacer el deploy del servidor
- Probar el servidor en la siguiente dirección
> localhost:8080
- Una vez hecho el deployment, se nos creara en nuestra base de datos *grupo-12-bbd-oo2-2021* las tablas necesarias automáticamente, es necesario cargar manualmente en la base de datos los tipos de perfiles que se registrarán (admin, user, auditor, etc...)

# Git-Hub Page
Podremos visualizar el contenido de nuestra base de datos en el siguiente enlace
https://rodries11.github.io/grupo-12-OO2-2021/

Siguiendo la lista de parametros es posible visualizar todo desde un link estático, obtenido por un codigo QR

Los parámetros son:
- nombre=
- dni=
- desde=
- hasta=
- tipoPermiso = (1 Permiso diario / 2 Permiso periodo)
- motivo=
- dias=
- vacaciones= (1 SI / 0 NO )
- dominio=
- vehiculo=

Ejemplo: https://rodries11.github.io/grupo-12-OO2-2021/?nombre=rodrigo+espindola&?dni=12345678&?desde=01/01/2020&?hasta=31/12/2020&tipoPermiso=2&motivo=Prueba&dias=300&vacaciones=0
