# 🐾 Spring Patitas - Sistema de Gestión de Citas Veterinarias

## 📋 Descripción del Proyecto

**Spring Patitas** es una aplicación backend desarrollada con Spring Boot que permite la gestión completa de citas veterinarias. El sistema está construido siguiendo los principios de **Clean Architecture** (Arquitectura Hexagonal), lo que garantiza un código mantenible, testeable y desacoplado.

La aplicación ofrece funcionalidades para crear, consultar, listar y actualizar el estado de las citas veterinarias, con validaciones robustas y manejo de errores personalizado.

---

## 🏗️ Arquitectura del Sistema

El proyecto implementa **Clean Architecture** (Arquitectura Limpia) dividida en capas bien definidas:

### Capas de la Aplicación

```
┌─────────────────────────────────────────────────────────────┐
│                    INFRASTRUCTURE LAYER                      │
│  (Adapters, Controllers, Repositories, Config, Database)   │
│                                                              │
│  ┌────────────────────────────────────────────────────┐    │
│  │           APPLICATION LAYER                         │    │
│  │  (Use Cases, DTOs, Mappers)                        │    │
│  │                                                      │    │
│  │  ┌──────────────────────────────────────────┐     │    │
│  │  │         DOMAIN LAYER                      │     │    │
│  │  │  (Entities, Business Rules, Interfaces)  │     │    │
│  │  └──────────────────────────────────────────┘     │    │
│  └────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────┘
```

#### 1. **Domain Layer** (Dominio)
- **Entities**: `Appointment` - Entidad principal con lógica de negocio
- **Enums**: `AppointmentStatus` (PENDING, COMPLETED)
- **Exceptions**: Excepciones personalizadas del dominio
  - `AppointmentNotFoundException`
  - `DuplicateAppointmentException`
  - `InvalidTimeSlotException`
- **Repositories**: Interfaces de repositorios (puerto de salida)

**Reglas de Negocio en el Dominio:**
- Las citas solo pueden agendarse en intervalos de 30 minutos (09:00, 09:30, 10:00, etc.)
- No se permiten citas duplicadas en la misma fecha y hora
- Validación de slots de tiempo al crear una cita
- Control de estados de citas (PENDING ↔ COMPLETED)

#### 2. **Application Layer** (Aplicación)
- **Use Cases**: Casos de uso que orquestan la lógica de negocio
  - `CreateAppointmentUseCase`: Crear nueva cita
  - `GetAppointmentUseCase`: Obtener cita por ID
  - `GetAllAppointmentsUseCase`: Listar todas las citas
  - `UpdateAppointmentStatusUseCase`: Actualizar estado de cita
- **DTOs**: Objetos de transferencia de datos
  - `AppointmentRequest`: Datos de entrada para crear cita
  - `AppointmentResponse`: Datos de salida de cita
- **Mappers**: `AppointmentDtoMapper` - Conversión entre DTOs y entidades de dominio

#### 3. **Infrastructure Layer** (Infraestructura)
- **Input Adapters**: 
  - `AppointmentController`: Controlador REST con endpoints
- **Output Adapters**:
  - `AppointmentRepositoryImpl`: Implementación del repositorio
  - `AppointmentJpaRepository`: Repositorio JPA de Spring Data
  - `AppointmentEntity`: Entidad de persistencia JPA
  - `AppointmentMapper`: Conversión entre entidades de dominio y entidades JPA
- **Configuration**:
  - `CorsConfig`: Configuración CORS para integración con frontend Angular
  - `OpenApiConfig`: Configuración de Swagger/OpenAPI
  - `GlobalExceptionHandler`: Manejo global de excepciones

---

## 🎯 Funcionalidades Principales

### 1. **Crear Cita** (`POST /api/appointments`)
- Registra una nueva cita veterinaria
- Valida que no exista otra cita en la misma fecha y hora
- Valida que la hora sea en intervalos de 30 minutos
- Valida campos obligatorios (nombre cliente, nombre mascota, razón, fecha, hora)
- Asigna estado PENDING por defecto

### 2. **Consultar Cita por ID** (`GET /api/appointments/{id}`)
- Obtiene los detalles completos de una cita específica
- Retorna error 404 si la cita no existe

### 3. **Listar Todas las Citas** (`GET /api/appointments`)
- Devuelve todas las citas registradas en el sistema
- Útil para visualizar la agenda completa

### 4. **Actualizar Estado de Cita** (`PATCH /api/appointments/{id}/status`)
- Permite cambiar el estado de una cita
- Estados disponibles: PENDING, COMPLETED
- Valida que el estado no sea nulo

---

## 🛠️ Tecnologías Utilizadas

| Tecnología | Versión/Descripción |
|-----------|---------------------|
| **Java** | JDK 17+ |
| **Spring Boot** | Framework principal |
| **Spring Data JPA** | Persistencia de datos |
| **Spring Validation** | Validación de datos |
| **H2 Database** | Base de datos en memoria (desarrollo) |
| **Lombok** | Reducción de código boilerplate |
| **SpringDoc OpenAPI** | Documentación API (Swagger) |
| **Maven** | Gestión de dependencias |

---

## 📁 Estructura del Proyecto

```
spring-patitas/
├── src/
│   ├── main/
│   │   ├── java/com/example/spring_patitas/
│   │   │   ├── SpringPatitasApplication.java
│   │   │   │
│   │   │   ├── domain/                    # Capa de Dominio
│   │   │   │   ├── entities/
│   │   │   │   │   └── Appointment.java
│   │   │   │   ├── enums/
│   │   │   │   │   └── AppointmentStatus.java
│   │   │   │   ├── exceptions/
│   │   │   │   │   ├── AppointmentNotFoundException.java
│   │   │   │   │   ├── DuplicateAppointmentException.java
│   │   │   │   │   └── InvalidTimeSlotException.java
│   │   │   │   └── repositories/
│   │   │   │       └── AppointmentRepository.java
│   │   │   │
│   │   │   ├── application/               # Capa de Aplicación
│   │   │   │   ├── dto/
│   │   │   │   │   ├── AppointmentRequest.java
│   │   │   │   │   └── AppointmentResponse.java
│   │   │   │   ├── mappers/
│   │   │   │   │   └── AppointmentDtoMapper.java
│   │   │   │   └── usecases/
│   │   │   │       ├── CreateAppointmentUseCase.java
│   │   │   │       ├── GetAppointmentUseCase.java
│   │   │   │       ├── GetAllAppointmentsUseCase.java
│   │   │   │       └── UpdateAppointmentStatusUseCase.java
│   │   │   │
│   │   │   └── infrastructure/            # Capa de Infraestructura
│   │   │       ├── adapters/
│   │   │       │   ├── input/
│   │   │       │   │   └── rest/
│   │   │       │   │       └── AppointmentController.java
│   │   │       │   └── output/
│   │   │       │       └── persistence/
│   │   │       │           ├── AppointmentRepositoryImpl.java
│   │   │       │           ├── entities/
│   │   │       │           │   └── AppointmentEntity.java
│   │   │       │           ├── mappers/
│   │   │       │           │   └── AppointmentMapper.java
│   │   │       │           └── repositories/
│   │   │       │               └── AppointmentJpaRepository.java
│   │   │       └── config/
│   │   │           ├── CorsConfig.java
│   │   │           ├── GlobalExceptionHandler.java
│   │   │           ├── OpenApiConfig.java
│   │   │           └── dto/
│   │   │               └── ErrorResponse.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/                              # Pruebas
│       └── java/com/example/spring_patitas/
│
├── pom.xml                                # Configuración Maven
└── README.md                              # Este archivo
```

---

## 🚀 API REST - Endpoints Disponibles

### Base URL
```
http://localhost:8080/api/appointments
```

### Endpoints

#### 1. Crear Nueva Cita
```http
POST /api/appointments
Content-Type: application/json

{
  "clientName": "Juan Pérez",
  "petName": "Max",
  "reason": "Vacunación anual y chequeo general",
  "date": "2026-02-15",
  "time": "10:30:00"
}
```

**Respuesta Exitosa (201 Created):**
```json
{
  "id": 1,
  "clientName": "Juan Pérez",
  "petName": "Max",
  "reason": "Vacunación anual y chequeo general",
  "date": "2026-02-15",
  "time": "10:30:00",
  "status": "PENDING"
}
```

#### 2. Obtener Cita por ID
```http
GET /api/appointments/{id}
```

**Respuesta Exitosa (200 OK):**
```json
{
  "id": 1,
  "clientName": "Juan Pérez",
  "petName": "Max",
  "reason": "Vacunación anual y chequeo general",
  "date": "2026-02-15",
  "time": "10:30:00",
  "status": "PENDING"
}
```

#### 3. Listar Todas las Citas
```http
GET /api/appointments
```

**Respuesta Exitosa (200 OK):**
```json
[
  {
    "id": 1,
    "clientName": "Juan Pérez",
    "petName": "Max",
    "reason": "Vacunación anual",
    "date": "2026-02-15",
    "time": "10:30:00",
    "status": "PENDING"
  },
  {
    "id": 2,
    "clientName": "María González",
    "petName": "Luna",
    "reason": "Consulta general",
    "date": "2026-02-16",
    "time": "11:00:00",
    "status": "COMPLETED"
  }
]
```

#### 4. Actualizar Estado de Cita
```http
PATCH /api/appointments/{id}/status?status=COMPLETED
```

**Respuesta Exitosa (200 OK):**
```json
{
  "id": 1,
  "clientName": "Juan Pérez",
  "petName": "Max",
  "reason": "Vacunación anual",
  "date": "2026-02-15",
  "time": "10:30:00",
  "status": "COMPLETED"
}
```

---

## ⚠️ Validaciones y Reglas de Negocio

### Validaciones de Entrada

#### Campo `clientName`:
- ✅ **Obligatorio**
- ✅ Máximo 50 caracteres
- ✅ Solo letras, espacios y caracteres especiales (á, é, í, ó, ú, ñ, ü)

#### Campo `petName`:
- ✅ **Obligatorio**
- ✅ Máximo 50 caracteres
- ✅ Solo letras, espacios y caracteres especiales

#### Campo `reason`:
- ✅ **Obligatorio**
- ✅ Máximo 500 caracteres
- ✅ Letras, números, espacios, puntos y comas

#### Campo `date`:
- ✅ **Obligatorio**
- ✅ Formato: yyyy-MM-dd

#### Campo `time`:
- ✅ **Obligatorio**
- ✅ Formato: HH:mm:ss
- ✅ **Debe ser en intervalos de 30 minutos** (09:00, 09:30, 10:00, 10:30, etc.)

### Reglas de Negocio

1. **No Duplicación**: No se permiten dos citas en la misma fecha y hora
2. **Slots de Tiempo**: Solo se aceptan horas en punto o media (validación en dominio)
3. **Estados Válidos**: Solo PENDING y COMPLETED
4. **Estado Inicial**: Toda cita nueva se crea con estado PENDING

---

## 🔴 Manejo de Errores

El sistema utiliza un manejador global de excepciones que devuelve respuestas estructuradas:

### Estructura de Error
```json
{
  "timestamp": "2026-02-12T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Descripción del error",
  "path": "/api/appointments",
  "errors": {
    "campo": "mensaje de error específico"
  }
}
```

### Tipos de Errores

| Código | Excepción | Descripción |
|--------|-----------|-------------|
| **404** | `AppointmentNotFoundException` | Cita no encontrada con el ID proporcionado |
| **409** | `DuplicateAppointmentException` | Ya existe una cita en esa fecha y hora |
| **400** | `InvalidTimeSlotException` | La hora no está en intervalos de 30 minutos |
| **400** | `MethodArgumentNotValidException` | Errores de validación de campos |
| **500** | `Exception` | Error interno del servidor |

### Ejemplos de Errores

**Cita Duplicada:**
```json
{
  "timestamp": "2026-02-12T10:30:00",
  "status": 409,
  "error": "Conflict",
  "message": "Ya existe una cita programada para la fecha 15/02/2026 a las 10:30. Por favor, seleccione otro horario.",
  "path": "/api/appointments"
}
```

**Slot de Tiempo Inválido:**
```json
{
  "timestamp": "2026-02-12T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "La hora 10:15 no es válida. Las citas solo pueden agendarse en intervalos de 30 minutos (ejemplo: 09:00, 09:30, 10:00, 10:30, etc.)",
  "path": "/api/appointments"
}
```

**Cita No Encontrada:**
```json
{
  "timestamp": "2026-02-12T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Appointment not found with id: 99",
  "path": "/api/appointments/99"
}
```

---

## 🚀 Cómo Ejecutar la Aplicación

### Requisitos Previos
- Java 17 o superior
- Maven 3.6+

### Opción 1: Usando Maven Wrapper 

**En Windows:**
```bash
mvnw.cmd spring-boot:run
```

**En Linux/Mac:**
```bash
./mvnw spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

---

## 📚 Documentación de API (Swagger)

Una vez ejecutada la aplicación, puedes acceder a la documentación interactiva de la API:

**Swagger UI:**
```
http://localhost:8080/swagger-ui.html
```

Desde Swagger UI puedes:
- Ver todos los endpoints disponibles
- Probar las peticiones directamente
- Ver los esquemas de datos
- Revisar ejemplos de request/response

---

## 🔧 Configuración

### application.properties

El archivo de configuración principal se encuentra en `src/main/resources/application.properties`:

```properties
# Puerto del servidor
server.port=8080

# Configuración de base de datos H2 (desarrollo)
spring.datasource.url=jdbc:h2:mem:veterinaria
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# Consola H2 (opcional)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### CORS Configuration

El sistema está configurado para aceptar peticiones desde el frontend Angular:
- **Origen permitido**: `http://localhost:4200`
- **Métodos permitidos**: GET, POST, PUT, PATCH, DELETE, OPTIONS
- **Headers**: Todos (*)
- **Credenciales**: Habilitadas

---

## 🎯 Patrones de Diseño Utilizados

1. **Clean Architecture**: Separación en capas independientes
2. **Dependency Injection**: Inyección de dependencias con Spring
3. **Repository Pattern**: Abstracción de acceso a datos
4. **DTO Pattern**: Objetos de transferencia de datos
5. **Mapper Pattern**: Conversión entre capas
6. **Builder Pattern**: Construcción de objetos (con Lombok)
7. **Factory Method**: Métodos estáticos de creación en entidad Appointment