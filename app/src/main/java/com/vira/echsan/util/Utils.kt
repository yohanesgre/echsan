package com.vira.echsan.util

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.text.SimpleDateFormat
import java.util.*


object Utils {

    val currentYear: String
        get() {
            val year = Calendar.getInstance().get(Calendar.YEAR)
            return year.toString()
        }

    fun isEmpty(ed: EditText): Boolean {
        val temp = ed.text.toString().replace("\\s".toRegex(), "")
        return TextUtils.isEmpty(temp)
    }

    fun getString(ed: EditText): String {
        return ed.text.toString()
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun convertDateTime(DateTime: String, dateFormat1: SimpleDateFormat, dateFormat2: SimpleDateFormat): String {
        var DateTime = DateTime
        try {
            val value = dateFormat1.parse(DateTime)
            DateTime = dateFormat2.format(value)

        } catch (e: Exception) {

            DateTime = DateTime

        }

        return DateTime
    }

    fun fetchErrorMessage(e: Throwable): String {
        return when (e) {
            is HttpException -> {
                val responseBody = e.response()!!.errorBody()
                getErrorMessage(responseBody!!)
            }
            is SocketTimeoutException -> "Connection timed out."
            is IOException -> "Connection lost, please check your connection."
            else -> e.message ?: "Error"
        }
    }

    fun getErrorMessage(responseBody: ResponseBody): String {
        return try {
            val jsonObject = JSONObject(responseBody.string())
            jsonObject.getString("message")
        } catch (e: Exception) {
            e.message ?: ""
        }
    }



    fun disableEditText(editText: EditText) {
        editText.isFocusable = false
        editText.isEnabled = false
        editText.isCursorVisible = false
        editText.keyListener = null
        editText.setBackgroundColor(Color.TRANSPARENT)
    }

}