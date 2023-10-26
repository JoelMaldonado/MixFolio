package com.jjmf.mixfolio.ui.features.Cocktail.Agregar

import android.Manifest
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.LocalBar
import androidx.compose.material.icons.outlined.RemoveCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.jjmf.mixfolio.ui.components.CajaTexto
import com.jjmf.mixfolio.ui.features.Cocktail.Agregar.Components.BuscarIngredientes
import com.jjmf.mixfolio.ui.theme.ColorCard
import com.jjmf.mixfolio.ui.theme.ColorFondo
import com.jjmf.mixfolio.ui.theme.ColorP1
import com.jjmf.mixfolio.ui.theme.ColorTextos
import com.jjmf.mixfolio.util.show

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AddCocktailScreen(
    back: () -> Unit,
    viewModel: AddTragoViewModel = hiltViewModel(),
) {
    val perm = rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)

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
    LaunchedEffect(key1 = Unit){
        perm.launchPermissionRequest()
        viewModel.init()
    }

    if (perm.status.isGranted){
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
                }
            }

            BuscarIngredientes()

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
    }else{
        Text(text = "Permiso Denegado")
    }
}
