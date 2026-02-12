# Angular Patitas

Frontend de un sistema de gestión de citas para veterinaria desarrollado con Angular 21 y tecnologías modernas. Permite administrar citas, visualizar listados y gestionar la experiencia del usuario de forma eficiente y escalable.

Esta aplicación Angular se conecta con un backend REST API (documentado en el proyecto `spring-patitas`) para proporcionar una interfaz de usuario moderna y reactiva.

---

## Tabla de Contenidos

- [Descripción General](#descripción-general)
- [Tecnologías y Herramientas](#tecnologías-y-herramientas)
- [Arquitectura del Proyecto](#arquitectura-del-proyecto)
- [Estructura de Carpetas](#estructura-de-carpetas)
- [Componentes del Sistema](#componentes-del-sistema)
- [Pantallas y Funcionalidad](#pantallas-y-funcionalidad)
- [Servicios e Interceptores](#servicios-e-interceptores)
- [Modelos y Enums](#modelos-y-enums)
- [Instalación y Puesta en Marcha](#instalación-y-puesta-en-marcha)
- [Comandos Útiles](#comandos-útiles)
- [Calidad y Buenas Prácticas](#calidad-y-buenas-prácticas)

---

## Descripción General

Angular Patitas es un sistema completo de gestión veterinaria que ofrece:

- ✅ **Agendamiento de citas**: Formulario intuitivo para que los clientes registren citas para sus mascotas
- ✅ **Panel administrativo**: Vista de tabla con todas las citas registradas
- ✅ **Gestión de estados**: Control del ciclo de vida de las citas (PENDING → COMPLETED)
- ✅ **Validaciones robustas**: Formularios reactivos con validaciones en tiempo real
- ✅ **UI moderna**: Interfaz responsiva basada en Freya UI Library
- ✅ **Gestión de errores**: Interceptores HTTP para manejo centralizado de errores
- ✅ **Feedback visual**: Loaders, alertas y tooltips para mejorar UX

## Tecnologías y Herramientas

### Frontend
- **Angular 21.1.0**: Framework principal con soporte para Standalone Components y Signals
- **TypeScript 5.9.2**: Lenguaje tipado para mayor robustez y mantenibilidad
- **RxJS 7.8**: Programación reactiva y manejo de flujos asíncronos
- **SCSS**: Preprocesador de estilos para modularidad y reutilización
- **Freya**: Librería de componentes UI propia, sin dependencias externas, basada en Angular nativo y Signals
  - Componentes: Table, Paginator, Form, Button, Datepicker, Timepicker, Modal, Alert, Tooltip, Loader

### Herramientas de Desarrollo
- **Angular CLI**: Herramienta de línea de comandos para desarrollo
- **Vite**: Build tool rápido integrado en Angular
- **Google Material Symbols**: Iconografía moderna

## Arquitectura del Proyecto

El sistema sigue una **arquitectura modular y escalable**, basada en los principios de separación de responsabilidades y componentes standalone de Angular 21. La aplicación está diseñada con las siguientes capas:

### Capas Principales

#### 1. **Core** (`src/app/core/`)
Contiene la lógica central de la aplicación que se ejecuta de forma singleton:

- **Interceptors**: 
  - `RequestInterceptor`: Agrega headers (Content-Type) a todas las peticiones HTTP
  - `ResponseInterceptor`: Manejo centralizado de errores HTTP y mostrar alertas
  - `FreyLoaderInterceptor`: Controla el loader global durante peticiones HTTP

- **Services**:
  - `AppointmentsService`: CRUD de citas (GET, POST, PATCH)
  - `AlertService`: Wrapper para mostrar alertas de éxito, error, advertencia e info

#### 2. **Shared** (`src/app/shared/`)
Componentes, directivas y pipes reutilizables en toda la aplicación:

- **Components**:
  - `AppointmentForm`: Formulario reactivo para crear/visualizar citas
  - `ButtonBackComponent`: Botón de retroceso para navegación
  - `LoaderComponent`: Indicador de carga animado personalizado
  - `TableManagerComponent`: Wrapper para tablas con paginador integrado

#### 3. **UI** (`src/app/ui/`)
Páginas/vistas principales de la aplicación (Feature Modules):

- `HomeComponent`: Página de inicio y bienvenida
- `AppointmentComponent`: Vista para agendar nuevas citas
- `ListComponent`: Panel administrativo con listado de citas

#### 4. **Utils** (`src/app/utils/`)
Utilidades, helpers y definiciones compartidas:

- **Enums**: `AppointmentStatusEnum` (PENDING, COMPLETED)
- **Helpers**: `parseFechaYYYYMMDDToDate` para conversión de fechas
- **Models**: `Features` (clase base con método cleaner para sanitización)
- **Interfaces**: Definiciones de tipos TypeScript

#### 5. **Environments** (`src/environments/`)
Configuración específica por entorno:

- `environment.ts`: Desarrollo (localhost:8080)
- `environment.qa.ts`: QA
- `environment.prod.ts`: Producción

#### 6. **Guards** (`src/app/guards/`)
Protección de rutas (actualmente sin implementar, preparado para futuras funcionalidades de autenticación)

### Patrones de Diseño Implementados

- **Dependency Injection**: Uso extensivo de `inject()` para inyección de servicios
- **Reactive Programming**: Signals y RxJS para gestión de estado reactivo
- **Standalone Components**: Arquitectura moderna de Angular sin NgModules
- **Smart/Dumb Components**: Separación entre componentes contenedores (smart) y presentacionales (dumb)
- **Service Layer**: Abstracción de lógica de negocio en servicios
- **Interceptor Pattern**: Manejo centralizado de peticiones HTTP

## Estructura de Carpetas

```
angular-patitas/
├── .angular/                    # Cache de Angular CLI
├── .husky/                      # Git hooks (pre-commit, commit-msg)
├── .scripts/                    # Scripts personalizados
├── .vscode/                     # Configuración de VS Code
│   └── mcp.json                 # Model Context Protocol config
├── libs/                        # Librerías locales (Freya UI)
├── public/                      # Recursos públicos estáticos
│   └── images/                  # Imágenes (cat.webp, cat2.webp)
├── src/
│   ├── app/
│   │   ├── core/                # Lógica central de la aplicación
│   │   │   ├── interceptors/    # HTTP Interceptors
│   │   │   │   ├── request/     # Request interceptor
│   │   │   │   └── response/    # Response interceptor
│   │   │   └── services/        # Servicios singleton
│   │   │       ├── alert/       # Servicio de alertas
│   │   │       └── appointments/ # Servicio de citas
│   │   ├── guards/              # Route guards 
│   │   ├── shared/              # Componentes compartidos
│   │   │   └── components/
│   │   │       ├── appointment-form/   # Formulario de citas
│   │   │       ├── button-back/        # Botón volver
│   │   │       ├── loader/             # Loader animado
│   │   │       └── table-manager/      # Gestor de tablas
│   │   ├── ui/                  # Páginas/Vistas principales
│   │   │   ├── appointment/     # Vista crear cita
│   │   │   ├── home/            # Vista principal
│   │   │   └── list/            # Vista listado admin
│   │   ├── utils/               # Utilidades
│   │   │   ├── class/           # Clases auxiliares
│   │   │   ├── constants/       # Constantes
│   │   │   ├── decorators/      # Decoradores custom
│   │   │   ├── enums/           # Enumeraciones
│   │   │   ├── helpers/         # Funciones helper
│   │   │   ├── interfaces/      # Interfaces TypeScript
│   │   │   └── models/          # Modelos de datos
│   │   ├── app.config.ts        # Configuración de la app
│   │   ├── app.routes.ts        # Definición de rutas
│   │   ├── app.ts               # Componente raíz
│   │   └── app.html             # Template raíz
│   ├── environments/            # Configuración por entorno
│   │   ├── environment.ts       # Desarrollo
│   │   ├── environment.qa.ts    # QA
│   │   └── environment.prod.ts  # Producción
│   ├── styles/                  # Estilos globales
│   │   ├── freya.scss           # Estilos de Freya UI
│   │   ├── google-icons.scss    # Material Symbols
│   │   └── styles.scss          # Estilos base
│   ├── index.html               # HTML principal
│   └── main.ts                  # Entry point de la app
├── angular.json                 # Configuración Angular CLI
├── package.json                 # Dependencias y scripts
├── tsconfig.json                # Configuración TypeScript
├── commitlint.config.js         # Reglas de commits
├── .eslintrc.json               # Configuración ESLint
└── .prettierrc                  # Configuración Prettier
```

## Componentes del Sistema

### Componentes de UI (Páginas)

#### 1. **HomeComponent** (`src/app/ui/home/`)
**Propósito**: Página de bienvenida y punto de entrada principal

**Características**:
- Diseño visual atractivo con imagen 
- Mensaje de bienvenida personalizado
- Dos botones de acción:
  - "Agendar": Lleva a `/agendar` para crear nueva cita
  - "Ingresa como admin": Lleva a `/lista` para gestión administrativa
- Uso de RouterLink para navegación

**Tecnologías**: Standalone Component, RouterLink, FreyButton

#### 2. **AppointmentComponent** (`src/app/ui/appointment/`)
**Propósito**: Vista para agendar nuevas citas veterinarias

**Características**:
- Integra `AppointmentForm` para recolección de datos
- Validación completa del formulario antes de envío
- Comunicación con `AppointmentsService` para POST
- Feedback visual con alertas de éxito/error
- Botones de "Agendar" y "Cancelar"
- Reset automático del formulario tras éxito
- Imagen decorativa 

**Flujo**:
1. Usuario completa el formulario (nombre, mascota, fecha, hora, razón)
2. Click en "Agendar" → Validación
3. Si válido → POST a API → Alerta de éxito → Reset formulario
4. Si inválido → Marca campos con errores
5. "Cancelar" → Retorna a home

**Tecnologías**: FormGroup, ReactiveFormsModule, AppointmentForm, AlertService

#### 3. **ListComponent** (`src/app/ui/list/`)
**Propósito**: Panel administrativo para gestionar todas las citas

**Características**:
- Tabla con todas las citas desde la API
- Columnas: Cliente, Mascota, Razón (truncada), Fecha, Hora, Estado, Acciones
- Paginación integrada (5, 10, 20 items por página)
- Sorting por columnas
- Acciones por fila:
  - **Ver**: Modal de solo lectura con detalles completos
  - **Completar**: Cambia estado a COMPLETED con confirmación
- Botón "Volver" para regresar
- Estados visuales con clases CSS (pending, completed)
- Tooltips en iconos de acción

**Flujo**:
1. `ngOnInit()` → GET appointments → Actualiza tabla
2. Click "Ver" → Abre modal con datos en modo readonly
3. Click "Completar" → Alerta de confirmación → PATCH status → Reload datos → Alerta éxito
4. Datos actualizados en tiempo real tras cada operación

**Tecnologías**: FreyTable, FreyPaginator, FreyModal, Signals, DatePipe

### Componentes Compartidos

#### 1. **AppointmentForm** (`src/app/shared/components/appointment-form/`)
**Propósito**: Formulario reutilizable para crear/visualizar citas

**Características**:
- Modo lectura/escritura configurable
- FormGroup reactivo con validaciones:
  - `clientName`: Requerido, solo letras, max 50 caracteres
  - `petName`: Requerido, solo letras, max 50 caracteres
  - `date`: Requerido, fecha futura o presente
  - `time`: Requerido, rango 09:00-18:00
  - `reason`: Requerido, max 500 caracteres
- Datepicker y Timepicker integrados de Freya
- Validación en tiempo real con directivas
- Output `formGroupOutput` para comunicación con padre
- Soporte para `dataSource` con valores iniciales


#### 2. **TableManagerComponent** (`src/app/shared/components/table-manager/`)
**Propósito**: Wrapper para tablas con paginación automática

**Características**:
- Gestión automática de paginación
- Input `dataSource` con datos completos
- Models para `itemsPerPage` y `currentPage`
- Integración seamless con FreyTable y FreyPaginator
- Uso de `contentChild` para comunicación con tabla
- Effects para sincronización automática

#### 3. **LoaderComponent** (`src/app/shared/components/loader/`)
**Propósito**: Indicador de carga global animado

**Características**:
- Animación CSS personalizada de perro
- Se activa automáticamente durante peticiones HTTP
- Conectado a `FreyLoaderService` vía Signal
- Overlay fullscreen con mensaje "Procesando..."
- Se muestra/oculta reactivamente

#### 4. **ButtonBackComponent** (`src/app/shared/components/button-back/`)
**Propósito**: Botón para retroceder en navegación

**Características**:
- Usa `window.history.back()` para navegación
- Icono Material Symbols "arrow_back"
- Estilo consistente con Freya UI

## Pantallas y Funcionalidad

### Resumen de Rutas

| Ruta | Componente | Descripción | Acceso |
|------|-----------|-------------|--------|
| `/home` | HomeComponent | Página de inicio | Público |
| `/agendar` | AppointmentComponent | Formulario para agendar cita | Público |
| `/lista` | ListComponent | Panel administrador con todas las citas | Admin |
| `/**` | Redirect | Redirige a `/home` | - |

## Servicios e Interceptores

### AppointmentsService (`src/app/core/services/appointments/`)

**Responsabilidad**: Comunicación con la API de citas

**Métodos**:

```typescript
getAppointments(): Observable<Appointment[]>
// GET /api/appointments
// Retorna todas las citas

postAppointment(body: Appointment): Observable<void>
// POST /api/appointments
// Crea una nueva cita

updateAppointmentStatus(id: number, status: AppointmentStatusEnum): Observable<void>
// PATCH /api/appointments/:id/status?status=:status
// Actualiza el estado de una cita

getAppointmentById(appointmentId: number): Observable<Appointment>
// GET /api/appointments/:id
// Obtiene una cita específica (preparado para uso futuro)
```

**Configuración**:
- Utiliza `HttpClient` con `inject()`
- URL base desde `environment.api`
- Todas las peticiones retornan Observables

### AlertService (`src/app/core/services/alert/`)

**Responsabilidad**: Wrapper para mostrar alertas consistentes

**Métodos**:

```typescript
successAlert(title: string, description?: string): Observable<boolean>
// Muestra alerta verde de éxito con botón "ACEPTAR"

errorAlert(title: string, description?: string): Observable<boolean>
// Muestra alerta roja de error con botón "ACEPTAR"

warningAlert(title: string, description?: string): Observable<boolean>
// Muestra alerta amarilla con botones "CANCELAR" y "CONTINUAR"
// Retorna true si usuario confirma, false si cancela

infoAlert(title: string, description?: string): Observable<boolean>
// Muestra alerta azul informativa con botón "ACEPTAR"
```

**Integración**:
- Encapsula `FreyAlertService`
- Configuración predefinida para cada tipo
- Retorna Observable para manejo reactivo

### RequestInterceptor (`src/app/core/interceptors/request/`)

**Responsabilidad**: Modificar peticiones HTTP salientes

**Funcionalidad**:
- Agrega header `Content-Type: application/json` a todas las peticiones
- Manejo de errores básico con throwError

### ResponseInterceptor (`src/app/core/interceptors/response/`)

**Responsabilidad**: Manejo centralizado de errores HTTP

**Funcionalidad**:
- Intercepta errores de respuesta (4xx, 5xx)
- Extrae mensaje de error del backend o usa "Error inesperado"
- Muestra automáticamente alerta de error al usuario
- Re-lanza el error para manejo específico en componente si es necesario

**Ventajas**:
- Usuario siempre recibe feedback de errores
- Manejo consistente en toda la aplicación
- Evita duplicación de código de manejo de errores

### FreyLoaderInterceptor (de Freya UI)

**Responsabilidad**: Control automático del loader global

**Funcionalidad**:
- Muestra loader al iniciar petición HTTP
- Oculta loader al completar/fallar petición
- Gestiona contador para múltiples peticiones concurrentes

## Modelos y Enums

### Appointment Model (`src/app/core/services/appointments/appointments.model.ts`)

```typescript
class Appointment extends Features {
  id?: number = null;
  clientName: string = null;
  petName: string = null;
  reason: string = null;
  date: string = null;          // Formato: YYYY-MM-DD
  time: string = null;          // Formato: HH:MM:SS
  status: string = null;        // PENDING | COMPLETED
}
```

**Características**:
- Extiende `Features` para método `cleaner()`
- `cleaner()`: Sanitiza input eliminando propiedades no definidas y convirtiendo strings vacíos a null
- Valores iniciales en null para detección de campos no completados

### AppointmentStatusEnum (`src/app/utils/enums/appointment-status.enum.ts`)

```typescript
enum AppointmentStatusEnum {
  PENDING = 'PENDING',      // Cita pendiente de atención
  COMPLETED = 'COMPLETED'   // Cita completada
}
```

**Uso**:
- Control de estados posibles de una cita
- Previene strings mágicos en el código
- Facilita refactorización y mantenimiento

### Features Model (`src/app/utils/models/features.model.ts`)

**Clase base para modelos con utilidades comunes**

```typescript
class Features {
  cleaner(input?: any): this {
    // 1. Elimina propiedades que no existen en el modelo
    // 2. Convierte strings vacíos a null
    // 3. Asigna valores limpios al objeto
    // 4. Retorna this para chaining
  }
}
```

**Beneficio**: Validación y sanitización automática de datos de entrada

## Instalación y Puesta en Marcha

### Prerrequisitos

- **Node.js**: v18.x o superior
- **npm**: v10.9.0 (definido en `packageManager`)
- **Backend API**: El servidor backend debe estar corriendo en `http://localhost:8080/api/` (ver proyecto `spring-patitas`)

### Instalación

1. **Clona el repositorio**:
   ```bash
   git clone <repository-url>
   cd angular-patitas
   ```

2. **Instala las dependencias**:
   ```bash
   npm install
   ```

3. **Instala Freya UI Library** (si es necesario):
   ```bash
   npm run freya:install
   ```
   Este script limpia instalaciones previas y reinstala la librería desde `libs/freya-0.0.1.tgz`

4. **Configura el entorno**:
   - La configuración por defecto apunta a `http://localhost:8080/api/`
   - Modifica `src/environments/environment.ts` si tu backend usa otro puerto o host
   - Asegúrate de que el backend esté corriendo antes de iniciar la aplicación

5. **Inicia el servidor de desarrollo**:
   ```bash
   npm start
   ```
   La aplicación se abrirá automáticamente en [http://localhost:4200/](http://localhost:4200/)

### Configuración de Entornos

La aplicación incluye tres archivos de entorno:

- **`environment.ts`**: Desarrollo local (`http://localhost:8080/api/`)
- **`environment.qa.ts`**: Ambiente de QA
- **`environment.prod.ts`**: Producción

Modifica estos archivos según la URL de tu backend en cada ambiente.

### Configuración de Git Hooks

Los hooks de Husky se configuran automáticamente al instalar dependencias:

- **pre-commit**: Ejecuta lint-staged (ESLint + Prettier en archivos staged)
- **commit-msg**: Valida mensajes de commit con commitlint

Si los hooks no se activan, ejecuta manualmente:
```bash
npx husky install
```

## Comandos Útiles

| Comando | Descripción |
|---------|-------------|
| `npm start` | Inicia servidor de desarrollo en http://localhost:4200 con apertura automática |
| `npm run build` | Compila para producción en `/dist` |
| `npm run lint` | Ejecuta ESLint en todo el proyecto |
| `npm run format` | Formatea código con Prettier |
| `npm run freya:install` | Reinstala Freya UI Library |
| `ng build` | Compila la aplicación |
| `ng serve` | Inicia el servidor de desarrollo |

### Convenciones de Commits

El proyecto usa **Conventional Commits** validados por Commitlint:

```
<type>(<scope>): <subject>

[optional body]

[optional footer]
```

**Tipos permitidos**:
- `feat`: Nueva funcionalidad
- `fix`: Corrección de bug
- `docs`: Cambios en documentación
- `style`: Cambios de formato (no afectan funcionalidad)
- `refactor`: Refactorización de código
- `test`: Agregado o modificación de tests
- `chore`: Tareas de mantenimiento

**Ejemplos**:
```bash
git commit -m "feat(appointments): add appointment cancellation feature"
git commit -m "fix(list): resolve pagination issue on table"
git commit -m "docs(readme): update installation instructions"
```

## Calidad y Buenas Prácticas

### Herramientas de Calidad

#### **ESLint + Angular ESLint**
- Análisis estático de código TypeScript
- Reglas específicas de Angular
- Prevención de errores comunes y malas prácticas
- Ejecuta con: `npm run lint`

#### **Prettier**
- Formateo automático y consistente
- Configurado para TypeScript, HTML, SCSS, JSON
- Integrado con ESLint
- Ejecuta con: `npm run format`

#### **Commitlint + Husky**
- Validación de mensajes de commit
- Hooks pre-commit para quality gates
- Previene commits con código sin formatear
- Enforce de Conventional Commits

#### **Lint-staged**
- Ejecuta linters solo en archivos modificados
- Mejora performance de pre-commit hooks
- Formateo automático antes de commit

#### **Vitest**
- Framework de testing moderno y rápido
- Compatible con TypeScript
- Hot Module Replacement para tests
- Ejecuta con: `npm test`

### Prácticas de Código

✅ **Standalone Components**: Toda la aplicación usa componentes standalone (Angular 21+)  
✅ **Signals**: Estado reactivo moderno en lugar de BehaviorSubjects  
✅ **Dependency Injection**: Uso de `inject()` en constructores  
✅ **Typed Forms**: FormGroup tipado con TypeScript  
✅ **Lazy Loading**: Rutas con `loadComponent` para code splitting  
✅ **Interceptors**: Manejo centralizado de HTTP  
✅ **Service Layer**: Separación de lógica de negocio  
✅ **Enums**: En lugar de strings mágicos  
✅ **SCSS Modular**: Estilos encapsulados por componente  
✅ **Readonly Signals**: Inmutabilidad donde sea posible

### Importaciones Organizadas

Los índices (`index.ts`) facilitan las importaciones:
```typescript
// ✅ Recomendado
import { AlertService, AppointmentsService } from 'src/app/core/services';

// ❌ Evitar
import { AlertService } from 'src/app/core/services/alert/alert.service';
import { AppointmentsService } from 'src/app/core/services/appointments/appointments.service';
```

