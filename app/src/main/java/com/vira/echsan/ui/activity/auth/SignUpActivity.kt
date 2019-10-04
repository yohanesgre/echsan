package com.vira.echsan.ui.activity.auth

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.widget.*
import com.bumptech.glide.Glide
import com.vira.echsan.R
import com.vira.echsan.model.DataPaketMember
import com.vira.echsan.network.ApiClient
import com.vira.echsan.network.ApiRequest
import com.vira.echsan.util.Utils
import com.vira.echsan.util.iosdialog.IOSDialog
import com.vira.echsan.util.permission.PermissionListener
import com.vira.echsan.util.permission.PermissionManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_auth_sign_up.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import retrofit2.HttpException
import java.io.File

class SignUpActivity : AppCompatActivity(), View.OnClickListener, PermissionListener {
    private lateinit var dialog: IOSDialog
    private var arrayListPrivilage = ArrayList<String>()
    private var mList = ArrayList<DataPaketMember>()
    private var idPaket = ""
    private var kodeReferral = ""
    private var konfirmasiEmail = ""
    private lateinit var myFile: File
    private lateinit var txtUploadImage: TextView
    private lateinit var manager: PermissionManager

    private var multiplePermission = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_sign_up)
        init()
    }

    private fun init() {
        manager = PermissionManager(this, this)
        dialog = IOSDialog.Builder(this)
            .setSpinnerColorRes(R.color.white)
            .setMessageColorRes(R.color.white)
            .setTitle("Loading...")
            .setTitleColorRes(R.color.white)
            .setMessageContent("Please wait!")
            .setCancelable(false)
            .setMessageContentGravity(Gravity.END)
            .build()

        getPaketMember()

        txtDaftar.setOnClickListener(this)

        EasyImage.configuration(this)
            .setImagesFolderName("echsan")
            .saveInRootPicturesDirectory()
            .setCopyExistingPicturesToPublicLocation(true)
    }

    private fun getPaketMember() {
        showLoading()
        val apiService = ApiClient.getClient().create(ApiRequest::class.java)
        apiService.paketMember()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { hideLoading() }
            .subscribe(
                { resp ->
                    hideLoading()
                    when (resp.code) {
                        200 -> {
                            updateSpinner(resp.data)
                        }
                    }
                },
                { error ->
                    hideLoading()
                    Log.e("Error", error.message)
                    Utils.showToast(this, Utils.fetchErrorMessage(error))
                    try {
                        var code: Int = (error as HttpException).code()
                        if (code.equals(401)) {
                            Utils.showToast(this, "Username atau Password Anda salah!")
                        } else if (code.equals(404)) {
                            Utils.showToast(this, "Uknown Error")
                        } else {
                            Utils.showToast(this, "Uknown Error")
                        }
                    } catch (e: Exception) {
                        Utils.showToast(this, "Tidak dapat terhubung dengan server")
                    }
                }
            )
    }

    private fun doRegistrasi(paket_member: String, nama_lengkap: String, email: String, nik: String,
                             no_hp: String, kode_referral: String) {
        showLoading()
        val apiService = ApiClient.getClient().create(ApiRequest::class.java)
        apiService.registrasi(paket_member, nama_lengkap, email, nik, no_hp, kode_referral)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { hideLoading() }
            .subscribe(
                { resp ->
                    hideLoading()
                    when (resp.code) {
                        200 -> {
                            Toast.makeText(this, resp.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                { error ->
                    hideLoading()
                    Log.e("Error", error.message)
                    Utils.showToast(this, Utils.fetchErrorMessage(error))
                    try {
                        var code: Int = (error as HttpException).code()
                        if (code.equals(401)) {
                            Utils.showToast(this, "Username atau Password Anda salah!")
                        } else if (code.equals(404)) {
                            Utils.showToast(this, "Uknown Error")
                        } else {
                            Utils.showToast(this, "Uknown Error")
                        }
                    } catch (e: Exception) {
                        Utils.showToast(this, "Tidak dapat terhubung dengan server")
                    }
                }
            )
    }

    private fun updateSpinner(resList: List<DataPaketMember>) {
        mList.addAll(resList)
        for(i in 0 until resList.size) {
            arrayListPrivilage.add(resList.get(i).namaPaket)
        }

        val spinnerArrayPrivilage = ArrayAdapter(this, R.layout.segment_privilage, R.id.txtNama, arrayListPrivilage)
        spnPrivilage.adapter = spinnerArrayPrivilage

        spnPrivilage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                for(i in 0 until mList.size) {
                    if(mList.get(i).namaPaket.equals(selectedItem, true)) {
                        txtHargaPaket.text = mList.get(i).hargaPaket.toString()
                        idPaket = mList.get(i).id.toString()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }


    private fun saveImage(file: File, email: String) {
        showLoading()
        val apiService = ApiClient.getClient().create(ApiRequest::class.java)
        try {
            val filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(
                MediaType.parse("image/*"), file))

            val emails = RequestBody.create(MediaType.parse("text/plain"), email)

            apiService
                .konfirmasi( filePart, emails)
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
            R.id.txtDaftar -> {
                doRegistrasi(idPaket, etNamaLengkap.text.toString(), etEmail.text.toString(), etNik.text.toString(),
                    etNoHp.text.toString(), kodeReferral)
            }
            R.id.txtLogin -> {
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
            }
            R.id.txtUpload -> {
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
                    konfirmasiEmail = etEmail.text.toString()
                    saveImage(myFile, konfirmasiEmail)
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
                    Toast.makeText(this@SignUpActivity, "Ukuran gambar terlalu besar!", Toast.LENGTH_LONG).show()
                } else {
                    if (imageFile.length() > 5242880) {
                        Toast.makeText(this@SignUpActivity, "Ukuran gambar terlalu besar!", Toast.LENGTH_LONG).show()
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
                    val photoFile = EasyImage.lastlyTakenButCanceledPhoto(this@SignUpActivity)
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
