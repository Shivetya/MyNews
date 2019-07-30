package com.gt.mynews.usecases

interface SettingsSaveUseCaseInterface {

    fun saveKeyword(stringToSave: String?)

    fun saveKeywordFilter(keywordFiltersToSave: Collection<String>)

    fun getKeyword(): String?

    fun getKeywordFilter(): Collection<String>
}