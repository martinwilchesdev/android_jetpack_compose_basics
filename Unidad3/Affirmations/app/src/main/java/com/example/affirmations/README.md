# Icono de la aplicación

El icono de la app se conoce tambien como selector.

Independientemente de la forma que elija el fabricante, el objetivo es que todos los iconos de las
apps en un solo dispositivo tengan la misma forma, brindadon una experiencia cohere para el usuario.

## Iconos adaptables

A partir del nivel de API 26 se introdujo la compatibilidad de iconos adaptables. Los iconos
adaptables se adaptan a una gran variedad de dispositivos y el icono es mostrado segun
la pantalla del dispositivo.

### Iconos de selector

El objetivo es que el icono de selector se vea nitido y claro, independientemente del modelo del
dispositivo o la densidad de pantalla.

> La densidad de pantalla hace referencia a la cantidad de pixeles por pulgada o puntos por
> pulgada (dpi) que se muestran en pantalla.

Para adaptarse a la variedad de densidades de pantalla de los distintos dispositivos, se deben
proporcionar diferentes versiones del icono de la app.

1. La vista Project muestra la estructura de los archivos del proyecto.
2. En la ruta `app > src > main > res` se muestran las carpetas mimap las cuales corresponden a la
   ubicacion de los elementos del icono de selector para la app.

Las carpetas `mimap` contienen los vectores del icono del selector en archivos XML. (mdpi, xhdpi,
etc) son calificadores de densidad, que se pueden agregar al nombre de un directorio de recursos a
fin de indicar que son recursos para dispositivos de una determinada densidad de pantalla.

> En cada directorio mimap se proporciona la version cuadrada y la version circular del icono.

## Iconos adaptables

A partir de la version de Android 8.0 (nivel de API 26) se admiten iconos adaptables.

Los iconos se componen de 2 capas, una en primer plano y otra para el segundo plano.

La capa del primer plano se apila sobre la capa del segundo plano.

> Los iconos adaptables se deben declarar en el directorio de recursos `mimap` que tiene el
> calificador de recursos `-v26`. Esto significa que los recursos de este directorio se aplicaran
> solo a dispositivos que ejecutan el nivel de API 26 (Android 8.0).

Debido a que la forma del icono se puede recortar segun la forma de la mascara del fabricante del
dispositivo, es importante colocar la informacion clave del iucono en la zona segura.

> La zona segura es un circulo de 66dpi de diametro en el centro de la capa en primer plano.

## Cambiar el icono de la app

1. Eliminar los recursos asociados al icono actual.
2. Desde la gestor de recursos se crea un nuevo recurso de imagen `(image asset)`.
3. En las pestañas foreground y background cargar el archivo de capa correspondiente.