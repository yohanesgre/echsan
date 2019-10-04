package com.vira.echsan.model

data class ProfileResponse(
    val status: String,
    val code: Int,
    val message: String,
    val data: DataProfile
)

data class DataProfile(
    val nama: String,
    val jenisMember: String,
    val jumlahPoint: String,
    val noMember: String,
    val kodeReferral: String,
    val myVoucher: Int,
    val myDownlink: Int,
    val myPoint: Int,
    val myRedeem: Int,
    val dataLengkap: DataLengkapProfile
)

data class DataLengkapProfile(
    val namaProfil: String,
    val jenisKelamin: String,
    val namaLengkap: String,
    val tempatLahir: String,
    val tanggalLahir: String,
    val alamat: String,
    val rt: String,
    val rw: String,
    val kelurahan: String,
    val kecamatan: String,
    val kota: String,
    val provinsi: String,
    val kodePos: String,
    val noKtp: String,
    val fileKtp: String,
    val noKk: String,
    val fileKk: String,
    val noNpwp: String,
    val fileNpwp: String,
    val noTelepon: String,
    val noHp: String,
    val email: String
)