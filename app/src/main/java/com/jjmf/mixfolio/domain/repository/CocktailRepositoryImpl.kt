package com.jjmf.mixfolio.domain.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import com.jjmf.mixfolio.core.EstadosResult
import com.jjmf.mixfolio.data.dto.CocktailDto
import com.jjmf.mixfolio.data.module.FirebaseModule
import com.jjmf.mixfolio.data.repository.CocktailRepository
import com.jjmf.mixfolio.domain.model.Cocktail
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    @FirebaseModule.CocktailCollection private val fb: CollectionReference,
) : CocktailRepository {

    override suspend fun getFlow(): Flow<List<Cocktail>> = callbackFlow {
        val listado = fb.addSnapshotListener { sna, _ ->
            val lista = mutableListOf<CocktailDto>()
            sna?.forEach {
                val product = it.toObject(CocktailDto::class.java)
                product.id = it.id
                lista.add(product)
            }
            trySend(lista.map { it.toDomain() }).isSuccess
        }
        awaitClose { listado.remove() }
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
            fb.document(cocktail.id).set(cocktail)
            EstadosResult.Correcto(true)
        } catch (e: Exception) {
            EstadosResult.Error(e.message.toString())
        }
    }

    override suspend fun get(id: String): Cocktail? {
        val cock =  fb.document(id).get().await().toObject(CocktailDto::class.java).also {
            it?.id = id
        }?.toDomain()

        return cock
    }

}