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

    @TragoCollection
    @Singleton
    @Provides
    fun provideTrago() = provideFirebase().collection(Constants.FB_TRAGOS)

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class TragoCollection

}