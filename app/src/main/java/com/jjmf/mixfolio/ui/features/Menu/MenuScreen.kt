package com.jjmf.mixfolio.ui.features.Menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.mixfolio.ui.theme.ColorFondo

@Composable
fun MenuScreen(
    toCocktail: () -> Unit,
    toAddCocktail: () -> Unit,
    toIngredientes: () -> Unit,
    toAddIngrediente: () -> Unit,
    viewModel: MenuViewModel = hiltViewModel(),
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorFondo),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Button(onClick = toCocktail) { Text(text = "Ver Cocktails") }
        Button(onClick = toAddCocktail) { Text(text = "Agregar Cocktail") }
        Button(onClick = toIngredientes) { Text(text = "Ver Ingredientes") }
        Button(onClick = toAddIngrediente) { Text(text = "Agregar Ingrediente") }
        Button(onClick = { }) { Text(text = "Favoritos") }

    }
}