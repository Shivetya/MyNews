package com.gt.mynews.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gt.mynews.viewmodels.models.Article


abstract class GenericViewModel : ViewModel() {

    protected val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles
    abstract fun reloadArticles(keyword: String?)
    abstract suspend fun fetchArticles(keyword: String?)
    abstract fun cancelJobIfActive()
}