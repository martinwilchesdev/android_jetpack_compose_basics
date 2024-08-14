# Temas de Material con Jetpack Compose

Material Design es un sistema de diseño respaldado por Google para crear experiencias digitales de
alta calidad para Android.

## Color

### Esquema de colores

Es la combinacion de colores que utiliza la aplicacion. En el sistema Android el color se representa
con un valor Hexadecimal.

> Los colores HEX comienzan con un simbolo `#` seguido de seis letras o numeros que representan los
> componentes rojo, verde y azul (RGB), eg, `#FF8000`.

Un color tambien pueden tener un valor alfa que representa la transparencia del color. `#00` es 0%
de opacidad y `#FF` es 100% de opacidad.

#### Materia Theme Builder

[Material Theme Builder](https://m3.material.io/theme-builder#/custom)

- Los colores primary se usan para los componentes claves de la IU.
- Los colores secundary se usan para los componentes menos destacados ed la IU.
- Los colores tertiary se usan para contrastar los acentos que pueden utilizarse para equilibrar los
  colores primarios y secundarios, o dirigir la atencion hacia un elemento como por ejemplo un campo
  de entrada.
- Los elementos de color on se aplican principalmente al texto, la iconografia y los trazos.

> Si se usan los archivos generados desde Material Theme Builder para un proyecto, se debe
> actualizar el nombre del paquete al nombre del paquete del proyecto.

## Tipografia

### Pantalla

Lo estilos en pantalla se reservan para texto o numeros importantes y breves. Funcionan mejor en
pantallas grandes.

### Encabezado

Adecuados para textos breves y de alto enfasis en pantallas pequeñas.

### Titulo

Se deben usar para el texto de enfasis medio que se mantiene relativamente breve.

### Cuerpo

Se usan para pasajes de texto mas largos en la app.

### Etiqueta

Estilos mas pequeños y funciones que se usan para texto dentro de componentes o para texto pequeño
en el contenido.

## Crear un directorio de recursos de Android para fuentes

1. En la carpeta `res` crear un nuevo directorio de recursos de Android cuyo tipo sea `font`.

> Las fuentes personalizadas proporcionadas por la plataforma de Android se pueden descargar
> desde [fonts.google.com](https://fonts.google.com/?authuser=1&hl=es-419)

2. En la carpeta del archivo descargado las fuentes pueden ser accedidas desde el
   directorio `static`.
3. Los archivos descargados deben ser movidos al directorio `font` creado anteriormente.

> Los nombres de los archivos de fuente deben tener el formato snake_case.ttf