package com.vira.echsan.features.umroh.data

data class InputJamaah(
    var userId: Int?,
    var peopleAmount: Int?,
    var jamaahOrder: ArrayList<Int>?,
    var fullName: ArrayList<String>?,
    var gender: ArrayList<String>?,
    var birthPlace: ArrayList<String>?,
    var birthDate: ArrayList<String>?,
    var address: ArrayList<String>?,
    var RT: ArrayList<Int>?,
    var RW: ArrayList<Int>?,
    var kelurahan: ArrayList<String>?,
    var district: ArrayList<String>?,
    var city: ArrayList<String>?,
    var province: ArrayList<String>?,
    var posCode: ArrayList<Int>?,
    var phone: ArrayList<String>?
)