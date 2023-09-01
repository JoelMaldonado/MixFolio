package com.jjmf.mixfolio.ui.features.Menu

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.jjmf.mixfolio.data.repository.TragoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val repository: TragoRepository,
) : ViewModel() {
    var buscar by mutableStateOf("")
}