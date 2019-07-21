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

    abstract fun reloadArticles(keyword: String? = null,
                                keywordFilter: ArrayList<String>? = null,
                                beginDate: String? = null,
                                endDate: String? = null)
    abstract suspend fun fetchArticles(keyword: String? = null,
                                       keywordFilter: ArrayList<String>? = null,
                                       beginDate: String? = null,
                                       endDate: String? = null)

    fun cancelJobIfActive(){

        currentJob?.let {
            if(it.isActive){
                it.cancel()
            }
        }
    }
}