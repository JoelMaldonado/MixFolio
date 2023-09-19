package com.jjmf.mixfolio.data.dto

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude
import com.jjmf.mixfolio.domain.model.Cocktail

data class CocktailDto(
    @get:Exclude var id: String? = null,
    val nombre: String? = null,
    val preparacion: String? = null,
    val precio: Double? = null,
    val img: String? = null,
    val ingredientes: List<String?>? = null,
    val favorito: Boolean? = null,
    val usuario:String? = null,
){
    fun toDomain(id:String) : Cocktail{
        return Cocktail(
            id = id,
            nombre = nombre ?: "Sin Data",
            preparacion = preparacion ?: "Sin Data",
            precio = precio ?: 0.0,
            img = img ?: "Sin Data",
            favorito = favorito ?: false,
            ingredientes = emptyList()
        )
    }
}

fun DocumentSnapshot.toCocktailDto(): CocktailDto? {
    return try {
        val listIngredientes = get("ingredientes") as? List<*>
        val ingredientes = listIngredientes?.map { map -> map as? String }
        CocktailDto(
            id = id,
            nombre = getString("nombre"),
            preparacion = getString("preparacion"),
            precio = getDouble("precio"),
            img = getString("img"),
            ingredientes = ingredientes,
            favorito = getBoolean("favorito")
        )
    } catch (e: Exception) {
        null
    }
}