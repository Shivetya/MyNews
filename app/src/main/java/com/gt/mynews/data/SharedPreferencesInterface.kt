package com.gt.mynews.data

interface SharedPreferencesInterface {

    fun saveString(key: String, stringToSave: String?)
    fun getString(key: String): String?
}