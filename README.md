# Restabar
![icono](https://user-images.githubusercontent.com/8844134/35749327-7e26f0b8-0851-11e8-8427-5e09843b38e3.png)

Restabar | Aplicación Android y web para la gestion de Comandas y camareros de un bar
## Objetivo:
Gestion de comandas de un establecimiento de restauración mediante APP Android y Web.
* Visualiza las mesas, camareros, pedidos por mesa, pedidos completos del dia (Caja diaria), productos y stock de estos, etc
* Gestiona pedidos por mesa, mensajes a cocina, insercción de nuevos camareros en el sistema, pagar los pedidos, imprimir mediante PDF ticket de cada pedido y de la caja completa del dia.
La app y la web actuan completamente en tiempo real, trabajan con un servidor que debe estar accesible en red, las caracteristicas de el proyecto son:

# Servidor
* Linux (Ubuntu)
* Apache2
* MySQL
  * Vistas y triggers para optimización de la BD
* PHP
 *FPDF Library
* Bootstrap
* Backups de archivos mediante scripts Bash
* Servicios web (POST y GET) para acciones desde la APP Android con PHP (directorio /restabar/querys)

# App web
* Aplicación desde la que trabajarian en cocina o en barra.
* Gestiona camareros, productos, etc.
* Visualiza en tiempo real mesas disponibles, mensajes a cocina desde la APP Android, productos ingresados, pedidos por mesa junto con sus lineas de pedido.
* Imprime a PDF los pedidos de cada mesa seleccionada y todos los pedidos (Caja) que se han realizado en una fecha especifica o varias.
* Permite la descarga del fichero APK con la APP Android Restabar para cada camarero.
* Responsive para todo tipo de dispositivos.

# App Android
* Aplicacion desde la que trabajarian los camareros en los distintos puestos del bar/restaurante.
* Gestiona la apertura de nuevos pedidos.
* Establece las mesas del local como disponibles u ocupadas si hay un nuevo comensal.
* Inserta, modifica y borra cada linea de pedido para los productos que vayan pidiendo los comensales.
* Visualiza las imagenes de cada producto mediante URL que estan almacenadas en el servidor
* Login para que solo los usuarios puedan acceder a la app
* Preferencias para modificar valores de IP del servidor y guardar nombre y contraseña del camarero para el login
* Envio de mensajes a la APP web (Cocina o barra)


Algunas capturas
# App Android Restabar
![giphy](https://user-images.githubusercontent.com/8844134/35748045-ecc3b4ac-084c-11e8-8c13-42de0236d7a0.gif) ![screenshot_20180202-154325](https://user-images.githubusercontent.com/8844134/35748161-5633b5e0-084d-11e8-90af-15181b620b8d.png)

# App web Panel de control Restabar
![1](https://user-images.githubusercontent.com/8844134/35748227-8958b484-084d-11e8-8d0a-260ca0faf375.png)
![4](https://user-images.githubusercontent.com/8844134/35748422-49c1c21a-084e-11e8-826e-bf6143705cb9.png)
![6](https://user-images.githubusercontent.com/8844134/35748545-a98d766c-084e-11e8-99c6-f4f1c9ce474f.png)
![camarer](https://user-images.githubusercontent.com/8844134/35748969-37ff226e-0850-11e8-9528-bd5702869015.png)


### Trabajo realizado junto a (https://github.com/ObisNoBlan) para la asignatura programacin dispositivos móviles del ciclo superior (DAM)
