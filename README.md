# 📌 block-back

API REST para la gestión de un blog básico, que permite la creación de publicaciones (*Posts*) y comentarios.  
Éste proyecto está desarrollado con **Spring Boot** y utiliza **MySQL** como base de datos.

---

## 🛠️ Tecnologías y Versiones

| Tecnología  | Versión        |
|-------------|----------------|
| Java        | 21             |
| Spring Boot | 3.4.0-SNAPSHOT |
| MySQL       | Última estable |
| Maven       | Última estable |

---

## 📋 Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- [Java 21](https://www.oracle.com/java/technologies/downloads/#java21) o superior
- [Maven](https://maven.apache.org/) (versión 3.8+ recomendada)
- [MySQL](https://dev.mysql.com/downloads/) configurado y en ejecución

---

## 🚀 Ejecución en Local

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/Jorge-Urquiza/blog-back.git
   cd block-back
   ```

2. **Configurar base de datos**  
   Editar el archivo `application.properties` y definir:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/blog
   spring.datasource.username=<usuario>
   spring.datasource.password=<contraseña>
   ```

3. **Compilar y ejecutar**
   ```bash
   mvn spring-boot:run
   ```

---

## 🏗️ Construcción y Empaquetado

Para construir el proyecto y generar el archivo `.jar` ejecuta:

```bash
mvn clean install
```

El artefacto generado se encontrará en:

```
target/block-back-1.0.0.jar
```

Para ejecutarlo:

```bash
java -jar target/block-back-1.0.0.jar
```

---

## 👥 Equipo

| Nombre             |
|--------------------|
| Al Ivan Calle      |
| Jose Andres Mayser |
| Jorge Urquiza      |
| Daniel Zeballos    |

---
