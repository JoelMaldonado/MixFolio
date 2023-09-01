package com.jjmf.mixfolio.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.mixfolio.ui.theme.ColorCard
import com.jjmf.mixfolio.ui.theme.ColorFondo
import com.jjmf.mixfolio.ui.theme.ColorP1
import com.jjmf.mixfolio.ui.theme.ColorTextos


@Composable
fun CajaTexto(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    valor: String,
    newValor: (String) -> Unit,
    label: String,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.Words,
    keyboardType: KeyboardType = KeyboardType.Text,
) {

    val isFocused = remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .border(
                BorderStroke(1.dp, if (isFocused.value) ColorFondo else ColorP1),
                RoundedCornerShape(10.dp)
            )
            .background(ColorCard)
            .padding(15.dp)
            .onFocusChanged {
                isFocused.value = !isFocused.value
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isFocused.value) ColorTextos else ColorP1,
            modifier = Modifier.size(30.dp)
        )
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterStart
        ) {
            this@Row.AnimatedVisibility(visible = valor.isEmpty()) {
                Text(text = label, color = ColorTextos)
            }
            BasicTextField(
                value = valor,
                onValueChange = newValor,
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                ),
                cursorBrush = SolidColor(ColorP1),
                keyboardOptions = KeyboardOptions(
                    capitalization = capitalization,
                    keyboardType = keyboardType
                )
            )
        }
    }

}