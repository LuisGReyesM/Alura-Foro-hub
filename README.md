# 💬 Proyecto Foro Hub - Gestión de Tópicos

API REST desarrollada con **Java y Spring Boot** para la gestión de tópicos en un foro. Permite crear, listar y consultar tópicos vinculados a autores y cursos.

---

## 🧩 Funcionalidades principales

- 📌 Crear nuevos tópicos con título, mensaje, autor y curso.
- 📋 Listar todos los tópicos registrados.
- 🔍 Buscar un tópico por su `id` mediante la ruta `/topicos/{id}`.
- 🕒 Asignación automática de la fecha de creación.
- 🟢 Manejo de estados del tópico (por ejemplo: `NO_RESPONDIDO`, `CERRADO`, etc.).
- 🔗 Preparado para integrarse con entidades como `Usuario` y `Curso` en el futuro.

---

## ⚙️ Tecnologías utilizadas

- ☕ Java 17+
- 🚀 Spring Boot 3.x
- 🛠️ Spring Data JPA (Hibernate)
- 🐬 MySQL
- 🧰 Maven
- ✨ Lombok
- 📦 Jakarta Persistence (JPA)

---

## 🚀 Cómo ejecutar el proyecto

1. Clona este repositorio:
   ```bash
   git clone https://github.com/LuisGReyesM/Alura-Foro-hub.git
