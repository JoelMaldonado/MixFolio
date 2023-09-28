package com.jjmf.mixfolio.ui.features.Cocktail.Agregar

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.jjmf.mixfolio.core.EstadosResult
import com.jjmf.mixfolio.data.dto.CocktailDto
import com.jjmf.mixfolio.data.repository.CocktailRepository
import com.jjmf.mixfolio.data.repository.IngredienteRepository
import com.jjmf.mixfolio.domain.model.Ingrediente
import com.jjmf.mixfolio.domain.usecase.SubirImagenUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTragoViewModel @Inject constructor(
    private val repository: CocktailRepository,
    private val subirImagenUsecase: SubirImagenUsecase,
    private val repoIng: IngredienteRepository
) : ViewModel() {

    var buscar by mutableStateOf("")
    var isBuscar by mutableStateOf(false)
    var ingrediente by mutableStateOf("")
    var img by mutableStateOf<Uri?>(null)
    var nombre by mutableStateOf("")

    var preparacion by mutableStateOf("")

    var listIngredientAdd = mutableStateListOf<Ingrediente>()

    var listIngredientesTotal = emptyList<Ingrediente>()
    var listIngredients by mutableStateOf(listIngredientesTotal)

    var cargando by mutableStateOf(false)
    var back by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun addCocktail() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                cargando = true
                val cocktailDto = CocktailDto(
                    nombre = nombre,
                    preparacion = preparacion,
                    img = subirImagenUsecase(img),
                    ingredientes = listIngredientAdd.map { it.toDto() },
                    favorito = false,
                    usuario = FirebaseAuth.getInstance().currentUser?.uid
                )
                when (val res = repository.add(cocktailDto)) {
                    is EstadosResult.Correcto -> if (res.datos == true) back = true
                    is EstadosResult.Error -> error = res.mensajeError
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                cargando = false
            }
        }
    }

    fun init() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                repoIng.getFlow().collect{
                    listIngredientesTotal = it
                    listIngredients = listIngredientesTotal
                }
            }catch (e:Exception){

            }
        }
    }
}