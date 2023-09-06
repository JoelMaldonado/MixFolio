package com.jjmf.mixfolio.ui.features.Cocktail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
class CocktailViewModel @Inject constructor(
    private val repository: CocktailRepository,
) : ViewModel() {

    var buscar by mutableStateOf("")
    var cargando by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    var listCocktails by mutableStateOf<List<Cocktail>>(emptyList())

    fun init() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                cargando = true
                repository.getFlow().collect{
                    listCocktails = it
                }
            }catch (e:Exception){
                error = e.message
            }finally {
                cargando = false
            }
        }
    }

    fun changeFavorito(cocktail: Cocktail) {
        viewModelScope.launch(Dispatchers.IO){
            try {
                when(val res = repository.update(cocktail.copy(favorito = !cocktail.favorito))){
                    is EstadosResult.Correcto -> {}
                    is EstadosResult.Error -> error = res.mensajeError
                }
            }catch (e:Exception){
                error = e.message
            }
        }
    }
}