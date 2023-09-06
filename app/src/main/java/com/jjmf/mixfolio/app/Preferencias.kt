package com.jjmf.mixfolio.app

import android.content.Context

class Preferencias(val context: Context) {

    private val SHARED_NAME = "MYDTB"
    private val KEY_USUARIO = "KEY_USUARIO"

    private val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveUsuario(valor: String) = storage.edit().putString(KEY_USUARIO, valor).apply()
    fun getUsuario() = storage.getString(KEY_USUARIO, null)

}