package com.jjmf.mixfolio.util.test

import android.content.ContentUris
import android.content.Context
import android.media.browse.MediaBrowser
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GaleriaViewModel @Inject constructor(

)  :ViewModel() {

    var images by mutableStateOf<List<Uri>>(emptyList())

    fun getImagesFromStorage(context: Context) {
        val imageList = mutableListOf<Uri>()
        /**Obtener Fotos**/
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME
        )
        val query = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

        val cursor = context.contentResolver.query(
            query,
            projection,
            null,
            null,
            sortOrder
        )
        cursor?.use { c ->
            val idColumn = c.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            while (c.moveToNext()) {
                val id = c.getLong(idColumn)
                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id
                )
                imageList.add(contentUri)
            }
        }
        images = imageList
    }
}