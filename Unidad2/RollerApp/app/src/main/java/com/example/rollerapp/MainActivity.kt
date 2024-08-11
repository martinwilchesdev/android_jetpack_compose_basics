package com.example.rollerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    // El elemento remember componible requiere que se le pase una funcion
    var result by remember {
        /**
         * La funcion mutableStateOf() muestra un elemento observable.
         * Cuando cambia el valor de la variable result se activa una recomposicion, se refleja el valor del resultado
         * y se actualiza la IU.
         * */
        mutableIntStateOf(1)
    }

    val imageResource = when(result) {
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_1
    }

    /**
     * La funcion Column() es un diseño componible que ubica sus elementos secundarios en una secuencia vertical.
     * El argumento modifier garantiza que los elementos componibles que se encuentran contenidos por Column() cumplan con las restrucciones
     * a las que se llaman en la instancia modifier.
     *
     * El parametro horizontalAlignment configurado con el argumento Alignment.CenterHorizontally garantiza que los elementos secundarios
     * contenidos se centran con respecto al ancho.
     * */
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        println(imageResource.toString())
        Image(painter = painterResource(id = imageResource), contentDescription = null)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Mediante la funcion random() se genera un numero aleatorio del 1 al 6
            result = (1..6).random()
            /**
             * Los elementos de componibilidad no tienen estado de forma predeterminada, no tienen ningun valor
             * y el sistema los puede volver a componer, lo que hace que se restablezca el valor.
             * Las funciones de componibilidad pueden almacenar un objeto en la memoria con el elemento remember.
             * */
        }) {
            Text(text = stringResource(R.string.roll))
        }
    }
}

@Preview(showBackground = true)
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
    )
}