package com.gt.mynews.data.repositories

interface SharedPreferencesInterface {

    fun putTitleNewArticleInSharedPreferences(title: String?)

    fun getNewArticle() : String?

    fun getOldArticle() : String?

    fun saveApiRequestKeyword(keyword: String?)

    fun saveApiRequestKeywordFilter(keywordFilters: Collection<String>)

    fun getKeywordToSearch(): String?

    fun getKeywordFilterToSearch(): Collection<String>
}