package com.vira.echsan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vira.echsan.R
import com.vira.echsan.adapters.pembayaran.ImageMetodePembayaranItem
import com.vira.echsan.adapters.pembayaran.MetodePembayaranItem
import com.vira.echsan.adapters.pembayaran.SectionItem
import com.vira.echsan.data.entities.PaketUmroh

class UmrohSharedViewModel : ViewModel() {

    private val _selectedPaket = MutableLiveData<PaketUmroh>()
    val selectedPaket:LiveData<PaketUmroh> = _selectedPaket

    fun setSelectedPaket(paket:PaketUmroh){
        _selectedPaket.value = paket
    }

    private val _searchPaket = MutableLiveData<List<String>>()
    val searchPaket:LiveData<List<String>> = _searchPaket

    fun searchPaket(tipe:String, kota:String, tanggal:String){
        _searchPaket.value = listOf(tipe, kota, tanggal)
    }

    private val _selectedTanggal = MutableLiveData<Int>()
    val selectedTanggal:LiveData<Int> = _selectedTanggal

    val posSelectedTanggal = MutableLiveData<Int>()

    fun setSelectedTanggal(tanggal:Int) {
        _selectedTanggal.value = tanggal
    }

    private val _hargaTotal = MutableLiveData<String>()
    val hargaTotal:LiveData<String> = _hargaTotal

    fun setHargaTotal(harga:String){
        _hargaTotal.value = harga
    }

    private val _jumlahJamaah = MutableLiveData<Int>().apply {
        value = 0
    }
    val jumlahJamaah:LiveData<Int> = _jumlahJamaah

    fun addJumlahJamaah(){
        _jumlahJamaah.value = _jumlahJamaah.value?.plus(1)
    }
    fun minusJumlahJamaah(){
        _jumlahJamaah.value = _jumlahJamaah.value?.minus(1)
    }

    val listOfTanggal = listOf(12, 13, 14, 15, 16, 17, 18, 19, 20, 22)
    val listOfFasilitas = listOf("Visa Umroh", "Catering", "Zam-Zam", "Perlengkapan", "Test", "Test", "Test")
    val listOfRencanaPerjalanan = listOf(
        listOf("1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque sit amet sapien dignissim, consequat eros."),
        listOf("2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque sit amet sapien dignissim, consequat eros."),
        listOf("3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque sit amet sapien dignissim, consequat eros."))
    val progressCheckoutDesc = arrayOf("Pemesanan", "Pembayaran", "Selesai")
    val tipePembayaranSection = SectionItem("Pilih Tipe Pembayaran", R.color.colorPrimary, arrayListOf("Uang Muka - 5.000.000 per Jamaah", "Uang Muka - 10.000.000 per Jamaah"))
    val imgMetodePembayaran = listOf<ImageMetodePembayaranItem>(
        ImageMetodePembayaranItem(R.drawable.ic_dropdown), ImageMetodePembayaranItem(R.drawable.ic_dropdown_clicked)
    )
    val metodePembayaran = listOf<MetodePembayaranItem>(
        MetodePembayaranItem("Transfer Bank", imgMetodePembayaran)
    )
}