package com.ricardo.msgapp.retrofit

import com.ricardo.msgapp.model.Digimon
import com.ricardo.msgapp.model.DigimonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("name/{nombreDigimon}")
    fun getNombreDigimon(@Path("nombreDigimon") nombreDigimon: String): Call<List<Digimon>>

}
