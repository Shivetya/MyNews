package com.gt.mynews.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gt.mynews.data.ArticleApiResponse
import com.gt.mynews.usecases.NytUseCase
import com.gt.mynews.viewmodels.models.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class GenericViewModel (private val useCase : NytUseCase) : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles : LiveData<List<Article>> = _articles

    private var currentJob : Job? = null

    init {

        reloadArticles()
    }

    fun reloadArticles(){

        viewModelScope.launch(Dispatchers.IO){

            val articlesMP = useCase.getMostPopular()?.results
                    ?.map {
                        Article(it.geoFacet?.get(0), it.title, it.title, it.publishedDate)
                    }
            withContext(Dispatchers.Main){
                _articles.value = articlesMP
            }
        }

    }
}