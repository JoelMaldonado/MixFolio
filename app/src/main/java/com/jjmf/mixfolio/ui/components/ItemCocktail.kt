package com.jjmf.mixfolio.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jjmf.mixfolio.R
import com.jjmf.mixfolio.domain.model.Cocktail
import com.jjmf.mixfolio.ui.theme.ColorP1


@Composable
fun ItemCocktail(
    cocktail: Cocktail,
    click: () -> Unit,
    clickFavorito:()->Unit
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        AsyncImage(
            model = cocktail.img,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable { click() },
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.pisco_sour)
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "$${cocktail.precio}",
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = cocktail.nombre,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = cocktail.id,
                    color = Color.White,
                    fontSize = 12.sp
                )
            }

            IconButton(
                onClick = clickFavorito
            ) {
                Icon(
                    imageVector = if (cocktail.favorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = ColorP1
                )
            }

        }
    }
}