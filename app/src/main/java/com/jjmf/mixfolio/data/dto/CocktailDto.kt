package com.jjmf.mixfolio.data.dto

import com.google.firebase.firestore.Exclude
import com.jjmf.mixfolio.domain.model.Cocktail

data class CocktailDto(
    @get:Exclude var id: String? = null,
    val nombre: String? = null,
    val preparacion: String? = null,
    val img: String? = null,
    val ingredientes: List<IngredienteDto?>? = null,
    val favorito: Boolean? = null,
    val usuario: String? = null,
) {
    fun toDomain(id: String): Cocktail {
        return Cocktail(
            id = id,
            nombre = nombre ?: "Sin Data",
            preparacion = preparacion ?: "Sin Data",
            img = img ?: "Sin Data",
            favorito = favorito ?: false,
            ingredientes = ingredientes?.mapNotNull { it?.toDomain() } ?: emptyList(),
            usuario = usuario ?: "Sin Data"
        )
    }
}