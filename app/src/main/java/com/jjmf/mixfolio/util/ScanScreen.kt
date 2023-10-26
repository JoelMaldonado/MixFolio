package com.jjmf.mixfolio.util

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions


@Composable
fun ScanCreen() {

    val context = LocalContext.current

    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val textChanged = remember { mutableStateOf("") }
    val list = remember { mutableStateListOf<String>() }
    val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
    val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)
    val selectImg = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            imageUri.value = uri
        }
    )
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                IconButton(
                    modifier = Modifier.align(Alignment.BottomStart),
                    onClick = {
                        selectImg.launch("image/*")
                    }) {
                    Icon(
                        Icons.Filled.Add,
                        "add",
                        tint = Color.Blue
                    )
                }

                if (imageUri.value != null) {
                    AsyncImage(
                        model = imageUri.value,
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = "image"
                    )
                    IconButton(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        onClick = {
                            val image = InputImage.fromFilePath(context, imageUri.value!!)
                            recognizer.process(image)
                                .addOnSuccessListener {
                                    textChanged.value = it.text
                                }
                                .addOnFailureListener {
                                    Log.e("tagito", it.message.toString())
                                }
                            labeler.process(image)
                                .addOnSuccessListener { labels ->
                                    for (label in labels) {
                                        val text = label.text
                                        val confidence = label.confidence
                                        val index = label.index
                                        list.add(label.text)
                                    }
                                }
                                .addOnFailureListener {
                                    Log.e("tagito", it.message.toString())
                                }
                        }) {
                        Icon(
                            Icons.Filled.Search,
                            "scan",
                            tint = Color.Blue
                        )
                    }
                }

            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Gray)
                .verticalScroll(rememberScrollState())
        ) {
            /*Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                text = textChanged.value
            )*/
            Text(text = "Objetos")
            list.forEach {
                Text(text = it)
            }
        }
    }
}