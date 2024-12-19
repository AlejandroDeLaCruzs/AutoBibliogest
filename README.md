# AUTOBIBLIOGEST

## Descripción

AUTOBIBLIOGEST es una aplicación diseñada para la autogestión de préstamos en bibliotecas. Su objetivo es facilitar a los usuarios la consulta, reserva y seguimiento de libros, optimizando la experiencia en la biblioteca. Toda la información de los usuarios y los libros se gestiona mediante ficheros.

## Funcionalidades

1. **Registro de Usuarios**  
   Los usuarios pueden registrarse proporcionando:
    - **Correo electrónico**: Será su identificador único en la aplicación.
    - **Contraseña**: Protege el acceso a su cuenta personal.

   Esto permite:
    - Realizar reservas personalizadas.
    - Consultar historial de reservas.
    - Recibir notificaciones sobre vencimientos y reservas.

2. **Consulta de ejemplares**  
   Los usuarios pueden buscar libros disponibles en la biblioteca utilizando:
    - Género
    - Título
    - Autor

3. **Reserva de ejemplares**  
   Una vez encontrada la disponibilidad del libro deseado, los usuarios pueden realizar una reserva.

4. **Consulta de historial de reservas**  
   Permite revisar las reservas recientes y las fechas de vencimiento.

5. **Notificaciones**  
   La aplicación genera avisos automáticos sobre:
    - Próximos vencimientos de préstamos

## Comportamiento de la Aplicación

- **Carga de datos**: Los datos de usuarios, libros y préstamos se almacenan en ficheros `.txt` y se cargan en estructuras de datos al inicio de la aplicación.
- **Guardado de datos**: Al cerrar la aplicación, los datos actualizados se guardan nuevamente en los ficheros.

## Requisitos

- **Java**: JDK 8 o superior.
- **IDE**: IntelliJ IDEA, Eclipse, o cualquier IDE compatible con Java.
- **Sistema operativo**: Windows, macOS, Linux.

## Instalación

1. Clona el repositorio:
   ```bash
   https://github.com/AlejandroDeLaCruzs/AutoBibliogest.git
2. Configura el entorno de ejecución para Java.
3. Ejecuta el archivo principal de la aplicación.

## Uso

1. Regístrate ingresando un correo electrónico y una contraseña.
2. Explora los libros disponibles mediante las opciones de búsqueda.
3. Reserva ejemplares y verifica tus reservas actuales.
4. Recibe notificaciones automáticas sobre tus reservas y vencimientos.

## Esquema UML
!(./res/UML1.jpg)
!(./res/UML2.jpg)

## Autores

Alejandro De La Cruz
Contacto: [acruzs3@alumnos.nebrija.es]

Luis Torrecilla
Contacto: [ltorrecillag@alumnos.nebrija.es]


