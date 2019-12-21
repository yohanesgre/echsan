package com.vira.echsan.api

import com.vira.echsan.api.resp.TransactionResp
import com.vira.echsan.data.entities.InputJamaahResp
import com.vira.echsan.data.entities.PaymentResp
import com.vira.echsan.features.umroh.data.PaketUmroh
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

/**
 * Lego REST API access points
 */
interface UmrohService {

    @GET("product-umroh")
    suspend fun getPaketUmrohs(
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null
    ): Response<ResultsResponse<PaketUmroh>>

    @GET("product-umroh/{id}")
    suspend fun getPaketUmroh(@Path("id") id: Int): Response<PaketUmroh>

    @FormUrlEncoded
    @POST("checkout/{id}/input-jemaah")
    suspend fun postInputJemaah(
        @Path("id") id: Int,
        @Field("user_id") userId: Int,
        @Field("people_amount") peopleAmount: Int,
        @Field("full_name[]") fullName: ArrayList<String>,
        @Field("gender[]") gender: ArrayList<String>,
        @Field("birth_place[]") birthPlace: ArrayList<String>,
        @Field("birth_date[]") birthDate: ArrayList<String>,
        @Field("address[]") address: ArrayList<String>,
        @Field("RT[]") RT: ArrayList<Int>,
        @Field("RW[]") RW: ArrayList<Int>,
        @Field("kelurahan[]") kelurahan: ArrayList<String>,
        @Field("district[]") district: ArrayList<String>,
        @Field("city[]") city: ArrayList<String>,
        @Field("province[]") province: ArrayList<String>,
        @Field("pos_code[]") posCode: ArrayList<Int>,
        @Field("phone[]") phone: ArrayList<String>
    ): Response<InputJamaahResp>

    @Multipart
    @POST("transaction-payment ")
    suspend fun postPayment(
        @PartMap text: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part paidFile: MultipartBody.Part
    ): Response<PaymentResp>

    @FormUrlEncoded
    @POST("transaction-user-undone")
    suspend fun postTransactionUserUndone(
        @Field("user_id") userId: Int,
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null
    ): Response<ResultsResponse<TransactionResp>>

    @FormUrlEncoded
    @POST("transaction-user-done")
    suspend fun postTransactionUserDone(
        @Field("user_id") userId: Int,
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null
    ): Response<ResultsResponse<TransactionResp>>

    @FormUrlEncoded
    @POST("transaction")
    suspend fun postTransactionSearch(@Path("transaction_code") code: String): Response<TransactionResp>
}
