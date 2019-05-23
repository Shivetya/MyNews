package com.gt.mynews.viewmodels

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gt.mynews.usecases.NytUseCase
import com.gt.mynews.viewmodels.models.Article
import kotlinx.coroutines.*


class GenericViewModel(private val useCase: NytUseCase) : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private var currentJob: Job? = null

    init {
        reloadArticlesMP()
    }

    fun reloadArticlesMP() {

        cancelJobIfActive()

        currentJob = viewModelScope.launch(Dispatchers.IO) {
            fetchArticlesMP()
        }
    }

    suspend fun fetchArticlesMP() {

        val articlesMP = useCase.getMostPopular()?.results
                ?.map {
                    Article("${it.section} > ${it.desFacet?.get(0)}", it.section, it.title, it.publishedDate)
                }
        withContext(Dispatchers.Main) {
            _articles.value = articlesMP
        }
    }

    fun reloadArticlesTS(keywordTS : String?){

        cancelJobIfActive()

        currentJob = viewModelScope.launch(Dispatchers.IO) {
            fetchArticlesTS(keywordTS)
        }
    }

    suspend fun fetchArticlesTS(keywordTS : String?){

        val articlesTS : List<Article>?

        when(keywordTS){
            "home" -> articlesTS = useCase.getTopStories()?.results
                ?.map {
                    Article("${it.section} > ${it.desFacet?.get(0)}", it.section, it.title, it.publishedDate)
                }
            "science" -> articlesTS = useCase.getScience()?.results
                    ?.map {
                        Article("${it.section} > ${it.desFacet?.get(0)}", it.section, it.title, it.publishedDate)
                    }
            "technology" -> articlesTS = useCase.getTechnology()?.results
                    ?.map {
                        Article("${it.section} > ${it.desFacet?.get(0)}", it.section, it.title, it.publishedDate)
                    }
            else -> articlesTS = null
        }

        withContext(Dispatchers.Main){
            _articles.value = articlesTS
        }
    }


    private fun cancelJobIfActive(){

        currentJob?.let {
            if(it.isActive){
                it.cancel()
            }
        }
    }
}