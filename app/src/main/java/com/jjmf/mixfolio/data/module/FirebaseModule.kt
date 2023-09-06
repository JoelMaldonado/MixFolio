package com.jjmf.mixfolio.data.module

import com.google.firebase.firestore.FirebaseFirestore
import com.jjmf.mixfolio.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebase() = FirebaseFirestore.getInstance()

    @UsuarioCollection
    @Singleton
    @Provides
    fun provideUsuario() = provideFirebase().collection(Constants.FB_USUARIOS)

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UsuarioCollection

    @CocktailCollection
    @Singleton
    @Provides
    fun provideCocktail() = provideFirebase().collection(Constants.FB_COCKTAIL)

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CocktailCollection

    @IngredientesCollection
    @Singleton
    @Provides
    fun provideIngrediente() = provideFirebase().collection(Constants.FB_INGREDIENTES)

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class IngredientesCollection

}