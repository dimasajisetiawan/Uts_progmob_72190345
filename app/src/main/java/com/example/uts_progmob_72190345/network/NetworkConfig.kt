package com.example.uts_progmob_72190345.network

import com.example.uts_progmob_72190345.model.ResponseAddMatkul
import com.example.uts_progmob_72190345.model.ResponseMatkul
import com.example.uts_progmob_72190345.model.ResponseMatkulItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class NetworkConfig {
    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return okHttpClient
    }
    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
//        .baseUrl("https://jsonplaceholder.typicode.com/")
            .baseUrl("http://192.168.3.253/uts-slim/public/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getService() = getRetrofit().create(Users::class.java)
}
interface Users {
    @GET("api/progmob/matkul/{nim_progmob}")
    fun getMatkulNim(@Path("nim_progmob") nim_progmob : String): Call<List<ResponseMatkulItem>>
    @GET("api/progmob/matkul/{nim_progmob}/{kode}")
    fun getMatkulKode(@Path("nim_progmob") nim_progmob: String,
                      @Path("kode") kode : String):
            Call<ResponseMatkul>
    @POST("api/progmob/matkul/create")
    fun addMatkul(@Body req : ResponseMatkulItem) : Call<ResponseAddMatkul>
    @PUT("api/progmob/matkul/update")
    fun updateMatkul(@Body req : ResponseMatkulItem) : Call<ResponseAddMatkul>
    @DELETE("api/progmob/matkul/delete")
    fun deleteMatkul(@Body req : ResponseMatkulItem) : Call<ResponseAddMatkul>
}