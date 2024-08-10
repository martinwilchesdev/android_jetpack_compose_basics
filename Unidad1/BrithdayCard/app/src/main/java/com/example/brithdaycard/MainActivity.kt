package com.example.brithdaycard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brithdaycard.ui.theme.BrithdayCardTheme

/**
 * * Jetpack Compose
 * Kit de herramientas utilizado para crear interfaces de Android.
 * Con compose se puede compilar la IU de una aplicacion a partir de la definicion de un conjunto de funciones.
 *
 * * Jerarquia de la IU:
 * - Box :: Elementos contenidos uno sobre el otro.
 * - Row :: Elementos contenidos uno al lado del otro.
 * - Column: Elementos contenidos uno debajo del otro.
 *
 * * Modificadores de diseño
 * Se usan para decorar o agregar comportamiento a los elementos de la IU de Jetpack Compose.
 * Para configurar un modificador, un elemento componible deben aceptar un modificar como parametro.
 * */

class MainActivity : ComponentActivity() {
    /**
     * La interfaz de usuario es lo que se puede visualizar en pantalla (texto, imagenes, botones, etc.).
     * Los componentes de la IU pueden ser interactivos como un boton o estaticos como imagenes.
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BrithdayCardTheme {
                GreetingImage(
                    message = stringResource(R.string.happy_birthday_message),
                    from = stringResource(
                        R.string.happy_birthday_from
                    )
                )
            }
        }
    }
}

/**
 * Las funciones de componibilidad describen una parte de la IU, no retornan ningun valor y reciben informacion
 * generando una posterior salida en pantalla.
 * */
@Composable
// La practica recomendada es que el elemento componible acepta un parametro Modifier y pase ese modifier a su primer elemento secundario
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    Column(
        // Una practica recomendada es usar los incrementos de padding de a 4.dp
        modifier = modifier.padding(8.dp),
        // Los elementos secundarios dentro de Column se posicionan con los argumentos verticalArrangement y/o horizontalAlignment
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message, fontSize = 100.sp, lineHeight = 115.sp, textAlign = TextAlign.Center)
        Text(
            text = from,
            // Los pixeles escalables `sp` son una unidad de medida utilizada para el tamaño de la fuente.
            fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) {
    /**
     * Los recursos son archivos adicionales y contenido estatico.
     * Cada recurso es colocado en un subdirectorio especifico del directorio `res/`.
     *
     * Android genera automaticamente una clase `R` que contiene todos los ID de los recursos.
     * La funcion painterResource() carga un recurso de imagen, tomando el id del recurso como argumento.
     * */
    val image = painterResource(id = R.drawable.androidparty)

    // El elemento componible `Box` permite apilar elementos de diseño uno sobre otro
    Box(modifier) {
        // Se puede omitir definir un valor para la descripcion de la imagen cuando esta es meramente decorativa
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop, // Ajusta la escala de la imagen de la imagen de manera uniforme para mantener la relacion de aspecto
            alpha = 0.5F // Modificar la opacidad de la imagen con valores entre 0 y 1
        )
        GreetingText(
            message = "Happy Birthday Sam!",
            from = "From Emma",
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}

/**
 * Las anotaciones adjuntan informacion adicional al codigo, la cual ayuda al compilador a comprender la aplicacion.
 *
 * Las funciones componibles que tienen la anotacion @Preview se previsualizan en el panel `Design`.
 * El parametro showSystemUi añade una vista previa de la IU del sistema.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BirthdayCardPreview() {
    BrithdayCardTheme {
        // Las funciones de componibilidad pueden llamar a otras funciones del mismo tipo
        GreetingImage(message = "Happy Birthday Sam!", from = "Ada Lovelace")
    }
}