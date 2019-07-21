package com.gt.mynews.usecases


interface ArticlesComparatorUseCaseInterface {

    fun isThereNewArticle():Boolean

    fun saveTitle(stringToSave: String?)

    fun getNewArticleTitle(): String?

    fun getOldArticleTitle(): String?

    fun isSameTitle():Boolean
}