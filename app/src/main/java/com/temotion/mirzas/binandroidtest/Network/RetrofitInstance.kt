package com.temotion.mirzas.datafetchingmvvmhiltretro.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object{
        val BaseURL = "https://picsum.photos/v2/"

        fun getRetroInstance():Retrofit{
            return Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}