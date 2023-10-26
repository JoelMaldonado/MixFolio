package com.jjmf.mixfolio.ui.features.Cocktail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.FirebaseAuth
import com.jjmf.mixfolio.ui.components.CajaTexto
import com.jjmf.mixfolio.ui.components.ItemCocktail
import com.jjmf.mixfolio.ui.theme.ColorCard
import com.jjmf.mixfolio.ui.theme.ColorFondo
import com.jjmf.mixfolio.util.show
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailScreen(
    toAdd: () -> Unit,
    toDetalle: (id: String) -> Unit,
    back: () -> Unit,
    viewModel: CocktailViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.init()
    }

    val context = LocalContext.current

    viewModel.error?.let {
        context.show(it)
        Log.d("tagito", it)
        viewModel.error = null
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutine = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(
                modifier = Modifier
                    .width(300.dp)
                    .fillMaxHeight()
                    .background(ColorCard)
            ) {
                ListItem(
                    leadingContent = {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    },
                    headlineContent = {
                        Text(text = "Agregar")
                    },
                    modifier = Modifier.clickable {
                        toAdd()
                    },
                    colors = ListItemDefaults.colors(
                        containerColor = Color.Transparent,
                        leadingIconColor = Color.White,
                        headlineColor = Color.White
                    )
                )
                ListItem(
                    leadingContent = {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
                    },
                    headlineContent = {
                        Text(text = "Favoritos")
                    },
                    modifier = Modifier.clickable {
                    },
                    colors = ListItemDefaults.colors(
                        containerColor = Color.Transparent,
                        leadingIconColor = Color.White,
                        headlineColor = Color.White
                    )
                )
                ListItem(
                    leadingContent = {
                        Icon(imageVector = Icons.Default.Logout, contentDescription = null)
                    },
                    headlineContent = {
                        Text(text = "Cerrar Sesión")
                    },
                    modifier = Modifier.clickable {
                        FirebaseAuth.getInstance().signOut()
                        back()
                    },
                    colors = ListItemDefaults.colors(
                        containerColor = Color.Transparent,
                        leadingIconColor = Color.White,
                        headlineColor = Color.White
                    )
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorFondo),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            coroutine.launch {
                                drawerState.open()
                            }
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                    }
                },
                title = {
                    Text(text = "Menu")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White,
                )
            )
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(viewModel.listCocktails) {
                    ItemCocktail(
                        cocktail = it,
                        click = {
                            toDetalle(it.id)
                        },
                        clickFavorito = {
                            viewModel.changeFavorito(it)
                        }
                    )
                }
            }

        }
    }
}