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

    // region @Before initialization
    @Before
    fun setUseCase() {
        useCase = object : NytUseCase {
            override fun getMostPopular(): ArticleApiResponse? {
                return ArticleApiResponse().apply {
                    results = listOf(Result().apply {
                        section = "lolilol"
                        desFacet = listOf("Ici !", "Vraim€nt Ici !!!")
                        publishedDate = "2019-05-15"
                        title = "De$ vol€ur$ d'arg€nt vont €n prison !"
                    })
                }
            }

            override fun getTopStories(): ArticleApiResponse? {
                return ArticleApiResponse().apply {
                    results = listOf(Result().apply {
                        section = "lolilol"
                        desFacet = listOf("Ici !", "Vraim€nt Ici !!!")
                        publishedDate = "2019-05-15"
                        title = "Yep, à tester aussi !"
                    })
                }
            }

            override fun getScience(): ArticleApiResponse? {
                return ArticleApiResponse().apply {
                    results = listOf(Result().apply {
                        section = "lolilol"
                        desFacet = listOf("Ici !", "Vraim€nt Ici !!!")
                        publishedDate = "2019-05-15"
                        title = "Des mots à tester"
                    })
                }
            }

            override fun getTechnology(): ArticleApiResponse? {
                return ArticleApiResponse().apply {
                    results = listOf(Result().apply {
                        section = "lolilol"
                        desFacet = listOf("Ici ! ", "Vraim€nt Ici !!!")
                        publishedDate = "2019-05-15"
                        title = "Mouahahahahahahha"
                    })
                }
            }
        }
    }

    @Test
    fun `should expose list of models (articles Most Popular) - get date`() = runBlockingTest {
        //given
        val viewModel = GenericViewModel(useCase)

        viewModel.fetchArticlesMP()

        //then
        assertEquals("2019-05-15", viewModel.articles.value?.get(0)?.date)
    }

    @Test
    fun `should expose list of models (articles Most Popular) - get section`() = runBlockingTest {
        //given
        val viewModel = GenericViewModel(useCase)

        viewModel.fetchArticlesMP()

        //then

        assertEquals("lolilol > Ici !", viewModel.articles.value?.get(0)?.categoryArticle)
    }

    @Test
    fun `should expose list of models (articles Most Popular) - get URL`() = runBlockingTest {
        //given
        val viewModel = GenericViewModel(useCase)

        viewModel.fetchArticlesMP()

        //then
        assertEquals("lolilol", viewModel.articles.value?.get(0)?.imageUrl)
    }

    @Test
    fun `should expose list of models (articles Most Popular) - get title`() = runBlockingTest {
        //given
        val viewModel = GenericViewModel(useCase)

        viewModel.fetchArticlesMP()

        //then
        assertEquals("De\$ vol€ur\$ d'arg€nt vont €n prison !", viewModel.articles.value?.get(0)?.articleTitle)
    }

    @Test
    fun`should expose list of models (articles Top Stories) with keyword Science - get Title`() = runBlockingTest {
        //given
        val viewModel = GenericViewModel(useCase)

        //when
        viewModel.fetchArticlesTS("science")

        //then

        assertEquals("Des mots à tester", viewModel.articles.value?.get(0)?.articleTitle)
    }

    @Test
    fun`should expose list of models (articles Top Stories) with keyword Technology - get Title`() = runBlockingTest {
        //given
        val viewModel = GenericViewModel(useCase)

        //when
        viewModel.fetchArticlesTS("technology")

        //then

        assertEquals("Mouahahahahahahha", viewModel.articles.value?.get(0)?.articleTitle)
    }

    @Test
    fun`should expose list of models (articles Top Stories) with keyword Home - get Title`() = runBlockingTest {
        //given
        val viewModel = GenericViewModel(useCase)

        //when
        viewModel.fetchArticlesTS("home")

        //then

        assertEquals("Yep, à tester aussi !", viewModel.articles.value?.get(0)?.articleTitle)
    }

    @Test
    fun`should expose null (articles Top Stories) with random keyword - get Title`() = runBlockingTest {
        //given
        val viewModel = GenericViewModel(useCase)

        //when
        viewModel.fetchArticlesTS("Lol")

        //then

        assertEquals(null, viewModel.articles.value?.get(0)?.articleTitle)
    }
}