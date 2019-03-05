package com.arsyady.flyit.merchant.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ProfileService {

    // Fungsi ini untuk memanggil API http://10.0.2.2/mahasiswa/login.php
    @FormUrlEncoded
    @POST("login/")
    fun loginRequest(@Field("email") email: String,
                              @Field("password") password: String): Call<ResponseBody>

    // Fungsi ini untuk memanggil API http://10.0.2.2/mahasiswa/register.php
    @FormUrlEncoded
    @POST("register/")
    fun registerRequest(@Field("first_name") first_name: String,
                        @Field("last_name") last_name: String,
                        @Field("merchant_name") merchant_name: String,
                                 @Field("email") email: String,
                        @Field("password") password: String,
                                 @Field("repeat_password") repeat_password: String): Call<ResponseBody>

//    @GET("semuadosen")
//    abstract fun getSemuaDosen(): Call<ResponseDosen>
//
//    @GET("dosen/{namadosen}")
//    abstract fun getDetailDosen(@Path("namadosen") namadosen: String): Call<ResponseDosenDetail>
//
//    @GET("matkul")
//    abstract fun getSemuaMatkul(): Call<ResponseMatkul>
//
//    @FormUrlEncoded
//    @POST("matkul")
//    abstract fun simpanMatkulRequest(@Field("nama_dosen") namadosen: String,
//                                     @Field("matkul") namamatkul: String): Call<ResponseBody>
//
//    @DELETE("matkul/{idmatkul}")
//    abstract fun deteleMatkul(@Path("idmatkul") idmatkul: String): Call<ResponseBody>
}