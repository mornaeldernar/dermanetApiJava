# DermanetApiJava

Api para manejo de doctores, pacientes y sus diagnosticos

## Configuración

Se creó un proyecto con Spring initializr en Maven, Spring Boot 3.0.2
empaquetado Jar y version de java 17.

## Dependencias

- Spring Web
- Spring Data JPA
- Rest Repositories
- Validation
- MySql Driver
- Lombok
- Spring Security
- Map Struct
- Data format
- Mockito
- Jjwt

## Arquitectura

Se utiliza una arquitectura multicapa
- La capa SECURITY se utiliza para implementar la seguridad de la aplicación por medio de JWT en la cual valida que los usuario 
esten en la base de datos. La contraseña se guarda encriptada. Si el usuario está en la base de datos, regresa un token que se tiene que enviar en las 
peticiones del api como un Bearer Token. Si el token no es válido, regresa un 403 FORBBIDEN o 401 UNAUTHORIZED
- La capa ENTITY para las entidades en la base de datos
- la capa DTO para los datos que se van a entregar y recibir en las peticiones
- la capa mapper para mapear los datos entre entity y DTO
- la capa REPOSITORY para mandar las operaciones de guardar, buscar, eliminar, modificar a la base de datos
- La capa SERVICE para manejar la informacion que nos manden desde los controladores y validar que la info este correcta para mandarla a la capa repository
- la capa controller para recibir las peticiones por medio de post(Crear), get(buscar), put(modificar), delete(eliminar)


## Validaciones
Se utilizan anotaciones de validacion en las capas entity y dto para validar que la informacion que recibimos sea la esperada
algunas anotaciones que se utilizan son
@NotBlank - El campo no tiene que estar en blanco
@Email - El campo es de tipo email
@PositiveOrZero - El campo tiene que ser un numero mayor o igual a 0
@PastOrPresent - La fecha no puede ser en el futuro(fechas de nacimiento)

## Spring Data Rest
- Las entidades diagnostic, image, speciality y user tienen la anotacion @RestResource para que Spring Data rest nos cree los endpoints necesarios para las operaciones CRUD
- Las entidades Doctor, y Patient se manejan con controladores para tener un mejor manejo de las excepciones y saber cuales son los errores que nos impiden que realizar las operaciones, debido a que con spring security, todos los errores nos los manda como 401 Unauthorized
![excepciones.png](img%2Fexcepciones.png)

## Authorization y Authentication

- Se utiliza Spring Security con configuracion personalizada para implementar JWT
- Los endpoints de tipo DELETE y lo /user Solo pueden ser accesados por los ADMIN
- El endpoint /login cualquiera puede accesar para generar un Bearer token
- En los demás endpoints se necesita enviar un Bearer token en el encabezado de Authorization para poder acceder

## Pruebas
- se realizan pruebas unitarias y con selenium para probar la api
![tests.png](img%2Ftests.png)

![selenium.png](img%2Fselenium.png)
## Despliegue 
Se ejecuto la instrucción
- mvnw.cmd package

para genera el archivo target/api-0.0.1-SNAPSHOT.jar

para ejecutarlo se ejecuta la instruccion:
- java -jar target/guide-spring-boot-0.1.0.jar

## Open Liberty
- No se utiliza Open liberty porque el proyecto esta hecho para spring boot 3 y open liberty solo ejecuta proyectos spring boot 2