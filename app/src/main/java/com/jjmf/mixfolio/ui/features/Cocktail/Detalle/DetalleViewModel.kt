package com.jjmf.mixfolio.ui.features.Cocktail.Detalle

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.mixfolio.core.EstadosResult
import com.jjmf.mixfolio.data.repository.CocktailRepository
import com.jjmf.mixfolio.domain.model.Cocktail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetalleViewModel @Inject constructor(
    private val repository: CocktailRepository,
) : ViewModel() {

    var error by mutableStateOf<String?>(null)
    var back by mutableStateOf(false)
    var cocktail by mutableStateOf<Cocktail?>(null)

    fun init(id: String) {
        viewModelScope.launch(Dispatchers.IO){
            try {
                cocktail = repository.get(id)
            }catch (e:Exception){
                error = e.message
            }
        }
    }

    fun delete(id:String) {
        viewModelScope.launch(Dispatchers.IO){
            try {
                when(val res = repository.delete(id)){
                    is EstadosResult.Correcto -> back = res.datos ?: false
                    is EstadosResult.Error -> error = res.mensajeError
                }
            }catch (e:Exception){
                error = e.message
            }
        }
    }
}