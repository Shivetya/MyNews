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
        reloadArticlesMPTS()
    }

    fun reloadArticlesMPTS() {

        currentJob = viewModelScope.launch(Dispatchers.IO) {
            fetchArticlesMPTS()
        }
    }

    @VisibleForTesting
    suspend fun fetchArticlesMPTS() {

        val articlesMP = useCase.getMostPopular()?.results
                ?.map {
                    Article("${it.geoFacet?.get(0)} > ${it.section}", it.section, it.title, it.publishedDate)
                }
        withContext(Dispatchers.Main) {
            _articles.value = articlesMP
        }
    }
}