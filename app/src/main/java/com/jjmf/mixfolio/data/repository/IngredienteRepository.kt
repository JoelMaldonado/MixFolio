package com.jjmf.mixfolio.data.repository

import com.jjmf.mixfolio.core.EstadosResult
import com.jjmf.mixfolio.data.dto.IngredienteDto
import com.jjmf.mixfolio.domain.model.Ingrediente
import kotlinx.coroutines.flow.Flow

interface IngredienteRepository {
    suspend fun getFlow() : Flow<List<Ingrediente>>
    suspend fun getList() : List<Ingrediente>
    suspend fun add(ingrediente: IngredienteDto) : EstadosResult<Boolean>
}