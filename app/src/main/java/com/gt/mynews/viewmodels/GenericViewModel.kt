package com.gt.mynews.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gt.mynews.viewmodels.models.Article
import kotlinx.coroutines.Job



abstract class GenericViewModel : ViewModel() {

    protected val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    var currentJob: Job? = null

    abstract fun reloadArticles(keyword: String?)
    abstract suspend fun fetchArticles(keyword: String?)

    fun cancelJobIfActive(){

        currentJob?.let {
            if(it.isActive){
                it.cancel()
            }
        }
    }
}