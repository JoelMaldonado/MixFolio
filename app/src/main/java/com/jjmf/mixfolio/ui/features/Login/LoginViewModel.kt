package com.jjmf.mixfolio.ui.features.Login

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.jjmf.mixfolio.app.BaseApp
import com.jjmf.mixfolio.data.repository.CocktailRepository
import com.jjmf.mixfolio.util.show
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: CocktailRepository,
) : ViewModel() {

    var usuario by mutableStateOf("")
    var clave by mutableStateOf("")
    var toMenu by mutableStateOf(false)
    var cargando by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun loginGoogle(task: Task<GoogleSignInAccount>, instancia : FirebaseAuth) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                cargando = true
                val idToken = task.await().idToken
                val credential = GoogleAuthProvider.getCredential(idToken, null)
                val call = instancia.signInWithCredential(credential).await()
                if (call.user!=null){
                    BaseApp.prefs.saveUsuario(call.user?.displayName.toString())
                    toMenu = true
                }else error = "No encontro el usuario"
            } catch (e: ApiException) {
                error = "No se pudo iniciar con Google"
            } finally {
                cargando = false
            }
        }
    }

    fun getIntent(context:Context) : Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("368567619987-bmrb1fla8i12i7vdbss6ihlpusridhub.apps.googleusercontent.com")
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context, gso).signInIntent
    }
}