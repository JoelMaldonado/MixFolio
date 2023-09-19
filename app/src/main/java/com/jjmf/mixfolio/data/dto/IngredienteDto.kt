package com.jjmf.mixfolio.data.dto

import com.google.firebase.firestore.Exclude
import com.jjmf.mixfolio.domain.model.Ingrediente
import com.jjmf.mixfolio.domain.model.TipoIngrediente

data class IngredienteDto(
    @get:Exclude var id:String? = null,
    val nombre: String? = null,
    val img: String? = null,
    val tipo: String? = null
){
    fun toDomain() : Ingrediente{
        return Ingrediente(
            id = id ?: "Sin Data",
            nombre = nombre ?: "Sin Data",
            img = img ?: "Sin Data",
            tipo = TipoIngrediente.valueOf(tipo ?: "Comun"),
            cant = "1"
        )
    }
}
