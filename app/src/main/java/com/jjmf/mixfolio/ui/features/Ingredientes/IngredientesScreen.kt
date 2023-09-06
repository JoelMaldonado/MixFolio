package com.jjmf.mixfolio.ui.features.Ingredientes

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun IngredientesScreen(
    viewModel: IngredientesViewModel = hiltViewModel(),
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(15.dp)
    ){
        items(viewModel.list){

        }
    }
}