package com.vira.echsan.features.umroh.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vira.echsan.data.entities.InputJamaahResp
import com.vira.echsan.data.entities.PaymentResp
import com.vira.echsan.features.umroh.data.InputJamaah
import com.vira.echsan.features.umroh.data.PaketUmroh
import com.vira.echsan.features.umroh.data.Payment

class UmrohSharedViewModel : ViewModel() {

    var userId: Int? = null

    private val selectedPaket = MutableLiveData<PaketUmroh>()
    val SelectedPaket: LiveData<PaketUmroh> = selectedPaket

    fun UpdateSelectedPaket(item: PaketUmroh) {
        selectedPaket.postValue(item)
    }

    private val currentTransaction = MutableLiveData<InputJamaahResp>()
    val CurrentTransaction: LiveData<InputJamaahResp> = currentTransaction

    fun UpdateCurrentTransaction(item: InputJamaahResp) {
        currentTransaction.postValue(item)
    }

    private val currentPayment = MutableLiveData<Payment>()
    val CurrentPayment: LiveData<Payment> = currentPayment

    fun UpdateCurrentPayment(item: Payment) {
        currentPayment.postValue(item)
    }

    private val finalPayment = MutableLiveData<PaymentResp>()
    val FinalPayment: LiveData<PaymentResp> = finalPayment

    fun UpdateFinalPayment(item: PaymentResp) {
        finalPayment.postValue(item)
    }

    var totalPembayaran = 0.0

    var mInputJamaah = InputJamaah(
        0,
        0,
        ArrayList(),
        ArrayList(),
        ArrayList(),
        ArrayList(),
        ArrayList(),
        ArrayList(),
        ArrayList(),
        ArrayList(),
        ArrayList(),
        ArrayList(),
        ArrayList(),
        ArrayList(),
        ArrayList(),
        ArrayList()
    )

    fun setUserIDToInputJamaah(id: Int) {
        mInputJamaah.userId = id
    }

    fun setInputAmountToInputJamaah(amount: Int) {
        mInputJamaah.peopleAmount = amount
    }

    fun clearMInputJamaah() {
        mInputJamaah = InputJamaah(
            0,
            0,
            ArrayList(),
            ArrayList(),
            ArrayList(),
            ArrayList(),
            ArrayList(),
            ArrayList(),
            ArrayList(),
            ArrayList(),
            ArrayList(),
            ArrayList(),
            ArrayList(),
            ArrayList(),
            ArrayList(),
            ArrayList()
        )
    }

    fun insertJamaah(
        jamaahOrder: Int,
        fullName: String,
        gender: String,
        birthPlace: String,
        birthDate: String,
        address: String,
        RT: Int,
        RW: Int,
        kelurahan: String,
        district: String,
        city: String,
        province: String,
        posCode: Int,
        phone: String
    ) {
        mInputJamaah.jamaahOrder!!.add(jamaahOrder)
        mInputJamaah.fullName!!.add(fullName)
        mInputJamaah.gender!!.add(gender)
        mInputJamaah.birthPlace!!.add(birthPlace)
        mInputJamaah.birthDate!!.add(birthDate)
        mInputJamaah.address!!.add(address)
        mInputJamaah.RT!!.add(RT)
        mInputJamaah.RW!!.add(RW)
        mInputJamaah.kelurahan!!.add(kelurahan)
        mInputJamaah.district!!.add(district)
        mInputJamaah.city!!.add(city)
        mInputJamaah.province!!.add(province)
        mInputJamaah.posCode!!.add(posCode)
        mInputJamaah.phone!!.add(phone)
    }

    private val _jumlahJamaah by lazy {
        MutableLiveData<Int>().apply {
            value = 0
        }
    }

    val jumlahJamaah: LiveData<Int> = _jumlahJamaah

    fun addJumlahJamaah() {
        _jumlahJamaah.value?.let { a ->
            _jumlahJamaah.value = a + 1
        }
    }

    fun minusJumlahJamaah() {
        _jumlahJamaah.value?.let { a ->
            _jumlahJamaah.value = a - 1
        }
    }

    val listOfTanggal = listOf(12, 13, 14, 15, 16, 17, 18, 19, 20, 22)
    val listOfFasilitas =
        listOf("Visa Umroh", "Catering", "Zam-Zam", "Perlengkapan", "Test", "Test", "Test")
    val listOfRencanaPerjalanan = listOf(
        listOf(
            "1",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque sit amet sapien dignissim, consequat eros."
        ),
        listOf(
            "2",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque sit amet sapien dignissim, consequat eros."
        ),
        listOf(
            "3",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque sit amet sapien dignissim, consequat eros."
        )
    )
    val progressCheckoutDesc = arrayOf("Pemesanan", "Pembayaran", "Selesai")
}
