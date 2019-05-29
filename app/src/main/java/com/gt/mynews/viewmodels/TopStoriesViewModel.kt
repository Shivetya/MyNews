package com.gt.mynews.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gt.mynews.usecases.NytUseCase
import com.gt.mynews.viewmodels.models.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TopStoriesViewModel (private val useCase : NytUseCase) : GenericViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private var currentJob: Job? = null

    override fun reloadArticles(keyword : String?){

        cancelJobIfActive()

        currentJob = viewModelScope.launch(Dispatchers.IO) {
            fetchArticles(keyword)
        }
    }

    override suspend fun fetchArticles(keyword : String?){

        val articlesTS = when(keyword){
            "home" -> useCase.getTopStories()?.results
                    ?.map {
                        Article(it.section, it.section, it.title, it.publishedDate)
                    }
            "science" -> useCase.getScience()?.results
                    ?.map {
                        Article(it.section, it.section, it.title, it.publishedDate)
                    }
            "technology" -> useCase.getTechnology()?.results
                    ?.map {
                        Article(it.section, it.section, it.title, it.publishedDate)
                    }
            else ->  null
        }

        withContext(Dispatchers.Main){
            _articles.value = articlesTS
        }
    }

    override fun cancelJobIfActive(){

        currentJob?.let {
            if(it.isActive){
                it.cancel()
            }
        }
    }
}