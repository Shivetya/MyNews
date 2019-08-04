package com.gt.mynews.usecases


interface ArticlesComparatorUseCaseInterface {

    fun howManyNewArticles():Int

    fun saveTitle(stringToSave: String?)

    fun getNewArticleTitle(): String?

    fun getOldArticleTitle(): String?

    fun isSameTitle():Boolean
}