package com.gt.mynews.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gt.mynews.data.ArticleApiResponse
import com.gt.mynews.data.Result
import com.gt.mynews.testutils.CoroutinesTestRule
import com.gt.mynews.usecases.NytUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GenericViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private lateinit var useCase: NytUseCase

    @Test
    fun `should expose list of models after initialisation`() = runBlockingTest {
        //given
        val viewModel = GenericViewModel(useCase)

        viewModel.fetchArticles()

        //then
        assertEquals("2019-05-15", viewModel.articles.value?.get(0)?.date)
    }

    // region @Before initialization
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
    // endregion
}