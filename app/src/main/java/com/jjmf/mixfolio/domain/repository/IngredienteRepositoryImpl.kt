package com.jjmf.mixfolio.domain.repository

import com.google.firebase.firestore.CollectionReference
import com.jjmf.mixfolio.core.EstadosResult
import com.jjmf.mixfolio.data.dto.IngredienteDto
import com.jjmf.mixfolio.data.module.FirebaseModule
import com.jjmf.mixfolio.data.repository.IngredienteRepository
import com.jjmf.mixfolio.domain.model.Ingrediente
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class IngredienteRepositoryImpl @Inject constructor(
    @FirebaseModule.IngredientesCollection private val fb: CollectionReference,
) : IngredienteRepository {
    override suspend fun getFlow(): Flow<List<Ingrediente>> = callbackFlow {
        val listado = fb.addSnapshotListener { sna, _ ->
            val lista = mutableListOf<IngredienteDto>()
            sna?.forEach {
                val product = it.toObject(IngredienteDto::class.java)
                product.id = it.id
                lista.add(product)
            }
            trySend(lista.map { it.toDomain() }).isSuccess
        }
        awaitClose { listado.remove() }
    }

    override suspend fun getList(): List<Ingrediente> {
        return try {
            val lista = mutableListOf<Ingrediente>()
            fb.get().await().documents.forEach {
                val obj = it.toObject(IngredienteDto::class.java)
                obj?.id = it.id
                obj?.let { dto-> lista.add(dto.toDomain()) }
            }
            lista
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun add(ingrediente: IngredienteDto): EstadosResult<Boolean> {
        return try {
            fb.add(ingrediente).await()
            EstadosResult.Correcto(true)
        }catch (e:Exception){
            EstadosResult.Error(e.message.toString())
        }
    }
}