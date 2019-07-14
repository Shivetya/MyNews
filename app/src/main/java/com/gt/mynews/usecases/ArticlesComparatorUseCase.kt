package com.gt.mynews.usecases

import androidx.annotation.VisibleForTesting
import com.gt.mynews.data.repositories.SharedPreferencesInterface
import com.gt.mynews.viewmodels.models.Article


class ArticlesComparatorUseCase(private val repo: SharedPreferencesInterface,
                                private val nytUseCase: NytUseCase,
                                private val apiSettingsSaveUseCase: SettingsSaveUseCaseInterface) {

    private var articleSearch: Article? = null

    fun launchSearchLastArticlesAndCompareToOldOnes():Boolean{


        val keyword = apiSettingsSaveUseCase.getKeyword()
        val keywordFilter = apiSettingsSaveUseCase.getKeywordFilter()

        articleSearch = nytUseCase.getSearch(keyword, keywordFilter, null, null)
                ?.response
                ?.docs?.get(0)?.let {
            Article(it.sectionName, null,null,null, null)
        }
        saveTitle((articleSearch?.articleTitle))
        return isSameTitle()
    }

    @VisibleForTesting
    internal fun saveTitle(stringToSave: String?){
        repo.putTitleNewArticleInSharedPreferences(stringToSave)
    }

    private fun getNewArticleTitle(): String?{
        return repo.getNewArticle()
    }

    private fun getOldArticleTitle(): String?{
        return repo.getOldArticle()
    }

    private fun isSameTitle():Boolean{
        return getNewArticleTitle() == getOldArticleTitle()
    }

}