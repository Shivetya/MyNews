package com.gt.mynews.usecases

interface SettingsSaveUseCaseInterface {

    fun saveKeyword(stringToSave: String?)

    fun saveKeywordFilter(stringToSave: String?)

    fun getKeyword(): String?

    fun getKeywordFilter(): String?
}