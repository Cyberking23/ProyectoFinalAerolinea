🧩 Proyecto Final Aerolínea ✈️

Aplicación Fullstack desarrollada con Spring Boot + Thymeleaf + MySQL + TailwindCSS, totalmente dockerizada para simplificar el despliegue y la ejecución.

🚀 Descripción

Este proyecto representa el sistema de gestión de una aerolínea, construido bajo una arquitectura monolítica en capas.
Incluye backend con Spring Boot 3.5.6, frontend con Thymeleaf + Tailwind (CDN), y base de datos MySQL 8.
Todo se ejecuta fácilmente mediante Docker Compose, sin necesidad de instalaciones manuales de MySQL ni configuraciones extra.

🧱 Tecnologías principales

Java 17

Spring Boot 3.5.6

Thymeleaf (motor de plantillas HTML)

TailwindCSS (CDN)

MySQL 8.0

Docker y Docker Compose

Maven 3.9+

⚙️ Requisitos previos

Antes de comenzar, asegúrate de tener instalado:

🐋 Docker Desktop

☕ Java 17

🧱 Maven 3.9+

💻 Un IDE como IntelliJ IDEA o VS Code

🐳 Ejecución con Docker
🔹 1. Construir y ejecutar el proyecto
docker-compose up --build


Este comando:

Crea y levanta el contenedor de MySQL

Compila el proyecto con Maven

Empaqueta el .jar de Spring Boot

Levanta el servidor en el puerto 8080

Una vez que todo esté arriba, accedé desde tu navegador a:
👉 http://localhost:8080

🔹 2. Detener los contenedores
docker-compose down


Esto detiene los servicios y libera los puertos.

🔹 3. Limpiar y reconstruir completamente

Si realizás cambios en el código Java o en las vistas (HTML, CSS, etc.), necesitás reconstruir la imagen para aplicar los cambios dentro del contenedor:

docker-compose down -v
docker system prune -f
docker-compose build --no-cache
docker-compose up


✅ Esto garantiza que el nuevo código se compile y se incluya en el .jar dentro del contenedor.
❌ Evita los errores por versiones cacheadas del build anterior.

🧠 Flujo de desarrollo recomendado
Etapa	Acción recomendada
🧩 Desarrollo local	Ejecutar mvn spring-boot:run desde el IDE para ver los cambios al instante
🧱 Pruebas con base real	Usar docker-compose up para simular el entorno completo con MySQL
🐳 Despliegue	Hacer docker-compose build --no-cache y luego docker-compose up
🚀 Producción	Subir la imagen final a un servidor o servicio en la nube (Render, Railway, etc.)
📦 Variables de entorno

Estas variables se definen en el docker-compose.yml:

Variable	Descripción	Valor por defecto
MYSQL_ROOT_PASSWORD	Contraseña del root de MySQL	root
MYSQL_DATABASE	Nombre de la base de datos	vuelosdb
MYSQL_USER	Usuario de la base	admin
MYSQL_PASSWORD	Contraseña del usuario	admin123
SPRING_DATASOURCE_URL	URL JDBC para conectar el backend	jdbc:mysql://db:3306/vuelosdb
SPRING_JPA_HIBERNATE_DDL_AUTO	Política de schema	update
📁 Estructura del proyecto
ProyectoFinalAerolinea/
│
├── src/
│   ├── main/
│   │   ├── java/com/udb/proyectofinalaerolinea/
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── ProyectoFinalAerolineaApplication.java
│   │   └── resources/
│   │       ├── templates/   <-- Vistas Thymeleaf (index.html, login.html, etc.)
│   │       ├── static/      <-- Archivos estáticos (imágenes, CSS opcionales)
│   │       └── application.properties
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md

🌐 Endpoints principales
Ruta	Método	Descripción
/	GET	Página principal
/login	GET	Vista de login (Thymeleaf + Tailwind)
/registro	GET	Vista de registro de usuario
/api/vuelos	GET/POST	Endpoints del backend para gestión de vuelos
🧩 Consejos útiles

Cuando cambies archivos HTML o Java, reconstruí la imagen con --no-cache para evitar errores.

Usá el comando:

docker logs -f springboot-app


para ver el log en tiempo real dentro del contenedor.

Podés acceder a la base de datos MySQL desde tu host en el puerto 3307.

✈️ Resultado final

Después de ejecutar:

docker-compose up


🌐 Accedé a la aplicación:
👉 http://localhost:8080

🛢️ Accedé a la base de datos:
👉 localhost:3307 (usuario: admin, contraseña: admin123)