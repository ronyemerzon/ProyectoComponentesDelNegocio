# Proyecto de Gestión de Solicitudes de Soporte Técnico (API REST)

Este proyecto consiste en el desarrollo de una API RESTful desarrollada con **Java** y **Spring Boot** para el registro, consulta, actualización y eliminación de solicitudes de soporte técnico. El sistema permite agilizar los procesos de soporte al automatizar lo que antes se registraba en papel o correos desordenados.

## 🚀 Características Principales

*   **Arquitectura Limpia en Capas**: Controladores, Servicios, Modelos y Repositorios.
*   **Almacenamiento en Memoria**: Simulado mediante colecciones concurrentes seguras para hilos (`ConcurrentHashMap` y `AtomicLong`), eliminando la necesidad de conectarse a una base de datos física durante la fase de desarrollo/demostración.
*   **Validación de Entradas**: Validación estricta mediante anotaciones de Jakarta Validation (`@Valid`, `@NotBlank`, `@Email`, `@Size`, etc.).
*   **Manejo Global de Excepciones**: Gestión centralizada mediante `@RestControllerAdvice` para capturar errores de validación, recursos no encontrados y excepciones inesperadas, retornando respuestas estructuradas en formato JSON.
*   **Documentación Interactiva con Swagger UI**: Especificación OpenAPI 3 integrada para explorar y probar la API desde el navegador.

---

## 🛠️ Requisitos Previos

*   **Java**: JDK 21 o superior instalado.
*   **Maven**: Versión 3.9 o superior.

---

## ⚙️ Instalación y Ejecución

1.  **Clonar el repositorio**:
    ```bash
    git clone <URL_DEL_REPOSITORIO>
    cd ProyectoJavaIDAT
    ```

2.  **Compilar el proyecto**:
    ```bash
    mvn clean compile
    ```

3.  **Ejecutar la aplicación**:
    ```bash
    mvn spring-boot:run
    ```

La aplicación arrancará por defecto en el puerto **8080** (`http://localhost:8080`).

---

## 📘 Documentación e Interacción (Swagger UI)

Una vez que la aplicación esté corriendo, puedes acceder a la documentación interactiva en:
👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

Desde allí podrás visualizar la lista completa de endpoints, los esquemas JSON requeridos y realizar pruebas en vivo directamente sobre la API.

---

## 🧭 Endpoints y Payloads de Ejemplo

### 1. Clientes (`/api/clientes`)
Permite gestionar la información de los clientes corporativos.

*   **GET `/api/clientes`**: Listar todos los clientes. (Viene precargado con 3 clientes de prueba).
*   **GET `/api/clientes/{id}`**: Obtener un cliente específico por ID.
*   **POST `/api/clientes`**: Registrar un nuevo cliente.
    *   *Payload de ejemplo:*
        ```json
        {
          "nombre": "Carlos Mendoza",
          "email": "carlos.mendoza@empresa.com",
          "telefono": "981234567",
          "empresa": "Mendoza Consultores"
        }
        ```
*   **PUT `/api/clientes/{id}`**: Actualizar un cliente existente.
*   **DELETE `/api/clientes/{id}`**: Eliminar un cliente por ID.

### 2. Técnicos (`/api/tecnicos`)
Permite gestionar los técnicos de soporte disponibles.

*   **GET `/api/tecnicos`**: Listar todos los técnicos de soporte. (Viene precargado con 3 técnicos de prueba).
*   **GET `/api/tecnicos/{id}`**: Obtener un técnico por su ID.
*   **POST `/api/tecnicos`**: Registrar un nuevo técnico.
    *   *Payload de ejemplo:*
        ```json
        {
          "nombre": "Elena Torres",
          "especialidad": "Seguridad de la Información",
          "email": "elena.torres@tech.com",
          "estado": "ACTIVO"
        }
        ```
*   **PUT `/api/tecnicos/{id}`**: Actualizar datos de un técnico (admite estado `ACTIVO` o `INACTIVO`).
*   **DELETE `/api/tecnicos/{id}`**: Eliminar un técnico.

### 3. Solicitudes de Soporte (`/api/solicitudes`)
Entidad central que relaciona a un Cliente y a un Técnico asignado (opcional).

*   **GET `/api/solicitudes`**: Listar todas las solicitudes registradas. (Viene con 2 solicitudes precargadas de ejemplo).
*   **GET `/api/solicitudes/{id}`**: Obtener detalles de una solicitud por ID.
*   **POST `/api/solicitudes`**: Crear una solicitud de soporte.
    *   *Payload de ejemplo:*
        ```json
        {
          "descripcion": "El servidor de base de datos reporta lentitud extrema y falta de memoria física.",
          "prioridad": "ALTA",
          "clienteId": 1,
          "tecnicoId": 1
        }
        ```
    *   *Nota*: El estado de la solicitud inicial se establece en `PENDIENTE` de manera automática.
*   **PUT `/api/solicitudes/{id}`**: Actualizar o asignar técnico / cambiar estado de la solicitud.
    *   *Payload de ejemplo (cambio a estado RESUELTO):*
        ```json
        {
          "descripcion": "El servidor de base de datos reporta lentitud extrema y falta de memoria física.",
          "prioridad": "ALTA",
          "clienteId": 1,
          "tecnicoId": 1,
          "estado": "RESUELTO"
        }
        ```
*   **DELETE `/api/solicitudes/{id}`**: Eliminar una solicitud.

---

## 🗂️ Estructura del Código Fuente

El proyecto sigue una estructura limpia basada en paquetes según las directrices de Spring Boot:

```
com.ico.proyectojavaidat
├── controller        # Controladores REST que exponen los endpoints y manejan las peticiones HTTP.
├── service           # Lógica de negocio (Interfaces e Implementaciones).
├── repository        # Acceso a datos (Simulado en memoria con colecciones Concurrentes).
├── model             # Entidades de dominio (Cliente, Técnico, Solicitud y Enums).
├── dto               # Objetos de Transferencia de Datos para desacoplar el payload de entrada.
└── exception         # Excepciones personalizadas y el Manejador Global de Excepciones.
```

---

## 📋 Integrantes del Equipo y Roles

*   **Integrante 1**: Desarrollador Backend - Diseño del Modelo de Datos y DTOs, Implementación de Repositorios en Memoria y Lógica de Negocio.
*   **Integrante 2**: Desarrollador Backend - Creación de Controladores REST, Integración y Configuración de validaciones `@Valid`.
*   **Integrante 3**: Desarrollador Backend - Implementación del Manejador de Excepciones Global (`@RestControllerAdvice`), Documentación Swagger UI y preparación del informe de pruebas funcionales en Postman.
