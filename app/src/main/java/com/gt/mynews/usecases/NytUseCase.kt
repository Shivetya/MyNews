package com.gt.mynews.usecases

import com.gt.mynews.data.ArticleApiResponse

interface NytUseCase{

    fun getMostPopular() : ArticleApiResponse?
    fun getTopStories() : ArticleApiResponse?
    fun getScience() : ArticleApiResponse?
    fun getTechnology() : ArticleApiResponse?
    /*fun getSearch(keywordToSearch : String,
                  keywordFilter : String?,
                  beginDate : String?,
                  endDate : String?) : ArticleApiResponse?*/

}
