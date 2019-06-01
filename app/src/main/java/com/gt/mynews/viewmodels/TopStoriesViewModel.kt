package com.gt.mynews.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gt.mynews.data.Multimedium
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
                        val image = if (it.multimedia!!.isNotEmpty()) {
                            it.multimedia?.get(0)?.url
                        } else {
                            null
                        }
                        Article(it.section,
                                image,
                                it.title,
                                it.publishedDate?.substring(0,it.publishedDate!!.indexOf('T')),
                                it.url)
                    }
            "science" -> useCase.getScience()?.results
                    ?.map {
                        val image = if (it.multimedia!!.isNotEmpty()) {
                            it.multimedia?.get(0)?.url
                        } else {
                            null
                        }
                        Article(it.section,
                                image,
                                it.title,
                                it.publishedDate?.substring(0,it.publishedDate!!.indexOf('T')),
                                it.url)
                    }
            "technology" -> useCase.getTechnology()?.results
                    ?.map {
                        val image = if (it.multimedia!!.isNotEmpty()) {
                            it.multimedia?.get(0)?.url
                        } else {
                            null
                        }
                        Article(it.section,
                                image,
                                it.title,
                                it.publishedDate?.substring(0,it.publishedDate!!.indexOf('T')),
                                it.url)
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