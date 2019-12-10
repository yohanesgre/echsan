package com.vira.echsan.api

import com.vira.echsan.data.entities.PaketUmroh
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Lego REST API access points
 */
interface UmrohService {

    /*@GET("lego/themes/")
    suspend fun getThemes(@Query("page") page: Int? = null,
                  @Query("page_size") pageSize: Int? = null,
                  @Query("ordering") order: String? = null): Response<ResultsResponse<LegoTheme>>*/

    @GET("product-umroh")
    suspend fun getPaketUmrohs(): Response<ResultsResponse<PaketUmroh>>

    @GET("product-umroh/{id}")
    suspend fun getPaketUmroh(@Path("id") id: Int): Response<PaketUmroh>
/*
    @GET("lego/sets/{id}/")
    suspend fun getSet(@Path("id") id: String): Response<LegoSet>*/
}
