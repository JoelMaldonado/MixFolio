package com.jjmf.mixfolio.data.dto

import com.google.firebase.firestore.Exclude
import com.jjmf.mixfolio.domain.model.Cocktail

data class CocktailDto(
    @get:Exclude var id: String? = null,
    val nombre: String? = null,
    val preparacion: String? = null,
    val precio:Double? = null,
    val img: String? = null,
    val ingredientes : List<IngredienteDto>? = null,
    val favorito: Boolean? = null
) {
    fun toDomain(): Cocktail {
        return Cocktail(
            id = id ?: "Sin Data",
            nombre = nombre ?: "Sin Data",
            preparacion = preparacion ?: "Sin Data",
            precio = precio ?: 0.0,
            img = img ?: "Sin Data",
            ingredientes = ingredientes?.map { it.toDomain() } ?: emptyList(),
            favorito = favorito ?: false,
        )
    }
}