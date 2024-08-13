# Temas de Material con Jetpack Compose

Material Design es un sistema de diseño respaldado por Google para crear experiencias digitales de
alta calidad para Android.

## Color

### Esquema de colores

Es la combinacion de colores que utiliza la aplicacion. En el sistema Android el color se representa
con un valor Hexadecimal.

> Los colores HEX comienzan con un simbolo `#` seguido de seis letras o numeros que representan los
> componentes rojo, verde y azul (RGB), eg, #FF8000

Un color tambien pueden tener un valor alfa que representa la transparencia del color. `#00` es 0%
de opacidad y `#FF``es 100% de opacidad.

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