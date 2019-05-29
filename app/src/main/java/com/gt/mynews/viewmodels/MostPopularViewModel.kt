package com.gt.mynews.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gt.mynews.usecases.NytUseCase
import com.gt.mynews.viewmodels.models.Article
import kotlinx.coroutines.*

class MostPopularViewModel (private val useCase: NytUseCase) : GenericViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private var currentJob: Job? = null

    override fun reloadArticles(keyword: String?) {

        cancelJobIfActive()

        currentJob = viewModelScope.launch(Dispatchers.IO) {
            fetchArticles(null)
        }
    }

    override suspend fun fetchArticles(keyword: String?) {

        val articlesMP = useCase.getMostPopular()?.results
                ?.map {
                    Article(it.section, it.section, it.title, it.publishedDate)
                }
        withContext(Dispatchers.Main) {
            _articles.value = articlesMP
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