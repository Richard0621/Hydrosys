# Hydrosys

_Sistema básico de gestión de inventario tipo ecommerce._

Hydrosys es una API REST en Java pensada para gestionar productos, existencias y operaciones típicas de un pequeño ecommerce: registro de productos, control de stock, movimientos de inventario y ventas.

## Tabla de contenidos

- [Características](#características)
- [Stack tecnológico](#stack-tecnológico)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Requisitos previos](#requisitos-previos)
- [Configuración](#configuración)
- [Ejecución del proyecto](#ejecución-del-proyecto)
  - [Con Maven / mvnw](#con-maven--mvnw)
  - [Construir JAR](#construir-jar)
- [Uso básico del API](#uso-básico-del-api)
- [Buenas prácticas y convenciones](#buenas-prácticas-y-convenciones)
- [Roadmap](#roadmap)
- [Autor](#autor)

---

## Características

- 🧾 **Gestión de productos**: creación, actualización, consulta y eliminación de productos.
- 📦 **Control de inventario**: manejo de existencias y movimientos de entrada/salida.
- 🛒 **Lógica básica de ventas**: operaciones típicas de un sistema de tipo ecommerce.
- 🔌 **API REST**: diseñada para ser consumida por un frontend (web, móvil, etc.).
- 🧪 **Arquitectura preparada para pruebas** (services, repositorios, controladores).

---

## Stack tecnológico

- ☕ **Java** (versión recomendada: 21 o superior)
- 📦 **Maven** como gestor de dependencias (`pom.xml`)
- 🌱 Framework backend: **Spring Boot** (por la estructura típica del proyecto)
- 🗄️ Base de datos relacional (por ejemplo: **PostgreSQL** o **MySQL**)
- 🐘 / 🐬 Conexión vía **Spring Data JPA** (repositorios) *(esperado por el tipo de proyecto)*

> Revisa el `pom.xml` y `application.properties` para ver el detalle exacto de dependencias y configuración.

---

## Estructura del proyecto

Estructura típica de un proyecto Spring Boot con Maven:

```text
Hydrosys/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ...              # Paquetes con controladores, servicios, entidades, repositorios
│   │   └── resources/
│   │       ├── application.properties  # Configuración de la app (DB, puerto, etc.)
│   │       └── ...
│   └── test/
│       └── java/                
├── pom.xml                      # Configuración Maven
├── mvnw / mvnw.cmd              # Maven Wrapper
└── .mvn/                        # Configuración interna del wrapper
