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

    override fun saveString(key: String, stringToSave: String?) {
        when(key){
            KEY_NEW_ARTICLE -> putTitleNewArticleInSharedPreferences(stringToSave)
            KEY_KEYWORD -> saveApiRequestKeyword(stringToSave)
            KEY_KEYWORD_FILTER -> saveApiRequestKeywordFilter(stringToSave)
            else -> throw IllegalArgumentException("Wrong key !")
        }
    }

    override fun getString(key: String): String? {
        return when(key){
            KEY_NEW_ARTICLE -> getNewArticle()
            KEY_OLD_ARTICLE -> getOldArticle()
            KEY_KEYWORD -> getKeywordToSearch()
            KEY_KEYWORD_FILTER -> getKeywordFilterToSearch()
            else -> throw IllegalArgumentException("Wrong key !")
        }
    }

    fun putTitleNewArticleInSharedPreferences(title: String?){

        //not deleting old "new" article, but transfer it in KEY_OLD_ARTICLE
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

    fun saveApiRequestKeyword(keyword: String?){

        mPreferences.edit().putString(KEY_KEYWORD, keyword).apply()
    }

    fun saveApiRequestKeywordFilter(keywordFilter: String?){

        mPreferences.edit().putString(KEY_KEYWORD_FILTER, keywordFilter).apply()
    }

    fun getKeywordToSearch(): String?{

        return mPreferences.getString(KEY_KEYWORD, null)
    }

    fun getKeywordFilterToSearch(): String?{

        return mPreferences.getString(KEY_KEYWORD_FILTER, null)
    }
}