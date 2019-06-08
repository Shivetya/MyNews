package com.gt.mynews.usecases

import com.gt.mynews.data.ArticleApiResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NytUseCaseImpl : NytUseCase {

    companion object{

        private const val APIKEY_NAME = "api-key"
        private const val APIKEY_VALUE = "vWKN7OglEv7zK9KY4pWAHwAgqU0s9Rhk"

        private var serviceMP : NytApi
        private var serviceS : NytApi
        private var serviceTS : NytApi
        private var serviceSearch : NytApi

        init {

            val apiKeyInterceptor = Interceptor{chain ->
                var request = chain.request()
                val url = request.url()
                        .newBuilder()
                        .addQueryParameter(APIKEY_NAME, APIKEY_VALUE)
                        .build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            }

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                    .addInterceptor(apiKeyInterceptor)
                    .addInterceptor(loggingInterceptor)
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.nytimes.com/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            serviceMP = retrofit.create(NytApi::class.java)
            serviceS = retrofit.create(NytApi::class.java)
            serviceTS = retrofit.create(NytApi::class.java)
            serviceSearch = retrofit.create(NytApi::class.java)
        }
    }

    override fun getMostPopular(): ArticleApiResponse? {
        return serviceMP.getMostPopularApi().execute().body()
    }

    override fun getTopStories(): ArticleApiResponse? {
        return serviceTS.getTopStoriesApi().execute().body()
    }

    override fun getScience(): ArticleApiResponse? {
        return serviceTS.getScienceApi().execute().body()
    }

    override fun getTechnology(): ArticleApiResponse? {
        return serviceTS.getTechnologyApi().execute().body()
    }

    override fun getSearch(keywordToSearch: String,
                           keywordFilter: String?,
                           beginDate: String?,
                           endDate: String?): ArticleApiResponse? {

        return serviceSearch.getSearchApi(keywordToSearch, keywordFilter, beginDate, endDate)
                .execute()
                .body()
    }
}
