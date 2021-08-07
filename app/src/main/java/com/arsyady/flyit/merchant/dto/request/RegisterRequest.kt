package com.arsyady.flyit.merchant.dto.request

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import okhttp3.RequestBody
import org.json.JSONObject

data class RegisterRequest (
    @SerializedName("email") val email: String?,
    @SerializedName("username") val username: String?,
    @SerializedName("password") val password: String?,
    @SerializedName("role") val role: String?
) {}

//class RegisterRequest{
//
//    fun createRequest(
//            email: String?,
//            username: String?,
//            password: String?,
//            role: String?
//    ) : RequestBody {
//        val paramObject = JSONObject()
//        paramObject.put("email", email)
//        paramObject.put("username", username)
//        paramObject.put("password", password)
//        paramObject.put("role", role)
//        val paramObject = JsonObject()
//        paramObject.addProperty("email", email)
//        paramObject.addProperty("username", username)
//        paramObject.addProperty("password", password)
//        paramObject.addProperty("role", role)
//        return paramObject.toString().toRequestBody("application/json".toMediaTypeOrNull())
//    }
//
//}