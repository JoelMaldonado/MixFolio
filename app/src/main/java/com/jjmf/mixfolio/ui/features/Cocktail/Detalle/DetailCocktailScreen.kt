package com.jjmf.mixfolio.ui.features.Cocktail.Detalle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import coil.compose.AsyncImage
import com.jjmf.mixfolio.domain.model.Ingrediente
import com.jjmf.mixfolio.ui.theme.ColorCard
import com.jjmf.mixfolio.ui.theme.ColorFondo
import com.jjmf.mixfolio.ui.theme.ColorP1
import com.jjmf.mixfolio.ui.theme.ColorP2
import com.jjmf.mixfolio.ui.theme.ColorS1

@Composable
fun DetailCocktailScreen(
    id: String,
    back: () -> Unit,
    viewModel: DetalleViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.init(id)
    }

    val context = LocalContext.current

    if (viewModel.back) {
        LaunchedEffect(key1 = Unit) {
            back()
            viewModel.back = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorFondo)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            AsyncImage(
                model = viewModel.cocktail?.img,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = {
                    SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).apply {
                        titleText = "Cuidado"
                        contentText = "Se eliminara el cocktail"
                        confirmButtonBackgroundColor = ColorP1.hashCode()
                        cancelButtonBackgroundColor = ColorS1.hashCode()
                        setCancelButton("Cancelar") {
                            dismissWithAnimation()
                        }
                        setConfirmButton("Confirmar") {
                            viewModel.delete(id)
                            dismissWithAnimation()
                        }
                        show()
                    }
                },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = ColorP1
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                ColorCard
                            )
                        )
                    ),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = viewModel.cocktail?.nombre ?: "Sin Data",
                    color = Color.White,
                    modifier = Modifier.padding(start = 15.dp, bottom = 15.dp),
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(15.dp)
                    .clip(CircleShape)
                    .background(ColorP2)
            )
            Text(
                text = "Preparación",
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }

        Text(text = viewModel.cocktail?.preparacion ?: "Sin Data", color = Color.White)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(15.dp)
                    .clip(CircleShape)
                    .background(ColorP2)
            )
            Text(
                text = "Lista de Ingredientes",
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }

        viewModel.cocktail?.ingredientes?.forEach {
            CardIngrediente(
                ingrediente = it
            )
        }

    }
}

@Composable
fun CardIngrediente(
    ingrediente: Ingrediente,
) {
    ListItem(
        leadingContent = {
            AsyncImage(
                model = ingrediente.img,
                contentDescription = null,
                modifier = Modifier.size(70.dp),
                contentScale = ContentScale.FillHeight
            )
        },
        headlineContent = {
            Text(
                text = ingrediente.nombre,
                color = ColorP1,
                fontWeight = FontWeight.Medium
            )
        },
        supportingContent = {
            Text(
                text = ingrediente.cant,
                color = Color.White,
                fontSize = 14.sp
            )
        },
        colors = ListItemDefaults.colors(
            containerColor = Color.Transparent
        )
    )
}
