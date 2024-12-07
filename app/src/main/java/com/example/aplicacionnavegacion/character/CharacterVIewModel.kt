package com.example.aplicacionnavegacion.character

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacionnavegacion.apiconfig.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class CharacterViewModel : ViewModel() {
    private val apiService = ApiClient.retrofit.create(ApiServiceCharacter::class.java)

    // Estados
    var isLoading by mutableStateOf(false)
        private set

    var getResponse: List<Result>? by mutableStateOf(null)
        private set

    var error: String? by mutableStateOf(null)
        private set

    fun fetchGetData() {
        isLoading = true // Indicar que está cargando
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getCharacter(pages = "1").awaitResponse()
                if (response.isSuccessful) {
                    getResponse = response.body()?.results
                } else {
                    error = "Error: ${response.body()?.error}"
                }
            } catch (e: Exception) {
                error = "Error: ${e.message}"
            } finally {
                isLoading = false // Finaliza la carga
            }
        }
    }
}
