package com.jjmf.mixfolio.domain.repository

import com.google.firebase.firestore.CollectionReference
import com.jjmf.mixfolio.data.dto.TragoDto
import com.jjmf.mixfolio.data.module.FirebaseModule
import com.jjmf.mixfolio.data.repository.TragoRepository
import com.jjmf.mixfolio.domain.model.Trago
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class TragoRepositoryImpl @Inject constructor(
    @FirebaseModule.TragoCollection private val fb: CollectionReference
) : TragoRepository {

    override suspend fun getFlow(): Flow<List<Trago>> = callbackFlow{
        val listado = fb.addSnapshotListener { sna, _ ->
            val lista = mutableListOf<TragoDto>()
            sna?.forEach {
                val product = it.toObject(TragoDto::class.java)
                product.id = it.id
                lista.add(product)
            }
            trySend(lista.map { it.toDomain() }).isSuccess
        }
        awaitClose { listado.remove() }
    }

}