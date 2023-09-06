package com.jjmf.mixfolio.data.module

import com.jjmf.mixfolio.data.repository.CocktailRepository
import com.jjmf.mixfolio.data.repository.IngredienteRepository
import com.jjmf.mixfolio.domain.repository.CocktailRepositoryImpl
import com.jjmf.mixfolio.domain.repository.IngredienteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun cocktailRepo(impl : CocktailRepositoryImpl) : CocktailRepository

    @Binds
    abstract fun ingredienteRepo(impl : IngredienteRepositoryImpl) : IngredienteRepository

}