package com.arsyady.flyit.merchant.dto.request

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class LoginRequest (
        @SerializedName("username") val username: String?,
        @SerializedName("password") val password: String?
) {}

//class LoginRequest {
//
//    fun createRequest(username: String?, password: String?) : String {
//        val paramObject = JSONObject()
//        paramObject.put("username", username)
//        paramObject.put("password", password)
//        val paramObject = JsonObject()
//        paramObject.addProperty("username", username)
//        paramObject.addProperty("password", password)
//        return paramObject.toString()
//    }
//
//}