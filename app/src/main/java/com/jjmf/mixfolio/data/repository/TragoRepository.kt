package com.jjmf.mixfolio.data.repository

import com.jjmf.mixfolio.domain.model.Trago
import kotlinx.coroutines.flow.Flow

interface TragoRepository {
    suspend fun getFlow() : Flow<List<Trago>>
}