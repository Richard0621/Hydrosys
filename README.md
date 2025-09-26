# Hydrosys - Sistema de Gestión de Inventario

Sistema de gestion de inventario básico tipo ecommerce desarrollado en Spring Boot y React.


## Backend 
### Características

- **Gestión de Productos**: CRUD completo con categorías y lotes
- **Sistema de Lotes**: Control de inventario por lotes con fechas de vencimiento
- **Autenticación JWT**: Sistema de autenticación y autorización seguro
- **Documentación API**: Interfaz Swagger para testing y documentación
- **Base de datos**: MariaDB con Hibernate/JPA
- **Validaciones**: Validación de datos con Bean Validation

### Tecnologías Utilizadas

- **Framework**: Spring Boot 3.5.6
- **Base de datos**: MariaDB
- **ORM**: Hibernate/JPA
- **Seguridad**: Spring Security + JWT
- **Documentación**: Springdoc OpenAPI (Swagger)
- **Build**: Maven
- **Java**: 23

### Requisitos Previos

- Java 23 o superior
- Maven 3.6+
- MariaDB 10.4+ (o MySQL 8.0+)

### Instalación

1. **Clonar el repositorio**
   ```bash
   git clone <url-del-repositorio>
   cd hydrosys
   ```

2. **Configurar la base de datos**
   - Crear una base de datos MariaDB/MySQL
   - Configurar las credenciales en `application.properties`

3. **Configurar variables de entorno**
   Crear archivo `.env` en la raíz del proyecto:
   ```env
   DB_URL=jdbc:mariadb://localhost:3306/hydrosys
   DB_USERNAME=tu_usuario
   DB_PASSWORD=tu_password
   JWT_SECRET=tu_clave_secreta_jwt
   ```

4. **Instalar dependencias y ejecutar**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

### Configuración de Base de Datos

```properties
# application.properties
spring.datasource.url=jdbc:mariadb://localhost:3306/hydrosys
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
```

### Estructura del Proyecto

```
src/main/java/com/hydrosys/hydrosys/
├── Controller/          # Controladores REST
├── Service/            # Lógica de negocio
├── Repository/         # Acceso a datos
├── Model/             # Entidades JPA
├── DTOs/              # Objetos de transferencia
├── Security/          # Configuración de seguridad
├── Expetion            # Manejo de expeciones
└── HydrosysApplication.java
```

### Documentación API

La documentación interactiva de la API está disponible en:
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs


## Licencia

Este proyecto está bajo la licencia [MIT](LICENSE).

## Contacto

- Desarrollador: [Ricardo Pérez]
- Email: [ricardoandresperezporras@gmail.com]
- Proyecto: [URL del repositorio]
