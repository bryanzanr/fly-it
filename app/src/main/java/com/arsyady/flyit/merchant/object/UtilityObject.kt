package com.arsyady.flyit.merchant.`object`

import com.arsyady.flyit.merchant.service.ProfileService
import com.arsyady.flyit.merchant.service.UserService

object UtilityObject {

    // Mendeklarasikan Interface BaseApiService
    fun getAPIService(): ProfileService {
        return RetrofitObject.getClient("https://fly-it.herokuapp.com/polls/")!!
                .create(ProfileService::class.java)
    }

    fun getUserService(): UserService {
        return RetrofitObject.getClient("https://product-goridepay.herokuapp.com/")!!
                .create(UserService::class.java)
    }

}