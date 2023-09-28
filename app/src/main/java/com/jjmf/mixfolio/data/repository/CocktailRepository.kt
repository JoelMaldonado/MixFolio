package com.jjmf.mixfolio.data.repository

import com.jjmf.mixfolio.core.EstadosResult
import com.jjmf.mixfolio.data.dto.CocktailDto
import com.jjmf.mixfolio.domain.model.Cocktail

interface CocktailRepository {
    suspend fun getList() : List<Cocktail>
    suspend fun add(cocktail: CocktailDto) : EstadosResult<Boolean>
    suspend fun update(cocktail: Cocktail) : EstadosResult<Boolean>
    suspend fun get(id: String) : Cocktail?
    suspend fun delete(id:String) : EstadosResult<Boolean>
}