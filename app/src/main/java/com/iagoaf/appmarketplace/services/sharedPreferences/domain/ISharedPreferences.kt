package com.iagoaf.appmarketplace.services.sharedPreferences.domain

interface ISharedPreferences {
    fun saveData(key: String, value: String)
    fun getData(key: String, defaultValue: String): String
}