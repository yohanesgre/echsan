package com.vira.echsan.network

import com.vira.echsan.model.*
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiRequest {
    //##########################################################
    //AUTH
    @POST("login")
    @FormUrlEncoded
    fun login(
            @Field("username") username: String,
            @Field("password") password: String): Observable<LoginResponse>

    @POST("registrasi")
    @FormUrlEncoded
    fun registrasi(
        @Field("paket_member") paket_member: String,
        @Field("nama_lengkap") nama_lengkap: String,
        @Field("email") email: String,
        @Field("nik") nik: String,
        @Field("no_hp") no_hp: String,
        @Field("kode_referral") kode_referral: String): Observable<SimpleResponse>

    @Multipart
    @POST("konfirmasi")
    fun konfirmasi(
        @Part file: MultipartBody.Part,
        @Part("email") email: RequestBody): Observable<SimpleResponse>

    @GET("paket-member")
    fun paketMember(): Observable<PaketMemberResp>

    @POST("dashboard")
    @FormUrlEncoded
    fun dashboard(
        @Field("token") token: String): Observable<DashboardResponse>

    @POST("my-voucher")
    @FormUrlEncoded
    fun myVoucher(
        @Field("token") token: String): Observable<SimpleResponse>

    //##########################################################
    //PAKET
    @POST("paket-umroh")
    @FormUrlEncoded
    fun umroh(
        @Field("meta") meta: String,
        @Field("start") start: String,
        @Field("length") length: String,
        @Field("search") search: String,
        @Field("sorting") sorting: String): Observable<UmrohResponse>

    @POST("paket-umroh/detail")
    @FormUrlEncoded
    fun umrohDetail(
        @Field("meta") meta: String,
        @Field("id") id: String): Observable<UmrohDetailResponse>

    @POST("paket-haji")
    @FormUrlEncoded
    fun haji(
        @Field("meta") meta: String,
        @Field("start") start: String,
        @Field("length") length: String,
        @Field("search") search: String,
        @Field("sorting") sorting: String): Observable<UmrohResponse>

    @POST("paket-haji/detail")
    @FormUrlEncoded
    fun hajiDetail(
        @Field("meta") meta: String,
        @Field("id") id: String): Observable<UmrohDetailResponse>


    //##########################################################
    //TRANSAKSI

    @POST("beli-paket")
    @FormUrlEncoded
    fun beliPaket(
        @Field("token") token: String,
        @Field("id_produk") id_produk: String): Observable<BeliPaketResponse>

    @Multipart
    @POST("transaksi/konfirmasi")
    fun transaksiKonfirmasi(
        @Part file: MultipartBody.Part,
        @Part("token") token: RequestBody,
        @Part("id_transaksi") id_transaksi: RequestBody): Observable<SimpleResponse>

    @POST("transaksi/riwayat")
    @FormUrlEncoded
    fun transaksiRiwayat(
        @Field("token") token: String,
        @Field("start") start: String,
        @Field("length") length: String,
        @Field("search") search: String,
        @Field("sorting") sorting: String): Observable<RiwayatTransaksiResponse>

    //##########################################################
    // PROFILE

    @POST("profil")
    @FormUrlEncoded
    fun profile(
        @Field("token") token: String): Observable<ProfileResponse>

    @POST("profil/save")
    @FormUrlEncoded
    fun profileSave(
        @Field("token") token: String,
        @Field("nama_profil") nama_profil: String,
        @Field("jenis_kelamin") jenis_kelamin: String,
        @Field("nama_lengkap") nama_lengkap: String,
        @Field("tempat_lahir") tempat_lahir: String,
        @Field("tanggal_lahir") tanggal_lahir: String,
        @Field("alamat") alamat: String,
        @Field("rt") rt: String,
        @Field("rw") rw: String,
        @Field("kelurahan") kelurahan: String,
        @Field("kecamatan") kecamatan: String,
        @Field("kota") kota: String,
        @Field("provinsi") provinsi: String,
        @Field("kode_pos") kode_pos: String,
        @Field("no_ktp") no_ktp: String,
        @Field("file_ktp") file_ktp: String,
        @Field("no_kk") no_kk: String,
        @Field("file_kk") file_kk: String,
        @Field("no_npwp") no_npwp: String,
        @Field("file_npwp") file_npwp: String,
        @Field("no_telepon") no_telepon: String,
        @Field("no_hp") no_hp: String): Observable<SimpleResponse>

    @POST("jaringan")
    @FormUrlEncoded
    fun jaringan(
        @Field("token") token: String): Observable<SimpleResponse>

    @POST("jaringan")
    @FormUrlEncoded
    fun jaringanTingkat(
        @Field("token") token: String): Observable<SimpleResponse>

    //##########################################################
    // REDEEM

    @POST("redeem/pengajuan")
    @FormUrlEncoded
    fun redeemPengajuan(
        @Field("token") token: String,
        @Field("jumlah_poin") jumlah_poin: String): Observable<SimpleResponse>

    @POST("redeem/riwayat")
    @FormUrlEncoded
    fun redeemRiwayat(
        @Field("token") token: String,
        @Field("start") start: String,
        @Field("length") length: String,
        @Field("search") search: String,
        @Field("sorting") sorting: String): Observable<RiwayatRedeemResponse>

}