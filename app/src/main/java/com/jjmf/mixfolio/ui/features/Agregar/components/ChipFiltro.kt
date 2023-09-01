package com.jjmf.mixfolio.ui.features.Agregar.components

import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.jjmf.mixfolio.ui.theme.ColorFondo
import com.jjmf.mixfolio.ui.theme.ColorP1


@Composable
fun ChipFiltro(
    tipo:String,
    isSelected:String,
    click:()->Unit
) {
    ElevatedAssistChip(
        onClick = click,
        label = {
            Text(text = tipo, color = Color.White)
        },
        colors = AssistChipDefaults.elevatedAssistChipColors(
            containerColor = if (isSelected == tipo) ColorP1 else ColorFondo
        )
    )
}