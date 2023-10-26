package com.jjmf.mixfolio.ui.features.Cocktail.Agregar.Components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jjmf.mixfolio.domain.model.Ingrediente
import com.jjmf.mixfolio.ui.features.Cocktail.Agregar.AddTragoViewModel
import com.jjmf.mixfolio.ui.theme.ColorCard
import com.jjmf.mixfolio.ui.theme.ColorP1
import com.jjmf.mixfolio.ui.theme.ColorTextos


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuscarIngredientes(
    viewModel: AddTragoViewModel = hiltViewModel(),
) {
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = if (viewModel.isBuscar) 0.dp else 15.dp),
        query = viewModel.buscar,
        onQueryChange = { new ->
            viewModel.buscar = new
            viewModel.listIngredients = viewModel.listIngredientesTotal.filter {
                it.nombre.contains(
                    new,
                    ignoreCase = true
                )
            }
        },
        onSearch = {
            viewModel.isBuscar = false
        },
        active = viewModel.isBuscar,
        onActiveChange = {
            viewModel.isBuscar = it
        },
        placeholder = {
            Text(text = "Agregar ingredientes", color = ColorTextos)
        },
        colors = SearchBarDefaults.colors(
            containerColor = ColorCard,
            dividerColor = ColorCard,
            inputFieldColors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = ColorTextos
            )
        },
        trailingIcon = {
            if (viewModel.isBuscar) {
                IconButton(onClick = { viewModel.isBuscar = false }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = ColorTextos
                    )
                }
            }
        },
        shape = RoundedCornerShape(10.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(viewModel.listIngredients) { ing ->
                ItemSearch(
                    ingrediente = ing
                ) {
                    viewModel.listIngredientAdd.add(ing.copy(cant = it))
                    viewModel.isBuscar = false
                    viewModel.buscar = ""
                    viewModel.listIngredients = viewModel.listIngredientesTotal
                }
            }
        }

    }
}

@Composable
private fun ItemSearch(ingrediente: Ingrediente, click: (String) -> Unit) {
    val bool = remember { mutableStateOf(false) }
    val des = remember { mutableStateOf("") }
    ListItem(
        headlineContent = {
            Text(text = ingrediente.nombre, color = Color.White)
        },
        leadingContent = {
            AsyncImage(
                model = ingrediente.img,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                bool.value = !bool.value
            },
        colors = ListItemDefaults.colors(
            containerColor = ColorCard
        ),
        supportingContent = {
            AnimatedVisibility(visible = bool.value) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 5.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (des.value.isEmpty()) {
                            Text(text = "Cantidad y descripción")
                        }
                        BasicTextField(
                            value = des.value,
                            onValueChange = { des.value = it },
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(
                                color = ColorTextos
                            )
                        )
                    }
                    FloatingActionButton(
                        onClick = {
                            click(des.value)
                        },
                        containerColor = ColorP1,
                        contentColor = Color.White,
                        modifier = Modifier.size(30.dp)
                    ) {
                        Icon(imageVector = Icons.Default.NavigateNext, contentDescription = null)
                    }
                }
            }
        }
    )
}