package com.gt.mynews.usecases

import com.gt.mynews.data.repositories.NotificationSettingsArticlesSaved
import com.gt.mynews.data.repositories.SharedPreferencesInterface

class NotificationUseCase(private val repo: SharedPreferencesInterface) {

    fun saveKeyword(stringToSave: String?){
        repo.saveString(NotificationSettingsArticlesSaved.KEY_KEYWORD, stringToSave)
    }

    fun saveKeywordFilter(stringToSave: String?){
        repo.saveString(NotificationSettingsArticlesSaved.KEY_KEYWORD_FILTER, stringToSave)
    }

    fun saveTitle(stringToSave: String?){
        repo.saveString(NotificationSettingsArticlesSaved.KEY_NEW_ARTICLE, stringToSave)
    }

    fun getKeyword(): String?{
        return repo.getString(NotificationSettingsArticlesSaved.KEY_KEYWORD)
    }

    fun getKeywordFilter(): String?{
        return repo.getString(NotificationSettingsArticlesSaved.KEY_KEYWORD_FILTER)
    }

    fun getNewArticleTitle(): String?{
        return repo.getString(NotificationSettingsArticlesSaved.KEY_NEW_ARTICLE)
    }

    fun getOldArticleTitle(): String?{
        return repo.getString(NotificationSettingsArticlesSaved.KEY_OLD_ARTICLE)
    }
}