package com.jjmf.mixfolio.ui.features.Cocktail.Agregar

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.LocalBar
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.RemoveCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jjmf.mixfolio.domain.model.Cocktail
import com.jjmf.mixfolio.domain.model.Ingrediente
import com.jjmf.mixfolio.ui.components.CajaTexto
import com.jjmf.mixfolio.ui.theme.ColorCard
import com.jjmf.mixfolio.ui.theme.ColorFondo
import com.jjmf.mixfolio.ui.theme.ColorP1
import com.jjmf.mixfolio.ui.theme.ColorTextos
import com.jjmf.mixfolio.util.show

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCocktailScreen(
    back: () -> Unit,
    viewModel: AddTragoViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.init()
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            viewModel.img = uri
        }
    )

    val context = LocalContext.current

    viewModel.error?.let {
        context.show(it)
        viewModel.error = null
    }

    if (viewModel.back) {
        LaunchedEffect(key1 = Unit) {
            back()
            viewModel.back = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorFondo),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AnimatedVisibility(visible = !viewModel.isBuscar) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(180.dp)
                            .clip(CircleShape)
                            .background(ColorCard)
                            .clickable { launcher.launch("image/*") },
                        contentAlignment = Alignment.Center
                    ) {
                        if (viewModel.img == null) {
                            Icon(
                                imageVector = Icons.Default.Image,
                                contentDescription = null,
                                tint = ColorTextos,
                                modifier = Modifier.size(70.dp)
                            )
                        } else {
                            AsyncImage(
                                model = viewModel.img,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    Text(text = "Imagen", color = ColorTextos)
                }

                CajaTexto(
                    icon = Icons.Outlined.LocalBar,
                    valor = viewModel.nombre,
                    newValor = {
                        viewModel.nombre = it
                    },
                    label = "Nombre del Trago"
                )

                CajaTexto(
                    icon = Icons.Outlined.Description,
                    valor = viewModel.preparacion,
                    newValor = {
                        viewModel.preparacion = it
                    },
                    label = "Preparación",
                    capitalization = KeyboardCapitalization.Sentences
                )

                CajaTexto(
                    icon = Icons.Outlined.MonetizationOn,
                    valor = viewModel.precio,
                    newValor = {
                        viewModel.precio = it
                    },
                    label = "Precio",
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Decimal
                )
            }
        }

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
            shape = RoundedCornerShape(10.dp),


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

        if (viewModel.listIngredientAdd.isEmpty()) {

        } else {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(viewModel.listIngredientAdd) {
                    Column(
                        modifier = Modifier.height(100.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier.size(80.dp),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            AsyncImage(
                                model = it.img,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(70.dp)
                                    .align(Alignment.Center)
                            )
                            IconButton(
                                onClick = {
                                    viewModel.listIngredientAdd.remove(it)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.RemoveCircle,
                                    contentDescription = null,
                                    tint = ColorP1
                                )
                            }
                        }
                        Text(
                            text = it.nombre,
                            color = Color.White,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                        Text(text = it.cant, fontSize = 12.sp, color = ColorTextos)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = back) {
                Text(text = "Volver")
            }
            Button(
                onClick = {
                    viewModel.ingrediente = ""
                    viewModel.addCocktail()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorP1,
                    contentColor = Color.White
                ),
                enabled = !viewModel.cargando
            ) {
                if (viewModel.cargando) {
                    CircularProgressIndicator()
                } else {
                    Text(text = "Agregar")
                }
            }
        }
    }
}

@Composable
fun ItemSearch(ingrediente: Ingrediente, click: (String) -> Unit) {
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
                    Text(text = "Oz", color = ColorTextos, fontWeight = FontWeight.Medium)
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 5.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (des.value.isEmpty()) {
                            Text(text = "Cantidad de Onzas")
                        }
                        BasicTextField(
                            value = des.value,
                            onValueChange = { des.value = it },
                            modifier = Modifier
                                .fillMaxWidth(),
                            textStyle = TextStyle(
                                color = ColorTextos
                            ),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
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
