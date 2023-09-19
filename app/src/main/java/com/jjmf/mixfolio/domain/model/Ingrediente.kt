package com.jjmf.mixfolio.domain.model

import com.jjmf.mixfolio.data.dto.IngredienteDto

data class Ingrediente(
    val id:String,
    val nombre: String,
    val img: String,
    val tipo: TipoIngrediente,
    val cant:String
) {
    fun toDto(): IngredienteDto {
        return IngredienteDto(
            nombre = nombre,
            img = img,
            tipo = tipo.name
        )
    }
}

enum class TipoIngrediente {
    Alcohol,
    Gaseosa,
    Comun
}