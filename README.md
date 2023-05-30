Para configurar el proyecto en un entorno de desarrollo necesitaremos las siguientes herramientas y configuraciones:

Una base de datos MySql llamada seguimientoplus a la que importaremos el archivo sql llamado "bbdd.sql" que se facilita para generar la estructura de la base de datos.

El Back Java está desarrollado bajo Spring Tool Suite 4.18.1 y Java 1.8 (8). En el archivo application.properties del proyecto deberemos indicar el usuario y contraseña deseado para acceder a nuestra base
de datos en la línea 2 y 3 del archivo respectivamente así como la dirección de la base de datos en la primera línea
(por defecto está para montarlo en un entorno todo local) y también si deseamos cambiar la instacia de la base de datos (por defecto la anteriormente dicha seguimientoplus).
