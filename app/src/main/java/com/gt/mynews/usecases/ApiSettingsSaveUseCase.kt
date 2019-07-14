package com.gt.mynews.usecases

import com.gt.mynews.data.repositories.NotificationSettingsArticlesSaved

class ApiSettingsSaveUseCase(private val repo: NotificationSettingsArticlesSaved): SettingsSaveUseCaseInterface {

    override fun saveKeyword(stringToSave: String?){
        repo.saveApiRequestKeyword(stringToSave)
    }

    override fun saveKeywordFilter(stringToSave: String?){
        repo.saveApiRequestKeywordFilter(stringToSave)
    }

    override fun getKeyword(): String?{
        return repo.getKeywordToSearch()
    }

    override fun getKeywordFilter(): String?{
        return repo.getKeywordFilterToSearch()
    }
}