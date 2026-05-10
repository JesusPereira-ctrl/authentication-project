# Api Autenticacion / Autorizacion

Esta api esta creada para probar mis conocimientos con Spring Security añadiendo seguridad por roles

## Tecnologias Usadas

- Java 25
- Spring Framework
- PostgreSQL
- Spring Data JPA
- Spring Security
- JJWT libreria para los JWT
- Lombok
- Bean Validation
- Spring MVC para las rutas y servidor emdebido
- Docker

## Levantar Proyecto

Cambiar variables docker-compose o aplication.properties en caso de tener problemas con los puertos usados ya sea el
8080 para el proyecto o el 5432 para la base de datos

```bash
docker compose up -d
```

## Rutas

### Ruta para registrarse

```http request
POST http:/localhost:8080/auth/register
```

Json necesario para cumplir la peticion

```json
{
  "firstName": "nombre del usuario",
  "lastName": "apellido del usuario",
  "email": "correo@correo.com",
  "password": "contraseña_secreta"
}
```

esta request toma los datos para guardarlos en la base de datos siguiendo la buena practica de guardar contraseñas
encriptadas con BCryptEncoder aparte devuelve un JWT con el identificador y el role por defecto es USER

### Ruta de Login

```http request
POST http://localhost:8080/auth/login
```

Json necesario para cumplir la peticion

```json
{
  "email": "correoregistrado@ŋmail.com",
  "password": "contraseña_usuario"
}
```

esta request intenta el login con correo y contraseña para devolver un token de acceso JWT

### Rutas protegidas para probar

## Ruta donde solo tiene acceso el admin

```http request
GET http://localhost:8080/api/private
```

## Ruta para cualquier usuario autenticado ya sea role admin o user

```http request
GET http://localhost:8080/api/public
```

## Usuario Admin para probar los endpoints protegidos

```json
{
  "email": "admin@gmail.com",
  "password": "SecretPassword"
}
```
