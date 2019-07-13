package com.gt.mynews.usecases

import com.gt.mynews.data.repositories.NotificationSettingsArticlesSaved

class ApiSettingsSaveUseCase(private val repo: NotificationSettingsArticlesSaved) {

    fun saveKeyword(stringToSave: String?){
        repo.saveApiRequestKeyword(stringToSave)
    }

    fun saveKeywordFilter(stringToSave: String?){
        repo.saveApiRequestKeywordFilter(stringToSave)
    }

    fun getKeyword(): String?{
        return repo.getKeywordToSearch()
    }

    fun getKeywordFilter(): String?{
        return repo.getKeywordFilterToSearch()
    }
}