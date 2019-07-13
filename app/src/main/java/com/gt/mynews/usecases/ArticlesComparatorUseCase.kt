package com.gt.mynews.usecases

import com.gt.mynews.data.repositories.NotificationSettingsArticlesSaved
import com.gt.mynews.viewmodels.models.Article
import kotlinx.coroutines.*

class ArticlesComparatorUseCase(private val repo: NotificationSettingsArticlesSaved,
                                private val nytUseCase: NytUseCase,
                                private val apiSettingsSaveUseCase: ApiSettingsSaveUseCase) {

    var currentJob: Job? = null

    private var articleSearch: Article? = null

    fun launchSearchLastArticlesAndCompareToOldOnes(){

        cancelJobIfActive()

        val keyword = apiSettingsSaveUseCase.getKeyword()
        val keywordFilter = apiSettingsSaveUseCase.getKeywordFilter()

        currentJob = CoroutineScope(Dispatchers.IO).launch{
            fetchArticles(keyword, keywordFilter)
        }
    }

    private suspend fun fetchArticles(keyword: String?, keywordFilter: String?){

        articleSearch = nytUseCase.getSearch(keyword, keywordFilter, null, null)
                ?.response
                ?.docs?.get(1)?.let {
            Article(it.sectionName, null,null,null, null)
        }
        withContext(Dispatchers.Main){
            saveTitle((articleSearch?.articleTitle))
        }
}

    fun saveTitle(stringToSave: String?){
        repo.putTitleNewArticleInSharedPreferences(stringToSave)
    }

    fun getNewArticleTitle(): String?{
        return repo.getNewArticle()
    }

    fun getOldArticleTitle(): String?{
        return repo.getOldArticle()
    }

    fun isSameTitle():Boolean{
        return getNewArticleTitle() == getOldArticleTitle()
    }


    fun cancelJobIfActive(){

        currentJob?.let {
            if(it.isActive){
                it.cancel()
            }
        }
    }
}