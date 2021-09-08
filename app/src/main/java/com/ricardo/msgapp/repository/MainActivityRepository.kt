package com.ricardo.msgapp.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.ricardo.msgapp.model.Digimon
import com.ricardo.msgapp.model.DigimonResponse
import com.ricardo.msgapp.retrofit.RetrofitClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object MainActivityRepository {

    val serviceSetterGetter = MutableLiveData<DigimonResponse>()

    fun getServicesApiCall(nombreDigimon: String): MutableLiveData<DigimonResponse> {

        val call = RetrofitClient.apiInterface.getNombreDigimon(nombreDigimon)

        call.enqueue(object: Callback<List<Digimon>> {
            override fun onFailure(call: Call<List<Digimon>>, t: Throwable) {
                Log.v("DEBUG ERROR: ", t.message.toString())
                serviceSetterGetter.value = DigimonResponse(null, "Ocurrió un error, intente más tarde", false)
            }

            override fun onResponse(
                call: Call<List<Digimon>>,
                response: Response<List<Digimon>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()

                    val digimons = data!!

                    serviceSetterGetter.value = DigimonResponse(digimons, null, true)
                } else {
                    try {
                        val jObjError = JSONObject(response.errorBody()!!.string()).getString("ErrorMsg")
                        serviceSetterGetter.value = DigimonResponse(null, jObjError, false)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        })

        return serviceSetterGetter
    }
}
