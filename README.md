# 🏨 Hoteling

Hoteling es una aplicación web desarrollada con **Spring Boot**, **Thymeleaf** y **MySQL** que permite a los usuarios **buscar, reservar y gestionar alojamientos** de forma sencilla e intuitiva.  
El proyecto simula un sistema de reservas real, donde los usuarios pueden registrarse, iniciar sesión, ver sus reservas y cancelarlas.

---

## 🚀 Características principales

### 👤 Gestión de usuarios
- Registro e inicio de sesión de usuarios.
- Sesión persistente con control de acceso.
- Cierre de sesión seguro.

### 🏡 Gestión de recursos (alojamientos)
- Listado de recursos disponibles (hoteles, casas rurales, apartamentos, etc.).
- Fichas de detalle con imagen, descripción, precio y capacidad.
- Búsqueda dinámica por nombre o descripción.

### 📅 Reservas
- Formulario para crear reservas seleccionando fechas y número de personas.
- Validación automática para evitar **reservas duplicadas o solapadas**.
- Visualización de **todas las reservas del usuario** en una página específica.
- Posibilidad de **cancelar una reserva** (cambio de estado a `CANCELADA`).
- Modal de confirmación antes de cancelar.

### 💾 Datos iniciales (data.sql)
- Base de datos precargada con múltiples recursos (hoteles, apartamentos, cabañas...).
- Soporte para imágenes de los recursos almacenadas en `/static/images/`.

---

## 🧩 Tecnologías utilizadas

| Tipo | Herramienta |
|------|--------------|
| **Backend** | Java 17 + Spring Boot |
| **Frontend** | HTML5, CSS3, JavaScript, Thymeleaf |
| **Base de datos** | MySQL (con `data.sql` para carga inicial) |
| **Gestión ORM** | Spring Data JPA / Hibernate |
| **Control de sesiones** | `HttpSession` |
| **Gestión de dependencias** | Maven |
| **Plantillas HTML** | Thymeleaf (con fragments reutilizables) |

---
