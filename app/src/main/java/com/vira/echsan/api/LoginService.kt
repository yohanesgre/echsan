package com.vira.echsan.api

import com.vira.echsan.data.entities.Profile
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Lego REST API access points
 */
interface LoginService {

    companion object {
        const val ENDPOINT = "http://echsan.sepaystudio.com/api/"
    }

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<Profile>
}
