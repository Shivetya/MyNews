package com.gt.mynews.viewmodels

import androidx.annotation.VisibleForTesting
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
                                keywordFilter: ArrayList<String>?,
                                beginDate: String?,
                                endDate: String?) {
        cancelJobIfActive()

        currentJob = viewModelScope.launch(Dispatchers.IO){
            fetchArticles(keyword, keywordFilter, beginDate, endDate)
        }
    }

    override suspend fun fetchArticles(keyword: String?,
                                       keywordFilter: ArrayList<String>?,
                                       beginDate: String?,
                                       endDate: String?) {

        val keywordQueryReady = transformKeywordToQueryReady(keyword)
        val keywordFilterQueryReady = transformKeywordFilterToQueryReady(keywordFilter)
        val beginDateQueryReady = transformDateToQueryReady(beginDate)
        val endDateQueryReady = transformDateToQueryReady(endDate)

        val articleSearch = useCase.getSearch(keywordQueryReady
                , keywordFilterQueryReady
                , beginDateQueryReady
                , endDateQueryReady)
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
                    date?.toString("yyyy-MM-dd"),
                    it.webUrl)
        }

        withContext(Dispatchers.Main){
            _articles.value = articleSearch
        }
    }

    @VisibleForTesting
    internal fun transformKeywordToQueryReady(keyword: String?) : String?{
        return if (keyword != null){
            "(\"$keyword\")"
        }
        else {
            null
        }
    }

    @VisibleForTesting
    internal fun transformKeywordFilterToQueryReady(keywordFilter: ArrayList<String>?) : String?{
        return if(keywordFilter != null && keywordFilter.isNotEmpty() ){
            "news_desk:(${keywordFilter.joinToString(" ")})"
        }
        else {
            null
        }
    }

    @VisibleForTesting
    internal fun transformDateToQueryReady(date : String?) : String?{
        return if(date != null){
            DateTime.parse(date, Utils.formatterToQuery).toString("yyyyMMdd")
        }
        else{
            null
        }
    }
}