package com.jjmf.mixfolio.ui.features.Agregar

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.LocalBar
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jjmf.mixfolio.R
import com.jjmf.mixfolio.domain.model.Ingrediente
import com.jjmf.mixfolio.domain.model.TipoIngrediente
import com.jjmf.mixfolio.domain.model.listIngredientes
import com.jjmf.mixfolio.ui.components.CajaTexto
import com.jjmf.mixfolio.ui.features.Agregar.components.AgregarIngredienteSheet
import com.jjmf.mixfolio.ui.features.Detalle.CardIngrediente
import com.jjmf.mixfolio.ui.theme.ColorCard
import com.jjmf.mixfolio.ui.theme.ColorFondo
import com.jjmf.mixfolio.ui.theme.ColorP1
import com.jjmf.mixfolio.ui.theme.ColorTextos
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarTragoScreen(
    viewModel: AgregarTragoViewModel = hiltViewModel(),
) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            viewModel.img = uri.toString()
        }
    )
    val bottomState = rememberBottomSheetScaffoldState()
    val coroutine = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomState,
        sheetContent = {
            AgregarIngredienteSheet(
                click = { ing ->
                    viewModel.listIngredientes.add(ing)
                }
            )
        },
        sheetPeekHeight = 0.dp,
        sheetContainerColor = ColorCard,
        sheetSwipeEnabled = false
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorFondo)
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
                valor = viewModel.descrip,
                newValor = {
                    viewModel.descrip = it
                },
                label = "Preparación",
                capitalization = KeyboardCapitalization.Sentences
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {

                CajaTexto(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.Coffee,
                    valor = viewModel.ingrediente,
                    newValor = {
                        viewModel.ingrediente = it
                    },
                    label = "Ingrediente"
                )
                IconButton(
                    onClick = {},
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = ColorP1,
                        contentColor = Color.White
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }
            }

            if (viewModel.listIngredientes.isEmpty()) {

            } else {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(viewModel.listIngredientes) {
                        CardIngrediente(
                            text = it.nombre,
                            ic = it.img,
                            modifier = Modifier.width(120.dp),
                            click = {}
                        )
                    }
                }
            }

            Button(
                onClick = {
                    coroutine.launch {
                        bottomState.bottomSheetState.expand()
                    }
                }
            ) {
                Text(text = "Agregar Ingrediente")
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {}) {
                    Text(text = "Volver")
                }
                Button(
                    onClick = {
                        viewModel.listIngredientes.add(insertOrUpdateInList(viewModel.ingrediente))
                        viewModel.ingrediente = ""
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = ColorP1,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Agregar")
                }
            }
        }
    }
}

fun insertOrUpdateInList(text: String): Ingrediente {
    return listIngredientes.find { it.nombre.equals(text, ignoreCase = true) }
        ?: Ingrediente(
            nombre = text,
            img = R.drawable.ic_naranja,
            tipo = TipoIngrediente.Comun
        )
}