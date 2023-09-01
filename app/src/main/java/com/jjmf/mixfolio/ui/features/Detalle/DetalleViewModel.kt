package com.jjmf.mixfolio.ui.features.Detalle

import androidx.lifecycle.ViewModel
import com.jjmf.mixfolio.data.repository.TragoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetalleViewModel @Inject constructor(
    private val repository: TragoRepository,
) : ViewModel() {
}