package com.gt.mynews.viewmodels

import androidx.lifecycle.ViewModel


abstract class GenericViewModel : ViewModel() {

    abstract fun reloadArticles(keyword: String?)
    abstract suspend fun fetchArticles(keyword: String?)
    abstract fun cancelJobIfActive()
}