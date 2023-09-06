package com.jjmf.mixfolio.ui.navigation

sealed class Rutas(val url:String){
    object Login : Rutas(url = "login")
    object Menu : Rutas(url = "menu")
    object Cocktail : Rutas(url = "cocktail"){
        object Add : Rutas(url = "add_cocktail")
        object Detail : Rutas(url = "detail_cocktail?{id}"){
            fun sendId(id:String) = "detail_cocktail?$id"
        }
    }
    object Ingrediente : Rutas(url = "ingrediente"){
        object Add : Rutas(url = "add_ingrediente")
    }
}
