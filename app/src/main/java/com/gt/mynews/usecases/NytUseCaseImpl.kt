package com.gt.mynews.usecases

import com.gt.mynews.data.ArticleApiResponse
import com.gt.mynews.utils.TLSSocketFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.util.*
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

class NytUseCaseImpl : NytUseCase {

    companion object{

        private const val APIKEY_NAME = "api-key"
        private const val APIKEY_VALUE = "vWKN7OglEv7zK9KY4pWAHwAgqU0s9Rhk"

        private var serviceMP : NytApi
        private var serviceS : NytApi
        private var serviceTS : NytApi
        private var serviceSearch : NytApi

        init {

            val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            trustManagerFactory.init(null as KeyStore?)
            val trustManagers = trustManagerFactory.trustManagers
            if (trustManagers.size != 1 || trustManagers[0] !is X509TrustManager) {
                throw IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers))
            }
            val trustManager = trustManagers[0] as X509TrustManager

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
                    .sslSocketFactory(TLSSocketFactory(), trustManager)
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

    override fun getSearch(keywordToSearch: String?,
                           keywordFilter: String?,
                           beginDate: String?,
                           endDate: String?): ArticleApiResponse? {

        return serviceSearch.getSearchApi(keywordToSearch, keywordFilter, beginDate, endDate)
                .execute()
                .body()
    }
}
