package com.gt.mynews.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class NotificationSettingsArticlesSaved(context: Context) {

    private val mPreferences: SharedPreferences

    init{
        mPreferences = context.getSharedPreferences(KEY_FILE_PREFERENCES, MODE_PRIVATE)
    }

    companion object{
        private const val KEY_FILE_PREFERENCES = "article.article"
        private const val KEY_NEW_ARTICLE = "KEY_NEW_ARTICLE"
        private const val KEY_OLD_ARTICLE = "KEY_OLD_ARTICLE"
        private const val KEY_KEYWORD = "KEY_KEYWORD"
        private const val KEY_KEYWORD_FILTER = "KEY_KEYWORD_FILTER"
    }



    fun putTitleNewArticleInSharedPreferences(title: String){

        val oldArticle = mPreferences.getString(KEY_NEW_ARTICLE, null)

        mPreferences.edit().putString(KEY_NEW_ARTICLE, title).apply()
        mPreferences.edit().putString(KEY_OLD_ARTICLE, oldArticle).apply()
    }

    fun getNewArticle() : String?{
        return mPreferences.getString(KEY_NEW_ARTICLE, null)
    }

    fun getOldArticle() : String?{
        return mPreferences.getString(KEY_OLD_ARTICLE, null)
    }

    fun saveApiRequestSettings(keyword: String?, keywordFilter: String?){

        mPreferences.edit().putString(KEY_KEYWORD, keyword).apply()
        mPreferences.edit().putString(KEY_KEYWORD_FILTER, keywordFilter).apply()
    }

    fun getKeywordToSearch(): String?{
        return mPreferences.getString(KEY_KEYWORD, null)
    }

    fun getKeywordFilterToSearch(): String?{
        return mPreferences.getString(KEY_KEYWORD_FILTER, null)
    }
}