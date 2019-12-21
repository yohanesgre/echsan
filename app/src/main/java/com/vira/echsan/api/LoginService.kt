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

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("nik") nik: String,
        @Field("role_id") roleId: Int
    ): Response<Profile>

    @FormUrlEncoded
    @POST("update-password")
    suspend fun ubahPassword(
        @Field("user_id") userId: Int,
        @Field("old_password") oldPassword: String,
        @Field("new_password") newPassword: String,
        @Field("new_password_confirm") newPasswordConfirm: String
    ): Response<Profile>
}
