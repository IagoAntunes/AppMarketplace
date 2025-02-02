package com.iagoaf.appmarketplace.services.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import com.iagoaf.appmarketplace.services.sharedPreferences.domain.ISharedPreferences

class PreferencesManager(context: Context) : ISharedPreferences {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

    override fun saveData(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    override fun getData(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }
}