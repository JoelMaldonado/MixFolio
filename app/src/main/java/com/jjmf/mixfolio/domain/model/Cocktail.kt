package com.jjmf.mixfolio.domain.model

data class Cocktail(
    val id:String,
    val nombre:String,
    val preparacion:String,
    val precio:Double,
    val img:String,
    val favorito:Boolean,
    val ingredientes: List<Ingrediente>
)
