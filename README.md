# 🧑 Personas API

API REST desarrollada en **Spring Boot** para gestionar personas.  
Permite **crear, buscar, actualizar y eliminar registros de personas**.

---

## ☕ Tecnologías
- Java 11
- Spring Boot 2.7.18
- Maven
- Docker & Docker Compose
- MySQL

---

## 📦 Requisitos previos
- [Java 11+](https://adoptopenjdk.net/)
- [Maven 2+](https://maven.apache.org/)
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

---

## ▶️ Ejecución

### 🐳 Localmente con Docker

- Clonar el siguiente repositorio:

```
git clone https://github.com/fede17rosales/api-personas.git
```

- Pararse en la carpeta /api-personas/

```
  api-personas/
  │
  ├── personas/
  │   ├── Dockerfile
      └── ...
  ├── docker-compose.yml     ← aqui en este nivel donde se encuentra el docker-compose.yml
  └── README.md
```

- Y ejecutar desde terminal:

```
docker-compose up --build
```

## 🚀 Postman

### Listar Personas

```
curl --location 'localhost:8081/api/v1/persona' \
--header 'Cookie: JSESSIONID=EE7C5F1788FA669732F2545FFA687EE4'
```

### Agregar Personas

```
curl --location 'localhost:8081/api/v1/persona' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=EE7C5F1788FA669732F2545FFA687EE4' \
--data-raw '{
  "name": "Persona 1",
  "email": "persona1@example.com"
}
'
```

### Actualizar Persona
```
curl --location --request PUT 'localhost:8081/api/v1/persona/1' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=EE7C5F1788FA669732F2545FFA687EE4' \
--data-raw '{
  "name": "ejemplo1",
  "email": "ejemplo1@example.com"
}
'
```

### Eliminar Personas

```
curl --location --request DELETE 'localhost:8081/api/v1/persona/1' \
--header 'Cookie: JSESSIONID=EE7C5F1788FA669732F2545FFA687EE4'
```

## 🔑 Seguridad 

Para consultar los endpoints necesitas de Authorization: **Basic Auth** e ingresas los siguientes datos:

- _Username_: **admin**
- _Password_: **admin123**


## 📖 Swagger

Una vez levantado el proyecto ingresar a:

👉 [Documentación](http://localhost:8081/swagger-ui.html)