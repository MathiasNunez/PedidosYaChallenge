# PedidosYaChallenge

- Aplicación desarrollada según lo solicitado 
- La aplicación puede ser descargada e instalada siguiendo este [link]()

## Compilación de la aplicación

* Este proyecto fue hecho utilizando Gradle 3.4.2, Kotlin 1.3.50 y Android Studio 3.5.2.
* El mismo contiene 2 variable de entorno `URL_BASE` y `IMAGE_BASE_URL` configurada en el archivo `app/build.gradle`.

## Arquitectura

Se aplica en la aplicación el patrón **MVP**, Model View Presenter, con el fin de separar la capa de negocio de la capa de  presentación. La UI está implementada en cada xml layout correspondiente y su controlador (Activity o Fragment), el cual implementa una interfaz llamada BaseView para mantenerlo indiferente al Presenter asociado.

Por esta razón, una estructura típica para una pantalla sería:
* XML Layout.
* Activity o Fragment implementando BaseView.
* Un Presenter.

## Mnunez Core
Se utilizó en este aplicación una librería de implementación propia que contiene las clases bases para la fácil implementación del patrón MVP. Además contiene la configuración de la capa de networking utilizando Retrofit, RxJava and Gson.
También brinda un conjunto de extensiones y utlidades que son y pueden ser utilizadas en diferentes aplicaciones.

## Funcionalidades

* Login:
  - Se brinda al usuario dos campos de usuario y contraseña para poder iniciar sesión en la aplicación.
  - En caso de dejar alguno de los campos vacíos, se provee un feedback apropiado para el usuario.
  
* Listado de Restaurantes:
  - La aplicación solicita al usuario que habilite el permiso para obtener su ubicación y asi realizar la búsqueda con la misma.
  - Se revela una lista de Restaurantes obtenidos de los primeros 20 y a medida que el usuario navega por la lista, se cargan otros 20 más y así sucesivamente
  - Se provee de una imagen, el nombre, ranking, medios de pago, costo de envío, etc.
  - Al tope del listado se indica cuántos resultados fueron encontrados y la dirección con la cuál se realizó la búsqueda.
  - En la parte baja derecha de la pantalla, se muestra un indicador flotante con la cantidad de resultados que están siendo mostrados.
