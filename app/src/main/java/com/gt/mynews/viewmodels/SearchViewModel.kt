package com.gt.mynews.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gt.mynews.usecases.NytUseCase
import com.gt.mynews.utils.Utils
import com.gt.mynews.viewmodels.models.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.joda.time.DateTime

class SearchViewModel(private val useCase : NytUseCase) : GenericViewModel() {

    //these 4 mutablesLiveData are only here to test the defferents field in the query.
    val mutableKeyword = MutableLiveData<String>()
    val mutableFilterKeyword = MutableLiveData<String>()
    val mutableBeginDate = MutableLiveData<String>()
    val mutableEndDate = MutableLiveData<String>()

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

        mutableKeyword.value = transformKeywordToQueryReady(keyword)
        mutableFilterKeyword.value = transformKeywordFilterToQueryReady(keywordFilter)
        mutableBeginDate.value = transformDateToQueryReady(beginDate)
        mutableEndDate.value = transformDateToQueryReady(endDate)

        val articleSearch = useCase.getSearch(mutableKeyword.value
                , mutableFilterKeyword.value
                , mutableBeginDate.value
                , mutableEndDate.value)
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

    private fun transformKeywordToQueryReady(keyword: String?) : String?{
        return if (keyword != null){
            "(\"$keyword\")"
        }
        else {
            null
        }
    }

    private fun transformKeywordFilterToQueryReady(keywordFilter: String?) : String?{
        return if(keywordFilter != null){
            "news-desk:($keywordFilter)"
        }
        else {
            null
        }
    }

    private fun transformDateToQueryReady(date : String?) : String?{
        return if(date != null){
            DateTime.parse(date, Utils.formatterToQuery).toString("yyyyMMdd")
        }
        else{
            null
        }
    }
}