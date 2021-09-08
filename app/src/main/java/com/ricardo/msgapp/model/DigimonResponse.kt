package com.ricardo.msgapp.model

data class DigimonResponse(
    val respuesta: List<Digimon>? = null,
    val errorMsg: String? = null,
    val success: Boolean? = null
)
