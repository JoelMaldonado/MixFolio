package com.jjmf.mixfolio.data.module

import com.jjmf.mixfolio.data.repository.TragoRepository
import com.jjmf.mixfolio.domain.repository.TragoRepositoryImpl
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    abstract fun tragoRepo(impl : TragoRepositoryImpl) : TragoRepository
}