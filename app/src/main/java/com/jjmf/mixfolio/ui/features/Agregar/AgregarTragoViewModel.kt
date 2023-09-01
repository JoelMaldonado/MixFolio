package com.jjmf.mixfolio.ui.features.Agregar

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.jjmf.mixfolio.data.repository.TragoRepository
import com.jjmf.mixfolio.domain.model.Ingrediente
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AgregarTragoViewModel @Inject constructor(
    private val repository: TragoRepository,
) : ViewModel() {

    var ingrediente by mutableStateOf("")
    var img by mutableStateOf<String?>(null)
    var nombre by mutableStateOf("")

    var descrip by mutableStateOf("")

    var listIngredientes = mutableStateListOf<Ingrediente>()
}