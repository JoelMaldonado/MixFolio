package com.jjmf.mixfolio.ui.features.Ingredientes

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.jjmf.mixfolio.data.repository.CocktailRepository
import com.jjmf.mixfolio.domain.model.Ingrediente
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IngredientesViewModel @Inject constructor(
    private val repository: CocktailRepository,
) : ViewModel() {

    var cargando by mutableStateOf(true)
    var error by mutableStateOf<String?>(null)
    var list by mutableStateOf<List<Ingrediente>>(emptyList())

    fun init(){
        try {
            cargando = true
        }catch (e:Exception){
            error = e.message
        }finally {
            cargando = false
        }
    }
}