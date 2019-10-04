package com.vira.echsan.helper

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val PREF_NAME = "Echsan"
    private var pref: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = pref.edit()

    val TOKEN = "token"
    val ID_TRANSAKSI = "id_transaksi"

    fun setLogin() {
        editor.putBoolean("login", true)
        editor.commit()
    }

    fun isLogin(): Boolean {
        return pref.getBoolean("login", false)
    }

    fun storeString(key: String, data: String?) {
        if (data != null) {
            editor.putString(key, data)
        } else {
            editor.putString(key, "")
        }
        editor.commit()
    }

    fun getString(key: String): String {
        return pref.getString(key, "")
    }

    fun storeInt(key: String, data: Int?) {
        if (data != null) {
            editor.putInt(key, data)
        } else {
            editor.putInt(key, 0)
        }
        editor.commit()
    }

    fun getInt(key: String): Int {
        return pref.getInt(key, 0)
    }

    fun destroySession() {
        editor.clear()
        editor = pref.edit()
        editor.clear()
        editor.commit()
    }
}
