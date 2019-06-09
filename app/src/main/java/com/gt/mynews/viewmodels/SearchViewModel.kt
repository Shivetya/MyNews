package com.gt.mynews.viewmodels

import androidx.lifecycle.viewModelScope
import com.gt.mynews.usecases.NytUseCase
import com.gt.mynews.utils.Utils
import com.gt.mynews.viewmodels.models.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.joda.time.DateTime

class SearchViewModel(private val useCase : NytUseCase) : GenericViewModel() {


    override fun reloadArticles(keyword: String?,
                                keywordFilter: String?,
                                beginDate: String?,
                                endDate: String?) {
        cancelJobIfActive()

        currentJob = viewModelScope.launch(Dispatchers.IO){
            fetchArticles(keyword, keywordFilter, beginDate, endDate)
        }
    }

    override suspend fun fetchArticles(keyword: String?,
                                       keywordFilter: String?,
                                       beginDate: String?,
                                       endDate: String?) {

        val articleSearch = useCase.getSearch(keyword, keywordFilter, beginDate, endDate)
                ?.response
                ?.docs?.map {
            val image = it.multimedia?.firstOrNull()?.url
            val date = if (it.pubDate != null){
                DateTime.parse(it.pubDate!!.substringBefore('T'), Utils.formatter)
            } else {
                null
            }
            Article(it.sectionName,
                    image,
                    it.snippet,
                    date.toString().substringBefore('T'),
                    it.webUrl)
        }

        withContext(Dispatchers.Main){
            _articles.value = articleSearch
        }
    }
}