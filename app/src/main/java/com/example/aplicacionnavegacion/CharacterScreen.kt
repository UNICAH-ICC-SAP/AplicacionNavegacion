package com.example.aplicacionnavegacion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplicacionnavegacion.character.CharacterViewModel
import com.example.aplicacionnavegacion.customcomponents.ImageCard

@Composable
fun CharacterScreen(){
    val apiViewModel: CharacterViewModel = viewModel()

    val isLoading = apiViewModel.isLoading
    val getResponse = apiViewModel.getResponse

    LaunchedEffect(Unit) {
        apiViewModel.fetchGetData()
    }
    Column(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                // Mostrar indicador de carga
                CircularProgressIndicator()
            }
            getResponse != null -> {
                // Mostrar contenido cuando la respuesta está lista
                getResponse.map {
                    ImageCard(it.image, it.name)
                }
            }
            else -> {
                // Mostrar algo si no hay datos
                Text(text = "No data available.")
            }
        }
    }
}