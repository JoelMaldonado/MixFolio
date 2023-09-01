package com.jjmf.mixfolio.ui.features.Login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jjmf.mixfolio.data.repository.TragoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: TragoRepository,
) : ViewModel() {

    var username by mutableStateOf("")
    var password by mutableStateOf("")
}