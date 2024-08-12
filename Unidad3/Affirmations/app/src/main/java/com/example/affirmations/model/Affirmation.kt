package com.example.affirmations.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

// La clase de datos Affirmation contiene 2 propiedades val
data class Affirmation(
    // Las anotaciones representan que las propiedades son un ID de un recurso
    @DrawableRes val imageResourceId: Int,
    @StringRes val stringResourceId: Int
)
