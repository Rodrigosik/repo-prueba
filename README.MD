# 🐾 Patitas - Sistema de Gestión de Citas Veterinarias

## Introducción

**Patitas** es un sistema completo de gestión de citas veterinarias que permite a los clientes agendar citas para sus mascotas y a los administradores gestionar toda la agenda de la clínica veterinaria.

El proyecto está dividido en dos aplicaciones independientes:

- **Frontend**: Aplicación web desarrollada con **Angular 21** que proporciona una interfaz moderna y reactiva
- **Backend**: API REST desarrollada con **Spring Boot** que maneja la lógica de negocio y persistencia de datos

## Estructura del Repositorio

```
repo-prueba/
├── angular-patitas/    # Aplicación Frontend (Angular 21)
├── spring-patitas/     # Aplicación Backend (Spring Boot)
└── README.MD          # Este archivo
```

## Documentación

Cada proyecto cuenta con su propia documentación detallada:

- **[Frontend Documentation](angular-patitas/README.md)**: Instrucciones de instalación, arquitectura, componentes, servicios y guía de desarrollo del frontend Angular
- **[Backend Documentation](spring-patitas/README.md)**: Arquitectura hexagonal, endpoints REST, reglas de negocio y configuración del backend Spring Boot

## Inicio Rápido

### 1. Ejecutar el Backend
```bash
cd spring-patitas
mvnw.cmd spring-boot:run
```
El servidor estará disponible en `http://localhost:8080`

### 2. Ejecutar el Frontend
```bash
cd angular-patitas
npm install
npm start
```
La aplicación se abrirá en `http://localhost:4200`

---

Para más información sobre cada parte del sistema, consulta la documentación específica de cada carpeta.
