# Employees Service API - Invex

Este es un microservicio robusto desarrollado con **Spring Boot 2.7.18** para la gestión de empleados. Proporciona una API RESTful para realizar operaciones CRUD, con seguridad integrada, documentación interactiva y soporte para bases de datos Oracle.

---

## 🛠️ Tecnologías Utilizadas

- **Java 17** (LTS)
- **Spring Boot 2.7.18**
- **Spring Data JPA**: Acceso a datos.
- **Spring Security**: Autenticación y autorización.
- **Oracle Database**: Base de datos principal (JDBC ojdbc8).
- **H2 Database**: Base de datos en memoria para pruebas.
- **Lombok**: Reducción de código repetitivo.
- **Springdoc OpenAPI (Swagger)**: Documentación de la API.
- **Maven**: Gestión de dependencias.

---

## 📋 Requisitos Previos (Windows)

Antes de comenzar, asegúrate de tener instalado lo siguiente:

1. **JDK 17**: [Descargar aquí](https://www.oracle.com/java/technologies/downloads/#java17).
2. **Git**: [Descargar aquí](https://git-scm.com/download/win).
3. **Variable de Entorno JAVA_HOME**: Configurada apuntando a tu instalación de JDK 17.
4. **Base de Datos Oracle** (opcional para ejecución local si usas el perfil de pruebas).

---

## ⚙️ Configuración de Variables de Entorno

El servicio requiere credenciales de seguridad que no deben estar hardcodeadas. En Windows, puedes configurarlas de dos formas:

### Opción A: Configuración Temporal (PowerShell)
```powershell
$env:APP_USER="tu_usuario"
$env:APP_PASSWORD="tu_password"