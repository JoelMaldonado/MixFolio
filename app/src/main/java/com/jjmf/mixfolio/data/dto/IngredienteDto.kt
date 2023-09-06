package com.jjmf.mixfolio.data.dto

import com.google.firebase.firestore.Exclude
import com.jjmf.mixfolio.domain.model.Ingrediente
import com.jjmf.mixfolio.domain.model.TipoIngrediente

data class IngredienteDto(
    @get:Exclude var id:String? = null,
    val nombre: String? = null,
    val img: Int? = null,
    val tipo: String? = null,
){
    fun toDomain() : Ingrediente{
        return Ingrediente(
            nombre = nombre ?: "Sin Data",
            img = img ?: 0,
            tipo = TipoIngrediente.valueOf(tipo ?: "Comun")
        )
    }
}
