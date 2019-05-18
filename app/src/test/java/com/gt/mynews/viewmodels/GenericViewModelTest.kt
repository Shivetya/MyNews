package com.gt.mynews.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gt.mynews.data.ArticleApiResponse
import com.gt.mynews.data.Result
import com.gt.mynews.usecases.NytUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GenericViewModelTest{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var useCase : NytUseCase

    @Before
    fun setUseCase() {
        useCase = object : NytUseCase {
            override fun getMostPopular(): ArticleApiResponse? {
                return ArticleApiResponse().apply {
                    results = listOf(Result().apply {
                        section = "lolilol"
                        geoFacet = listOf("Ici !", "Vraim€nt Ici !!!")
                        publishedDate = "2019-05-15"
                        title = "De$ vol€urs d'arg€nt vont €n prison !"
                    })
                }
            }

            override fun getTopStories(): ArticleApiResponse? {
                return ArticleApiResponse().apply {
                    results = listOf(Result().apply {
                        section = "lolilol"
                        geoFacet = listOf("Ici !", "Vraim€nt Ici !!!")
                        publishedDate = "2019-05-15"
                        title = "De$ vol€urs d'arg€nt vont €n prison !"
                    })
                }
            }

            override fun getScience(): ArticleApiResponse? {
                return ArticleApiResponse().apply {
                    results = listOf(Result().apply {
                        section = "lolilol"
                        geoFacet = listOf("Ici !", "Vraim€nt Ici !!!")
                        publishedDate = "2019-05-15"
                        title = "De$ vol€urs d'arg€nt vont €n prison !"
                    })
                }
            }

            override fun getTechnology(): ArticleApiResponse? {
                return ArticleApiResponse().apply {
                    results = listOf(Result().apply {
                        section = "lolilol"
                        geoFacet = listOf("Ici !", "Vraim€nt Ici !!!")
                        publishedDate = "2019-05-15"
                        title = "De$ vol€urs d'arg€nt vont €n prison !"
                    })
                }
            }
        }
    }



    @Test
    fun `should expose list of models after initialisation`(){

        //given
        val viewModel = runBlocking {GenericViewModel(useCase)}

        //then
        assertEquals("2019-05-15", viewModel.articles.value?.get(0)?.date)
    }
}