package com.gt.mynews.usecases

import com.gt.mynews.data.ArticleApiResponseS

interface NytUseCase{

    fun getMostPopular() : ArticleApiResponseS
    fun getTopStories() : ArticleApiResponseS
    fun getScience() : ArticleApiResponseS
    fun getTechnology() : ArticleApiResponseS

}
