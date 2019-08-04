package com.gt.mynews.usecases

import androidx.annotation.VisibleForTesting
import com.gt.mynews.data.repositories.SharedPreferencesInterface
import com.gt.mynews.viewmodels.models.Article


class ArticlesComparatorUseCase(private val repo: SharedPreferencesInterface,
                                private val nytUseCase: NytUseCase,
                                private val apiSettingsSaveUseCase: SettingsSaveUseCaseInterface) : ArticlesComparatorUseCaseInterface {

    //Launch seaurch for new articles and compare wit old ones to konw if there is nes articles
    override fun howManyNewArticles():Int{

        val keyword = transformKeywordQueryReady(apiSettingsSaveUseCase.getKeyword())
        val keywordFilter = transformKeywordFilterToQueryReady(apiSettingsSaveUseCase.getKeywordFilter())

        val articleSearch = nytUseCase.getSearch(keyword,keywordFilter, null, null)
                ?.response
                ?.docs?.map {
            Article(it.sectionName, null,null,null, null)
        }
        saveTitle((articleSearch?.firstOrNull()?.articleTitle))

        var counterNewArticles = 0

        if (articleSearch != null && articleSearch.isNotEmpty()) {

            for (article in articleSearch){

                if (article.categoryArticle.equals(getOldArticleTitle())){
                    break
                } else {
                    counterNewArticles++
                }
            }
        }
        return counterNewArticles
    }

    @VisibleForTesting
    override fun saveTitle(stringToSave: String?){
        repo.putTitleNewArticleInSharedPreferences(stringToSave)
    }

    override fun getNewArticleTitle(): String?{
        return repo.getNewArticle()
    }

    override fun getOldArticleTitle(): String?{
        return repo.getOldArticle()
    }

    override fun isSameTitle():Boolean{
        return getNewArticleTitle() == getOldArticleTitle()
    }

    @VisibleForTesting
    internal fun transformKeywordFilterToQueryReady(keywordFilter: Collection<String>) : String?{

        return if(keywordFilter.isNotEmpty() ){
            "news_desk:(\"${keywordFilter.joinToString("\" \"")}\")"
        }
        else {
            null
        }
    }

    @VisibleForTesting
    internal fun transformKeywordQueryReady(keyword: String?): String?{

        return if(keyword != null){
            "(\"$keyword\")"
        } else {
            null
        }
    }

}