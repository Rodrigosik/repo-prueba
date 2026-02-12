# Angular Patitas

Sistema de gestión de citas para veterinaria desarrollado con Angular y tecnologías modernas.

## Tecnologías

- **Angular 21.1.0** - Framework principal con Signals
- **TypeScript 5.9.2** - Lenguaje de programación
- **RxJS 7.8** - Programación reactiva
- **SCSS** - Preprocesador de estilos
- **Vitest** - Testing unitario

## Librerías

### Freya
Librería de componentes UI personalizada sin dependencias externas. Desarrollada completamente en Angular nativo utilizando Signals para la gestión de estado reactivo.

### Calidad de Código
- **ESLint** + **Angular ESLint** - Análisis estático
- **Prettier** - Formateo de código
- **Commitlint** + **Husky** - Convenciones de commits

## Arquitectura

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
└── styles/                      # Estilos globales
```

## Instalación

```bash
npm install
```

## Comandos

### Desarrollo
```bash
npm start
```
Inicia el servidor en `http://localhost:4200/`

### Construcción
```bash
npm run build
```