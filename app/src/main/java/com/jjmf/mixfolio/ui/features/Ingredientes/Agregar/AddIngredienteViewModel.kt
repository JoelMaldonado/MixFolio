package com.jjmf.mixfolio.ui.features.Ingredientes.Agregar

import androidx.lifecycle.ViewModel
import com.jjmf.mixfolio.data.repository.CocktailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddIngredienteViewModel @Inject constructor(
    private val repository: CocktailRepository,
) : ViewModel() {

}