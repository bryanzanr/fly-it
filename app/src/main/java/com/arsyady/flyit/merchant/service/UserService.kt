package com.arsyady.flyit.merchant.service

import com.arsyady.flyit.merchant.dto.request.LoginRequest
import com.arsyady.flyit.merchant.dto.request.RegisterRequest
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    @Headers("Content-Type: application/json")
    @POST("login")
    fun loginRequest(@Body body: LoginRequest): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @POST("register")
    fun registerRequest(@Body body: RegisterRequest): Call<ResponseBody>

    @DELETE("user/{iduser}")
    abstract fun deleteUser(@Path("iduser") iduser: String): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @PUT("user/{iduser}")
    fun editRequest(@Path("iduser") iduser: String,
                    @Body body: RegisterRequest): Call<ResponseBody>

}