package com.jjmf.mixfolio.domain.model

import com.jjmf.mixfolio.data.dto.IngredienteDto

data class Ingrediente(
    val id: String,
    val nombre: String,
    val img: String,
    val tipo: TipoIngrediente,
    val cant: String,
) {
    fun toDto(): IngredienteDto {
        return IngredienteDto(
            id = id,
            nombre = nombre,
            img = img,
            tipo = tipo.name,
            cant = cant
        )
    }
}

enum class TipoIngrediente {
    Alcohol,
    Gaseosa,
    Comun
}