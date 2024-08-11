package com.example.firstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstapp.ui.theme.FirstAppTheme

class MainActivity : ComponentActivity() {
    /**
     * La funcion onCreate() es el punto de partida de la aplicacion.
     * Llama otras funciones para compilar la interfaz de usuario.
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        /**
         * La funcion setContent() se usa para definir el diseño mediante funciones que admiten composicion.
         * Todas las funciones marcadas con @Composable se pueden llamar desde la funcion setContent().
         * */
        setContent {
            FirstAppTheme {
                Surface {
                    Greeting(name = "Android")
                }
            }
        }
    }
}

/**
 * La anotacion @Composable le indica al compilador de Kotlin que Jetpack Compose se uas para generar la UI.
 * Las funciones de componabilidad toman alguna entrada y generan lo que se muestra en la pantalla.
 * */
@Composable
fun Greeting(name: String) {
    /**
     * Surface es un contenedor que representa una seccion de la IU en la que se puede modificar el aspecto.
     * Modifier se utliza para decorar un elemento que admite composicion.
     * */
    Surface(color = Color.Cyan, modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Hi, my name is $name!",
            modifier = Modifier.padding(24.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, showSystemUi = true) // showBackground recibe un valor booleano, el cual permite modificar el tema de la previsualizacion
@Composable
fun GreetingPreview() {
    FirstAppTheme {
        Greeting("Martin")
    }
}