package com.jjmf.mixfolio.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.jjmf.mixfolio.ui.features.Cocktail.Agregar.AddCocktailScreen
import com.jjmf.mixfolio.ui.features.Cocktail.CocktailScreen
import com.jjmf.mixfolio.ui.features.Cocktail.Detalle.DetailCocktailScreen
import com.jjmf.mixfolio.ui.features.Login.LoginScreen
import com.jjmf.mixfolio.ui.features.Menu.MenuScreen

@Composable
fun NavegacionPrincipal() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination =
        if (FirebaseAuth.getInstance().currentUser != null) Rutas.Menu.url
        else Rutas.Login.url
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
                toCocktail = {
                    navController.navigate(Rutas.Cocktail.url)
                },
                toAddCocktail = {
                    navController.navigate(Rutas.Cocktail.Add.url)
                },
                back = {
                    navController.popBackStack()
                }
            )
        }

        composable(Rutas.Cocktail.url) {
            CocktailScreen(
                toDetalle = { id ->
                    navController.navigate(Rutas.Cocktail.Detail.sendId(id))
                }
            )
        }

        composable(Rutas.Cocktail.Add.url) {
            AddCocktailScreen(
                back = {
                    navController.popBackStack()
                }
            )
        }

        composable(Rutas.Cocktail.Detail.url) {
            it.arguments?.getString("id")?.let { id ->
                DetailCocktailScreen(id)
            }
        }

    }
}