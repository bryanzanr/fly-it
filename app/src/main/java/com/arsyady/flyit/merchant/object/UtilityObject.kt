package com.arsyady.flyit.merchant.`object`

import com.arsyady.flyit.merchant.activity.LoginActivity
import com.arsyady.flyit.merchant.service.ProfileService

object UtilityObject {

    // Mendeklarasikan Interface BaseApiService
    fun getAPIService(): ProfileService {
        return RetrofitObject.getClient("https://fly-it.herokuapp.com/polls/")!!
                .create(ProfileService::class.java)
    }

}