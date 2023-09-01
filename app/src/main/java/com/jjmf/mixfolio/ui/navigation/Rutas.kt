package com.jjmf.mixfolio.ui.navigation

sealed class Rutas(val url:String){
    object Login : Rutas(url = "login")
    object Menu : Rutas(url = "menu")
    object Agregar : Rutas(url = "agregar")
    object Detalle : Rutas(url = "detalle")
}
