package com.jjmf.mixfolio.ui.features.Cocktail.Agregar

import android.net.Uri
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.mixfolio.core.EstadosResult
import com.jjmf.mixfolio.data.dto.CocktailDto
import com.jjmf.mixfolio.data.repository.CocktailRepository
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
) : ViewModel() {

    var ingrediente by mutableStateOf("")
    var img by mutableStateOf<Uri?>(null)
    var nombre by mutableStateOf("")

    var preparacion by mutableStateOf("")
    var precio by mutableStateOf("")

    var listIngredientes = mutableStateListOf<Ingrediente>()

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
                    precio = precio.toDoubleOrNull(),
                    img = subirImagenUsecase(img)
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
}