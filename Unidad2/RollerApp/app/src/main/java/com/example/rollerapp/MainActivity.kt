package com.example.rollerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.rollerapp.ui.theme.RollerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RollerAppTheme {
                DiceRollerApp()
            }
        }
    }
}

/**
 * La siguiente funcion recibe un parametro modifier, cuyo valor predeterminado es un objeto Modifier.
 * El valor predeterminado de un parametro permite que cuando se llame a la funcion sea opcional pasarle el modificador como argumento.
 * */
@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
}

@Preview
@Composable
fun DiceRollerApp() {
    /**
     * Ya que los elementos componibles pueden pasar por un proceso de recomposicion, y su bloque de codigo se ejecuta
     * es necesario pasar como argumento Modifier aunque la funcion tenga un parametro predeterminado.
     * Si se crea un objeto Modifier dentro del bloque de codigo, es posible que s, e vuelva a crear multiples veces
     * lo cual resulta en un proceso ineficiente.
     * */
    DiceWithButtonAndImage(
        modifier = Modifier
            // El metodo fillMaxSize() especifica que los elementos deben ocupar todo el espacio disponible
            .fillMaxSize()
            /**
             * El metodo wrapContentSize() especifica que el espacio disponible debe ser al menos tan grande como los elementos que contiene.
             * Si los elementos dentro del diseño son mas pequeños que el espacio disponible, se puede usar el objeto
             * Alignment como parametro de wrapContentSize() para especificar la alineacion de los elementos dentro del espacio disponible.
             * */
            .wrapContentSize(Alignment.Center)
            .background(Color.Red)
    )
}