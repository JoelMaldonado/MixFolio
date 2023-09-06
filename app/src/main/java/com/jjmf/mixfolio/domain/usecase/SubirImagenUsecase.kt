package com.jjmf.mixfolio.domain.usecase

import android.content.Context
import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.jjmf.mixfolio.core.Compresor
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SubirImagenUsecase @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    suspend operator fun invoke(img: Uri?): String {
        return try {
            if (img==null){
                "Sin Imagen"
            }else{
                val file = Compresor().compressImage(
                    context = context,
                    uri = img,
                    namePhoto = img.lastPathSegment ?: System.currentTimeMillis().toString()
                )
                FirebaseStorage.getInstance().reference
                    .child("Fotos_Cocktails")
                    .child("${System.currentTimeMillis()}.jpeg")
                    .putFile(Uri.fromFile(file)).await().storage.downloadUrl.await().toString()
            }
        } catch (e: Exception) {
            "Error al comprimir"
        }

    }
}