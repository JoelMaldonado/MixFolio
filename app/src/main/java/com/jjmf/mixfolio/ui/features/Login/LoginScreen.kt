package com.jjmf.mixfolio.ui.features.Login

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.jjmf.mixfolio.R
import com.jjmf.mixfolio.ui.components.CajaTexto
import com.jjmf.mixfolio.ui.theme.ColorFondo
import com.jjmf.mixfolio.ui.theme.ColorP1

@Composable
fun LoginScreen(
    toMenu: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {

    if (viewModel.toMenu) {
        LaunchedEffect(key1 = Unit) {
            toMenu()
            viewModel.toMenu = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorFondo)
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.ic_mix_folio),
            contentDescription = "Login Image",
            modifier = Modifier.size(150.dp),
            tint = ColorP1
        )
        Text(
            text = "Login",
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            modifier = Modifier.align(Alignment.Start),
            color = Color.White
        )
        CajaTexto(
            icon = Icons.Outlined.Person,
            valor = viewModel.usuario,
            newValor = { viewModel.usuario = it },
            label = "Usuario"
        )
        CajaTexto(
            icon = Icons.Outlined.Lock,
            valor = viewModel.clave,
            newValor = { viewModel.clave = it },
            label = "Contraseña"
        )
        Button(
            onClick = toMenu,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Iniciar Sesión")
        }
        BotonGoogle(
            viewModel = viewModel
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun BotonGoogle(
    viewModel: LoginViewModel,
) {
    val context = LocalContext.current
    val firebaseAuth = FirebaseAuth.getInstance()

    LaunchedEffect(key1 = firebaseAuth.currentUser) {
        if (firebaseAuth.currentUser != null) {
            viewModel.toMenu = true
        }
    }

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        viewModel.loginGoogle(task, firebaseAuth)
    }

    Button(
        onClick = {
            googleSignInLauncher.launch(viewModel.getIntent(context))
        },
        modifier = Modifier
            .fillMaxWidth(),
        enabled = !viewModel.cargando
    ) {
        if (viewModel.cargando) {
            CircularProgressIndicator()
        } else {
            Icon(imageVector = Icons.Default.Person, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Inicia Sesión con Google")
        }
    }
}
