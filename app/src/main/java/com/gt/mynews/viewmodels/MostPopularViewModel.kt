package com.gt.mynews.viewmodels

import androidx.lifecycle.viewModelScope
import com.gt.mynews.usecases.NytUseCase
import com.gt.mynews.utils.Utils
import com.gt.mynews.viewmodels.models.Article
import kotlinx.coroutines.*
import org.joda.time.DateTime

class MostPopularViewModel (private val useCase: NytUseCase) : GenericViewModel() {


    override fun reloadArticles(keyword: String?,
                                keywordFilter: ArrayList<String>?,
                                beginDate: String?,
                                endDate: String?) {

        cancelJobIfActive()

        currentJob = viewModelScope.launch(Dispatchers.IO) {
            fetchArticles(null, null, null, null)
        }
    }

    override suspend fun fetchArticles(keyword: String?,
                                       keywordFilter: ArrayList<String>?,
                                       beginDate: String?,
                                       endDate: String?) {

        val articlesMP = useCase.getMostPopular()?.results
                ?.map {
                    val date = if (it.publishedDate != null){
                        DateTime.parse(it.publishedDate!!.substringBefore('T'), Utils.formatter)
                    } else {
                        null
                    }
                    Article(it.section,
                            it.media?.get(0)?.mediaMetadata?.get(0)?.url,
                            it.title,
                            date?.toString("yyyy-MM-dd"),
                            it.url)
                }
        withContext(Dispatchers.Main) {
            _articles.value = articlesMP
        }
    }
}