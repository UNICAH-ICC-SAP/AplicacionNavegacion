package com.example.aplicacionnavegacion.character

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

data class LocationModel(
    val name: String,
    val url: String
)

data class Result(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationModel,
    val location: LocationModel,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

data class Info(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String
)

data class Response(
    val info: Info,
    val results: List<Result>,
    val error: String
)

interface ApiServiceCharacter {
    @GET("character/")
    fun getCharacter(@Query("pages") pages: String): Call<Response>

//    @POST("endpoint/post")
//    fun postExample(@Body request: ExampleRequest): Call<ExampleResponse>
}
