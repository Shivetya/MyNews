package com.gt.mynews.data.repositories

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class NotificationSettingsArticlesSaved(context: Context): SharedPreferencesInterface {

    private val mPreferences: SharedPreferences = context.getSharedPreferences(KEY_FILE_PREFERENCES, MODE_PRIVATE)

    companion object{
        private const val KEY_FILE_PREFERENCES = "article.article"
        private const val KEY_NEW_ARTICLE = "KEY_NEW_ARTICLE"
        private const val KEY_OLD_ARTICLE = "KEY_OLD_ARTICLE"
        private const val KEY_KEYWORD = "KEY_KEYWORD"
        private const val KEY_KEYWORD_FILTER = "KEY_KEYWORD_FILTER"
    }


    override fun putTitleNewArticleInSharedPreferences(title: String?){

        //not deleting old "new" article, but transfer it in KEY_OLD_ARTICLE
        val oldArticle = mPreferences.getString(KEY_NEW_ARTICLE, null)

        mPreferences.edit().putString(KEY_NEW_ARTICLE, title).apply()
        mPreferences.edit().putString(KEY_OLD_ARTICLE, oldArticle).apply()
    }

    override fun getNewArticle() : String?{
        return mPreferences.getString(KEY_NEW_ARTICLE, null)
    }

    override fun getOldArticle() : String?{
        return mPreferences.getString(KEY_OLD_ARTICLE, null)
    }

    override fun saveApiRequestKeyword(keyword: String?){

        mPreferences.edit().putString(KEY_KEYWORD, keyword).apply()
    }

    override fun saveApiRequestKeywordFilter(keywordFilter: String?){

        mPreferences.edit().putString(KEY_KEYWORD_FILTER, keywordFilter).apply()
    }

    override fun getKeywordToSearch(): String?{

        return mPreferences.getString(KEY_KEYWORD, null)
    }

    override fun getKeywordFilterToSearch(): String?{

        return mPreferences.getString(KEY_KEYWORD_FILTER, null)
    }
}