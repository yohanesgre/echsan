package com.vira.echsan.ui.activity.hajiumroh

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.vira.echsan.R
import com.vira.echsan.helper.SessionManager
import com.vira.echsan.network.ApiClient
import com.vira.echsan.network.ApiRequest
import com.vira.echsan.util.Utils
import com.vira.echsan.util.iosdialog.IOSDialog
import com.vira.echsan.util.permission.PermissionListener
import com.vira.echsan.util.permission.PermissionManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_paket_detail.*
import kotlinx.android.synthetic.main.xml_toolbar.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import retrofit2.HttpException
import java.io.File

class PaketUmrohDetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var dialog: IOSDialog
    private lateinit var sessionManager: SessionManager
    private var id_paket = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paket_detail)
        init()
        id_paket = intent.getStringExtra("id_paket")
        getDetailUmroh(id_paket)
    }

    private fun init() {
        title = "Detail Paket"
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        sessionManager = SessionManager(this)
        dialog = IOSDialog.Builder(this)
            .setSpinnerColorRes(R.color.white)
            .setMessageColorRes(R.color.white)
            .setTitle("Loading...")
            .setTitleColorRes(R.color.white)
            .setMessageContent("Please wait!")
            .setCancelable(false)
            .setMessageContentGravity(Gravity.END)
            .build()

        containerBuy.setOnClickListener(this)

    }

    private fun getDetailUmroh(id_paket: String) {
        showLoading()
        val apiService = ApiClient.getClient().create(ApiRequest::class.java)
        apiService
            .umrohDetail("detail", id_paket)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { hideLoading() }
            .subscribe(
                { resp ->
                    hideLoading()
                    when (resp.code) {
                        200 -> {
                            txtPoint.text = resp.data.point + " POIN"
                            txtHarga.text = resp.data.harga
                            txtNamaPaket.text = resp.data.namaPaket

                            Glide.with(this).load(resp.data.icon).into(imgIcon)
                        }
                    }
                },
                { error ->
                    hideLoading()
                    Log.e("Error", error.message)
                    try {
                        var code: Int = (error as HttpException).code()
                        if (code.equals(401)) {
                            Utils.showToast(this, "Username atau Password Anda salah!")
                        } else if (code.equals(404)) {
                            Utils.showToast(this, "Not Found")
                        } else {
                            Utils.showToast(this, "Uknown Error")
                        }
                    } catch (e: Exception) {
                        Utils.showToast(this, "Tidak dapat terhubung dengan server")
                    }
                }
            )
    }

    private fun saveImage(file: File, token: String, id_transaksi: String) {
        showLoading()
        val apiService = ApiClient.getClient().create(ApiRequest::class.java)
        try {
            val filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(
                MediaType.parse("image/*"), file))

            val mToken = RequestBody.create(MediaType.parse("text/plain"), token)
            val mIdTransaksi = RequestBody.create(MediaType.parse("text/plain"), id_transaksi)

            apiService
                .transaksiKonfirmasi(filePart, mToken, mIdTransaksi)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { hideLoading() }
                .subscribe(
                    { resp ->
                        hideLoading()
                        when (resp.code) {
                            200 -> {
                                hideLoading()
                                Utils.showToast(this, resp.message + "")
                            }
                        }
                    },
                    { error ->
                        hideLoading()
                        Log.e("Error", error.message)
                        try {
                            var code: Int = (error as HttpException).code()
                            if (code.equals(401)) {
                                Utils.showToast(this, "Akun anda digunakan pada perangkat lain, Login kembali!")
                            } else if (code.equals(404)) {
                                Utils.showToast(this, "Not Found")
                            } else {
                                Utils.showToast(this, "Uknown Error")
                            }
                        } catch (e: Exception) {
                            Utils.showToast(this, "Tidak dapat terhubung dengan server")
                        }
                    }
                )
        } catch (e: java.lang.Exception) {
            //Utils.showToast(context!!, e.message!!)
            //Log.e("Error", e.message)
            hideLoading()
        }
    }

    fun showLoading() {
        dialog.show()
    }

    fun hideLoading() {
        if (dialog.isShowing)
            dialog.dismiss()
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.containerBuy -> {
                val intent = Intent(this, PaketUmrohPaymentActivity::class.java)
                intent.putExtra("id_paket", id_paket)
                startActivity(intent)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
