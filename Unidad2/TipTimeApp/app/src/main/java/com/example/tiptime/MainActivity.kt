/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.NumberFormat

/**
 * El estado es cualquier valor que puede cambiar con el tiempo.
 *
 * * Tema y estilos
 * Los estilos y temas son una coleccion de atributos que especifican la apariencia de los elementos de la IU.
 * Un estilo puede especificar atributos como el color y el tamaño de la fuente.
 *
 * * Composicion
 * La composicion es una descripcion de la IU que crea Compose cuando ejecuta elementos que admiten composicion.
 * Las apps de Compose llaman a funciones de componibilidad para transformar datos en IU.
 * Si se produce un cambio de estado, Compose vuelve a ejecutar las funciones componibles afectadas con el nuevo estado,
 * con la nueva actualizacion de la IU en lo que se conoce como un proceso de recomposicion.
 *
 * Se pueden usar los tipos State y MutableState en Compose para que se pueda hacer un seguimiento al estado de la app.
 * - State es inmutable, solo se puede leer el valor que tiene.
 * - MutableState es mutable, se puede usar la funcion mutableStateOf() para crear un MutableState observable.
 * */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    TipTimeLayout()
                }
            }
        }
    }
}

@Composable
fun TipTimeLayout() {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        EditNumberField(modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth())
        Text(
            text = stringResource(R.string.tip_amount, "$0.00"),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(150.dp))
    }
}

@Composable
fun EditNumberField(modifier: Modifier = Modifier) {
    /**
     * La variable amountInput representa el estado del cuadro de texto.
     *
     * Las funciones de componibilidad pueden almacenar un objeto entre recomposiciones con remember.
     * Un valor calculado por la funcion remember se almacena en la composicion durenta la composicion inicial
     * y el valor almacenado se muestra durante la recomposcion.
     *
     * Mediante la delegacion de propiedades las funciones de los metodos get y set de la propiedad amountInput
     * se delegan a las funciones del metodo get y set de remember.
     * */
    var amountInput by remember {
        mutableStateOf("")
    }
    /**
     * value :: Cuadro de texto que muestra el valor de cadena pasado al control input
     * onValueChange :: Devolucion de llamada de la funcion lambda que se activa cuando se ingresa texto
     *
     * Durante la composicion inicial `value` se establece en una cadena vacia.
     * Cuando se ingrese texto en el input, la funcion de devolucion de llamada es invocada estableciendo
     * el valor de `amountInput` en el texto ingresado.
     * amountInput es el estado observado durante la recomposicion. Ya que se estas usando rememeber {}
     * el cambio realizado sobrevive a la recomposicion, por lo cual no se vuelve a inicializar en ""
     * */
    TextField(value = amountInput, onValueChange = {
        amountInput = it
    }, modifier = modifier)
}

/**
 * Calculates the tip based on the user input and format the tip amount
 * according to the local currency.
 * Example would be "$10.00".
 */
private fun calculateTip(amount: Double, tipPercent: Double = 15.0): String {
    val tip = tipPercent / 100 * amount
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun TipTimeLayoutPreview() {
    TipTimeTheme {
        TipTimeLayout()
    }
}
