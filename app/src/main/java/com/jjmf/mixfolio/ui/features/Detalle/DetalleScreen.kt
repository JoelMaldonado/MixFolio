package com.jjmf.mixfolio.ui.features.Detalle

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jjmf.mixfolio.R
import com.jjmf.mixfolio.ui.theme.ColorCard
import com.jjmf.mixfolio.ui.theme.ColorDivider
import com.jjmf.mixfolio.ui.theme.ColorFondo
import com.jjmf.mixfolio.ui.theme.ColorP1
import com.jjmf.mixfolio.ui.theme.ColorP2
import com.jjmf.mixfolio.ui.theme.ColorTextos
import com.jjmf.mixfolio.ui.theme.ColorTitulo

@Composable
fun DetalleScreen(
    viewModel: DetalleViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorFondo)
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(text = "Machu Picchu", color = Color.White, fontWeight = FontWeight.Medium)
                Divider(
                    modifier = Modifier.width(60.dp),
                    color = ColorDivider
                )
                Text(text = "Descripcion", fontSize = 12.sp, color = ColorTitulo)
                Text(
                    text = LoremIpsum(20).values.iterator().next(),
                    fontSize = 14.sp,
                    color = ColorTextos, lineHeight = 16.sp
                )
            }

            AsyncImage(
                model = "",
                contentDescription = null,
                error = painterResource(id = R.drawable.img_trago),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentScale = ContentScale.FillHeight
            )

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
            Text(text = "Lista de Ingredientes", color = Color.White, fontWeight = FontWeight.Medium)
        }

        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {

        }

    }
}

@Composable
fun CardIngrediente(
    modifier: Modifier,
    text:String,
    @DrawableRes ic:Int,
    click:()->Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = ColorFondo
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = ic,
                contentDescription = null,
                modifier = Modifier.size(70.dp),
                contentScale = ContentScale.FillHeight
            )
            Text(
                text = text,
                color = ColorP1,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 10.dp),
                textAlign = TextAlign.Center
            )
            IconButton(onClick = click) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    }
}
