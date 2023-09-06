package com.jjmf.mixfolio.ui.features.Cocktail.Agregar.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jjmf.mixfolio.domain.model.Ingrediente
import com.jjmf.mixfolio.domain.model.TipoIngrediente
import com.jjmf.mixfolio.domain.model.listIngredientes
import com.jjmf.mixfolio.ui.features.Cocktail.Detalle.CardIngrediente
import com.jjmf.mixfolio.ui.theme.ColorFondo
import com.jjmf.mixfolio.ui.theme.ColorP1

@Composable
fun AgregarIngredienteSheet(
    modifier: Modifier,
    click: (Ingrediente) -> Unit,
) {
    val listTotal = listIngredientes
    val list = remember { mutableStateOf(listTotal) }
    val isSelected = remember { mutableStateOf("Todos") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ChipFiltro(
                tipo = "Todos",
                isSelected = isSelected.value
            ) {
                list.value = listTotal
                isSelected.value = "Todos"
            }
            TipoIngrediente.values().forEach { tipo ->
                ChipFiltro(
                    tipo = tipo.name,
                    isSelected = isSelected.value
                ) {
                    list.value = listTotal.filter { tipo == it.tipo }
                    isSelected.value = tipo.name
                }
            }
        }
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize(),
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(list.value) {
                CardIngrediente(
                    text = it.nombre,
                    ic = it.img,
                    modifier = Modifier.fillMaxWidth(),
                    click = {
                        click(it)
                    }
                )
            }
        }
    }
}
