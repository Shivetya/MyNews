package com.gt.mynews.usecases

import com.gt.mynews.data.ArticleApiResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NytApi {

    @GET("svc/mostpopular/v2/viewed/7.json")
    fun getMostPopularApi() : Call<ArticleApiResponse>

    @GET("svc/topstories/v2/home.json")
    fun getTopStoriesApi() : Call<ArticleApiResponse>

    @GET("svc/topstories/v2/science.json")
    fun getScienceApi() : Call<ArticleApiResponse>

    @GET("svc/topstories/v2/technology.json")
    fun getTechnologyApi() : Call<ArticleApiResponse>

    @GET("/svc/search/v2/articlesearch.json")
    fun getSearchApi(@Query("q")keywordToSearch : String,
                     @Query("fq")keywordFilter : String?,
                     @Query("begin_date")beginDate : String?,
                     @Query("end_date")endDate : String?) : Call<ArticleApiResponse>
}