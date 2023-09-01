package com.jjmf.mixfolio.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjmf.mixfolio.ui.features.Agregar.AgregarTragoScreen
import com.jjmf.mixfolio.ui.features.Detalle.DetalleScreen
import com.jjmf.mixfolio.ui.features.Login.LoginScreen
import com.jjmf.mixfolio.ui.features.Menu.MenuScreen

@Composable
fun NavegacionPrincipal() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Rutas.Login.url
    ) {

        composable(Rutas.Login.url) {
            LoginScreen(
                toMenu = {
                    navController.navigate(Rutas.Menu.url)
                }
            )
        }

        composable(Rutas.Menu.url) {
            MenuScreen(
                toAgregar = {
                            navController.navigate(Rutas.Agregar.url)
                },
                toDetalle = {
                    navController.navigate(Rutas.Detalle.url)
                }
            )
        }

        composable(Rutas.Agregar.url){
            AgregarTragoScreen()
        }

        composable(Rutas.Detalle.url){
            DetalleScreen()
        }

    }
}