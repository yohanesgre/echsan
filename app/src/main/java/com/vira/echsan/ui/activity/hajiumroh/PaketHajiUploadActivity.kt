package com.vira.echsan.ui.activity.hajiumroh

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
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
import kotlinx.android.synthetic.main.activity_upload_bukti_bayar.*
import kotlinx.android.synthetic.main.xml_toolbar.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import retrofit2.HttpException
import java.io.File

class PaketHajiUploadActivity : AppCompatActivity(), View.OnClickListener, PermissionListener {
    private lateinit var dialog: IOSDialog
    private lateinit var sessionManager: SessionManager
    private var id_paket = ""
    private var id_transaksi = ""
    private lateinit var txtUploadImage: TextView
    private lateinit var myFile: File
    private lateinit var manager: PermissionManager

    private var multiplePermission = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_bukti_bayar)
        init()
        id_paket = intent.getStringExtra("id_paket")
        doBeliHaji(id_paket)
        getDetailHaji(id_paket)
    }

    private fun init() {
        title = "E-Haji & Umroh"
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        manager = PermissionManager(this, this)
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

        containerUpload.setOnClickListener(this)

        EasyImage.configuration(this)
            .setImagesFolderName("echsan")
            .saveInRootPicturesDirectory()
            .setCopyExistingPicturesToPublicLocation(true)
    }

    private fun getDetailHaji(id_paket: String) {
        showLoading()
        val apiService = ApiClient.getClient().create(ApiRequest::class.java)
        apiService
            .hajiDetail("detail", id_paket)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { hideLoading() }
            .subscribe(
                { resp ->
                    hideLoading()
                    when (resp.code) {
                        200 -> {
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

    private fun doBeliHaji(id_paket: String) {
        showLoading()
        val apiService = ApiClient.getClient().create(ApiRequest::class.java)
        apiService
            .beliPaket(sessionManager.getString(sessionManager.TOKEN), id_paket)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { hideLoading() }
            .subscribe(
                { resp ->
                    hideLoading()
                    when (resp.code) {
                        200 -> {
                            id_transaksi = resp.data.idTransaksi.toString()
                            sessionManager.storeString(sessionManager.ID_TRANSAKSI, resp.data.idTransaksi.toString())
                            Toast.makeText(this, "" + resp.message, Toast.LENGTH_SHORT).show()
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
                        } else if (code.equals(406)) {
                            Utils.showToast(this, "Anda memiliki pembayaran yang belum diselesaikan.")
                            id_transaksi = sessionManager.getString(sessionManager.ID_TRANSAKSI)
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
            R.id.containerUpload -> {
                manager.check(multiplePermission, "")
                val alertDialogBuilder = AlertDialog.Builder(this)
                val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val dialogView = inflater.inflate(R.layout.segment_dialog_upload, null)
                alertDialogBuilder.setView(dialogView)

                val imgClose = dialogView.findViewById(R.id.imgClose) as ImageView
                val txtKonfirmasi = dialogView.findViewById(R.id.txtKonfirmasi) as TextView
                val etEmail = dialogView.findViewById(R.id.etEmail) as EditText
                txtUploadImage = dialogView.findViewById(R.id.txtUpload) as TextView
                val txtPilih = dialogView.findViewById(R.id.txtPilih) as TextView
                etEmail.visibility = View.GONE

                val alertDialog = alertDialogBuilder.create()
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                alertDialog.setCanceledOnTouchOutside(true)
                alertDialog.show()

                imgClose.setOnClickListener{
                    alertDialog.dismiss()
                }

                txtPilih.setOnClickListener{
                    EasyImage.openChooserWithGallery(this, "Pilih Foto", 0)
                }

                txtKonfirmasi.setOnClickListener{
                    saveImage(myFile, sessionManager.getString(sessionManager.TOKEN), id_transaksi)
                    alertDialog.dismiss()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, object : DefaultCallback() {
            override fun onImagePickerError(e: Exception?, source: EasyImage.ImageSource?, type: Int) {
                //Some error handling
            }

            override fun onImagePicked(imageFile: File, source: EasyImage.ImageSource, type: Int) {
                if (imageFile.length() > 5242880) {
                    Toast.makeText(this@PaketHajiUploadActivity, "Ukuran gambar terlalu besar!", Toast.LENGTH_LONG).show()
                } else {
                    if (imageFile.length() > 5242880) {
                        Toast.makeText(this@PaketHajiUploadActivity, "Ukuran gambar terlalu besar!", Toast.LENGTH_LONG).show()
                    } else {
                        txtUploadImage.text = imageFile.name
                        myFile = imageFile
                        //Glide.with(this@SignUpActivity).load(imageFile).into(img)
                    }
                }
            }

            override fun onCanceled(source: EasyImage.ImageSource?, type: Int) {
                //Cancel handling, you might wanna remove taken photo if it was canceled
                if (source == EasyImage.ImageSource.CAMERA) {
                    val photoFile = EasyImage.lastlyTakenButCanceledPhoto(this@PaketHajiUploadActivity)
                    photoFile?.delete()
                }
            }
        })
    }


    override fun onPermissionGranted(permissions: Array<String>, tag: String) {
        val msg = StringBuilder("Granted (" + permissions.size + ")")

        for (perm in permissions) {
            msg.append("\n").append(perm)
        }
    }

    override fun onPermissionDenied(permissions: Array<String>, tag: String) {
        // Do something here when permission is granted
        val msg = StringBuilder("Granted (" + permissions.size + ")")

        for (perm in permissions) {
            msg.append("\n").append(perm)
        }

        Utils.showToast(this, msg.toString())
    }

    override fun onPermissionDisabled(permissions: Array<String>, tag: String) {
        val msg = StringBuilder("Disabled (" + permissions.size + ")")

        for (perm in permissions) {
            msg.append("\n").append(perm)
        }

        Utils.showToast(this, msg.toString())

        // Show alert dialog when some permissions are disabled
        manager.alert("Some permission is required", "To setting", "Not now")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
