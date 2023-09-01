package com.jjmf.mixfolio.data.dto

import com.google.firebase.firestore.Exclude
import com.jjmf.mixfolio.domain.model.Trago

data class TragoDto(
    @get:Exclude var id:String? = null,
    val nombre:String? = null
){
    fun toDomain() : Trago {
        return Trago(
            nombre = nombre ?: "Sin Data"
        )
    }
}