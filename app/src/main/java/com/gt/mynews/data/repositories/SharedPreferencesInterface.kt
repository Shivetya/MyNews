package com.gt.mynews.data.repositories

interface SharedPreferencesInterface {

    fun saveString(key: String, stringToSave: String?)
    fun getString(key: String): String?
}