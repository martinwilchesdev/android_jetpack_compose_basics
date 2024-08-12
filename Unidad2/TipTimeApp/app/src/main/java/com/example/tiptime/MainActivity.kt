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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
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
     * toDoubleOrNull() es una funcion de Kotlin que analiza una cadena como un numero Double o null si la cadena
     * no es una representacion valida de un numero.
     * */
    val amount = amountInput.toDoubleOrNull() ?: 0.00
    val tip = calculateTip(amount)

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
        EditNumberField(
            value = amountInput,
            onValueChange = { amountInput = it },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        Text(
            /**
             * El formato posicional se usa para mostrar contenido dinamico en cadenas de texto.
             * Para usar el formato posicional, en el archivo strings.xml se debe definir el recurso con un argumento
             * de marcador de posicion `Tip amount %s`. En el siguiente ejemplo el valor de `tip` sera el valor mostrado
             * en la pantalla en lugar del marcador de posicion `%s`.
             * */
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(150.dp))
    }
}

/**
 * Cuando se aplica a los elementos componibles la elevacion de estado, esto implica incorporar 2 parametros al elemento.
 * - Un parametro `value: T`, que es el valor actual que se mostrara.
 * - Una lambda de devolucion de llamada `onValueChange: (T) -> Unit`, que se activa cuando cambiar el valor para que el estado
 * se pueda actualizar en otro lugar.
 * */
@Composable
fun EditNumberField(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
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
    TextField(
        value = value, onValueChange = onValueChange,
        /**
         * El parametro label define una etiqueta que se ubicara en el medio del campo de texto.
         * El valor de label es una lambda en la cual se define un elemento componible Text.
         * */
        label = { Text(text = stringResource(id = R.string.bill_amount)) },
        // El parametro singleLine permite definir si dentro del control de texto se permiten o no los saltos de linea
        singleLine = true,
        // El parametro keyBoardOptions configura el teclado que se muestra en pantalla.
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
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
