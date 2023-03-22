package com.temotion.mirzas.datafetchingmvvmhiltretro.Network


import com.temotion.mirzas.binandroidtest.Model.ImageList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("list")
    suspend fun getImagelist(): List<ImageList>
}