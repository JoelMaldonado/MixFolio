package com.jjmf.mixfolio.data.dto

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude
import com.jjmf.mixfolio.domain.model.Cocktail
import com.jjmf.mixfolio.domain.model.Ingrediente

data class CocktailDto(
    @get:Exclude var id: String? = null,
    val nombre: String? = null,
    val preparacion: String? = null,
    val img: String? = null,
    val ingredientes: List<IngredienteDto>? = null,
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
            ingredientes = ingredientes?.map { it.toDomain() } ?: emptyList()
        )
    }
}

fun DocumentSnapshot.toCocktailDto(): CocktailDto? {
    return try {
        val listIngredientes = get("ingredientes") as? List<Map<String, Any>>
        val ingredientes = listIngredientes?.map { map ->
            IngredienteDto(
                cant = map["cant"] as? String ?: "Sin valor",
                img = map["img"] as? String ?: "Sin valor",
                nombre = map["nombre"] as? String ?: "Sin valor",
                tipo = map["tipo"] as? String ?: "Sin valor",
            )
        }
        CocktailDto(
            id = id,
            nombre = getString("nombre"),
            preparacion = getString("preparacion"),
            img = getString("img"),
            ingredientes = ingredientes,
            favorito = getBoolean("favorito")
        )
    } catch (e: Exception) {
        null
    }
}