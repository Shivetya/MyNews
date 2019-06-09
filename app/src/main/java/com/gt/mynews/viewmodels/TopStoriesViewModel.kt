package com.gt.mynews.viewmodels

import androidx.lifecycle.viewModelScope
import com.gt.mynews.usecases.NytUseCase
import com.gt.mynews.utils.Utils
import com.gt.mynews.viewmodels.models.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.joda.time.DateTime

class TopStoriesViewModel (private val useCase : NytUseCase) : GenericViewModel() {

    override fun reloadArticles(keyword: String?,
                                keywordFilter: String?,
                                beginDate: String?,
                                endDate: String?){

        cancelJobIfActive()

        currentJob = viewModelScope.launch(Dispatchers.IO) {
            fetchArticles(keyword, null, null, null)
        }
    }

    override suspend fun fetchArticles(keyword: String?,
                                       keywordFilter: String?,
                                       beginDate: String?,
                                       endDate: String?){

        val articlesTS = when(keyword){
            "home" -> useCase.getTopStories()?.results
                    ?.map {
                        val image = it.multimedia?.firstOrNull()?.url
                        val date = if (it.publishedDate != null){
                            DateTime.parse(it.publishedDate!!.substringBefore('T'), Utils.formatter)
                        } else {
                            null
                        }

                        Article(it.section,
                                image,
                                it.title,
                                date.toString().substringBefore('T'),
                                it.url)
                    }
            "science" -> useCase.getScience()?.results
                    ?.map {
                        val image = it.multimedia?.firstOrNull()?.url
                        val date = if (it.publishedDate != null){
                            DateTime.parse(it.publishedDate!!.substringBefore('T'), Utils.formatter)
                        } else {
                            null
                        }
                        Article(it.section,
                                image,
                                it.title,
                                date.toString().substringBefore('T'),
                                it.url)
                    }
            "technology" -> useCase.getTechnology()?.results
                    ?.map {
                        val image = it.multimedia?.firstOrNull()?.url
                        val date = if (it.publishedDate != null){
                            DateTime.parse(it.publishedDate!!.substringBefore('T'), Utils.formatter)
                        } else {
                            null
                        }
                        Article(it.section,
                                image,
                                it.title,
                                date.toString().substringBefore('T'),
                                it.url)
                    }
            else ->  null
        }

        withContext(Dispatchers.Main){
            _articles.value = articlesTS
        }
    }

}