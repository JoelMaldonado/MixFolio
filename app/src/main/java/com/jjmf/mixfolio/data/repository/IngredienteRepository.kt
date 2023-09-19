package com.jjmf.mixfolio.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.jjmf.mixfolio.data.dto.IngredienteDto
import com.jjmf.mixfolio.data.module.FirebaseModule
import com.jjmf.mixfolio.domain.model.Ingrediente
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface IngredienteRepository {
    suspend fun getFlow() : Flow<List<Ingrediente>>
    suspend fun get(id: String): Ingrediente?
}

class IngredienteRepositoryImpl @Inject constructor(
    @FirebaseModule.IngredientesCollection private val fb: CollectionReference,
) : IngredienteRepository {
    override suspend fun getFlow(): Flow<List<Ingrediente>> = callbackFlow{
        val list = mutableListOf<Ingrediente>()
        val listado = fb.addSnapshotListener { snap, _ ->
            snap?.let {
                it.forEach {doc->
                    list.add(doc.toObject().toDomain())
                }
                trySend(list).isSuccess
            }
        }
        awaitClose { listado.remove() }
    }

    override suspend fun get(id: String): Ingrediente? {
        val ing = fb.document(id).get().await()
        val obj = ing.toObject(IngredienteDto::class.java)
        obj?.id = ing.id
        return obj?.toDomain()
    }

    private fun QueryDocumentSnapshot.toObject() : IngredienteDto{
        return IngredienteDto(
            id = id,
            nombre = getString("nombre"),
            img = getString("img"),
            tipo = getString("tipo")
        )
    }

}