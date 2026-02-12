# Angular Patitas

Sistema de gestión de citas para veterinaria desarrollado con Angular y tecnologías modernas. Permite administrar citas, visualizar listados y gestionar la experiencia del usuario de forma eficiente y escalable.

---

## Tabla de Contenidos

- [Tecnologías y Herramientas](#tecnologías-y-herramientas)
- [Arquitectura del Proyecto](#arquitectura-del-proyecto)
- [Estructura de Carpetas](#estructura-de-carpetas)
- [Pantallas y Funcionalidad](#pantallas-y-funcionalidad)
- [Instalación y Puesta en Marcha](#instalación-y-puesta-en-marcha)
- [Comandos Útiles](#comandos-útiles)
- [Calidad y Buenas Prácticas](#calidad-y-buenas-prácticas)

---

## Tecnologías y Herramientas

- **Angular 21.1.0**: Framework principal, uso de Signals para gestión reactiva de estado.
- **TypeScript 5.9.2**: Lenguaje tipado para mayor robustez.
- **RxJS 7.8**: Programación reactiva y manejo de flujos asíncronos.
- **SCSS**: Preprocesador de estilos para modularidad y reutilización.
- **Vitest**: Testing unitario rápido y moderno.
- **Freya**: Librería de componentes UI propia, sin dependencias externas, basada en Angular nativo y Signals.

## Arquitectura del Proyecto

El sistema sigue una arquitectura modular y escalable, separando responsabilidades en capas claras:

- **Core**: Servicios singleton, interceptores HTTP y lógica central.
- **Shared**: Componentes reutilizables y utilidades comunes.
- **UI**: Vistas/páginas principales de la aplicación.
- **Utils**: Helpers, constantes, enums, decoradores e interfaces.
- **Guards**: Protección de rutas y control de acceso.
- **Environments**: Configuración para distintos entornos (dev, prod, QA).
- **Styles**: Estilos globales y recursos visuales.

## Estructura de Carpetas

```
src/
├── app/
│   ├── core/                    # Servicios y funcionalidad central
│   │   ├── interceptors/        # HTTP interceptors
│   │   └── services/            # Servicios singleton
│   ├── shared/                  # Componentes compartidos
│   │   └── components/          # Componentes reutilizables
│   ├── ui/                      # Páginas/vistas
│   │   ├── appointment/         # Vista de citas
│   │   ├── home/                # Vista principal
│   │   └── list/                # Vista de listado
│   ├── utils/                   # Utilidades
│   │   ├── helpers/             # Funciones helper
│   │   ├── constants/           # Constantes
│   │   ├── decorators/          # Decoradores
│   │   ├── enums/               # Enumeraciones
│   │   └── interfaces/          # Interfaces
│   └── guards/                  # Route guards
├── environments/                # Configuración de entornos
└── styles/                      # Estilos globales                 # Estilos globales
```

## Pantallas y Funcionamiento

La aplicación está compuesta por varias pantallas principales, cada una con una función específica:

### 1. **Pantalla Principal (Home)**
- **Ruta:** `/`
- **Descripción:** Vista de bienvenida y acceso rápido a las funcionalidades principales.

### 2. **Listado de Citas (List)**
- **Ruta:** `/list`
- **Descripción:** Muestra un listado de todas las citas registradas, paginación.
- **Componentes clave:**
	- Tabla de citas (`table-manager`)
	- Acciones sobre cada cita (ver, completar)

### 3. **Gestión de Cita (Appointment)**
- **Ruta:** `/appointment` (para crear)
- **Descripción:** Permite crear una nueva cita.
- **Componentes clave:**
	- Formulario de cita (`appointment-form`)
	- Validaciones y alertas
	- Botón para guardar/cancelar

### 4. **Componentes Compartidos**
- **Loader:** Indicador de carga global.
- **Alertas:** Mensajes de éxito/error.
- **Botón Volver:** Navegación sencilla entre pantallas.

### 5. **Gestión de Estado y Servicios**
- **Servicios centralizados** para manejo de citas, alertas y comunicación con backend.
- **Interceptors** para gestión de peticiones HTTP.

## Flujo de Funcionamiento

1. El usuario accede a la pantalla principal y navega mediante el menú o accesos directos.
2. Puede consultar el listado de citas y realizar acciones sobre ellas.
3. Al crear una cita, se muestra un formulario validado y conectado al backend.
4. El sistema muestra alertas y loaders según el estado de las operaciones.

## Instalación y Puesta en Marcha

1. Clona el repositorio y accede a la carpeta `angular-patitas`.
2. Instala las dependencias:
	 ```bash
	 npm install
	 ```
3. Inicia el servidor de desarrollo:
	 ```bash
	 npm start
	 ```
4. Accede a la aplicación en [http://localhost:4200/](http://localhost:4200/)

## Comandos Útiles

- **Desarrollo:**
	```bash
	npm start
	```
- **Construcción:**
	```bash
	npm run build
	```

## Calidad y Buenas Prácticas

- **ESLint + Angular ESLint:** Análisis estático y reglas de estilo.
- **Prettier:** Formateo automático de código.
- **Commitlint + Husky:** Convenciones de commits y hooks de calidad.
- **Vitest:** Pruebas unitarias rápidas y modernas.
