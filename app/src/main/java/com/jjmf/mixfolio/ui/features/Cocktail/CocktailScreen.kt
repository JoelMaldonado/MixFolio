package com.jjmf.mixfolio.ui.features.Cocktail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.mixfolio.ui.components.CajaTexto
import com.jjmf.mixfolio.ui.components.ItemCocktail
import com.jjmf.mixfolio.ui.theme.ColorFondo
import com.jjmf.mixfolio.util.show

@Composable
fun CocktailScreen(
    toDetalle: (id:String) -> Unit,
    viewModel: CocktailViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = Unit){
        viewModel.init()
    }

    val context = LocalContext.current

    viewModel.error?.let {
        context.show(it)
        viewModel.error = null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorFondo)
            .padding(horizontal = 15.dp)
            .padding(top = 15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        CajaTexto(
            icon = Icons.Outlined.Search,
            valor = viewModel.buscar,
            newValor = {
                viewModel.buscar = it
            },
            label = "Buscar"
        )
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(viewModel.listCocktails) {
                ItemCocktail(
                    cocktail = it,
                    click = {
                            toDetalle(it.id)
                    },
                    clickFavorito = {
                        viewModel.changeFavorito(it)
                    }
                )
            }
        }

    }
}