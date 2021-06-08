# grupo-12-OO2-2021
Primera parte del TPC, Sistema integrador (versión web con bases de datos)

- Se creó la vista para el login haciendo referencia a los usuarios que hay ya precargados en la bd. Para testear el login, es necesario crear en la base de datos un perfil con el nombre "Admin" y un usuario asignado a este perfil, 
para loguearse, la clave debe estar encriptada con el metodo new BCryptPasswordEncoder().encode(contraseña) sino tira error.

- El nuevo usuario que se agrega, se carga con la clave encriptada en la bd.

- Dentro de la pestaña "Usuarios" se pueden agregar, eliminar o editar los usuarios que hay en la base de datos, ademas se puede generar un archivo PDF con el listado de usuarios.

- Dentro de la pestaña "Agregar Usuarios" se creo un formulario para poder agregar a un nuevo usuario. Con el boton "Registrar" se agrega un nuevo usuario a la BD redireccionando a la lista de usuarios para visualizarlo
- Un usuario con el perfil Admin, puede editar, agregar o eliminar un usuario o perfil, mientras que uno con el perfil Auditoria solo puede generar el reporte en un pdf, en caso de querer modificar algo saltara a la vista de error por falta de permiso.



