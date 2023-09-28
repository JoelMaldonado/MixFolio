package com.jjmf.mixfolio.domain.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.QuerySnapshot
import com.jjmf.mixfolio.core.EstadosResult
import com.jjmf.mixfolio.data.dto.CocktailDto
import com.jjmf.mixfolio.data.dto.toCocktailDto
import com.jjmf.mixfolio.data.module.FirebaseModule
import com.jjmf.mixfolio.data.repository.CocktailRepository
import com.jjmf.mixfolio.data.repository.IngredienteRepository
import com.jjmf.mixfolio.domain.model.Cocktail
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    @FirebaseModule.CocktailCollection private val fb: CollectionReference,
    private val repository: IngredienteRepository,
) : CocktailRepository {

    override suspend fun getList(): List<Cocktail> {
        val list = fb.get().await().documents
        return list.mapNotNull { map ->
            val obj = map.toCocktailDto()
            obj?.toDomain(map.id)
        }
    }

    override suspend fun add(cocktail: CocktailDto): EstadosResult<Boolean> {
        return try {
            fb.add(cocktail).await()
            EstadosResult.Correcto(true)
        } catch (e: Exception) {
            EstadosResult.Error(e.message.toString())
        }
    }

    override suspend fun update(cocktail: Cocktail): EstadosResult<Boolean> {
        return try {
            fb.document(cocktail.id).update("favorito", cocktail.favorito).await()
            EstadosResult.Correcto(true)
        } catch (e: Exception) {
            EstadosResult.Error(e.message.toString())
        }
    }

    override suspend fun get(id: String): Cocktail? {
        return fb.document(id).get().await().toCocktailDto()?.toDomain(id)
    }

    override suspend fun delete(id: String): EstadosResult<Boolean> {
        return try {
            fb.document(id).delete().await()
            EstadosResult.Correcto(true)
        } catch (e: Exception) {
            EstadosResult.Error(e.message.toString())
        }
    }

}