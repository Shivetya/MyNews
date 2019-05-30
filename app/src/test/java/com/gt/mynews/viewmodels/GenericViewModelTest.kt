package com.gt.mynews.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gt.mynews.data.*
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
                        media = listOf(Medium().apply {
                            mediaMetadata = listOf(MediaMetadatum().apply {
                                url = "Que de chemin pour cet URL !"
                            })
                        })
                    },Result().apply {
                        section = "Abracadabra"
                        desFacet = listOf("KH", "FF")
                        publishedDate = "aujourd'hui"
                        title = "un gros titre scandaleux ici"
                    })
                }
            }

            override fun getTopStories(): ArticleApiResponse? {
                return ArticleApiResponse().apply {
                    results = listOf(Result().apply {
                        section = "lolilol"
                        desFacet = listOf("Ici !", "Vraim€nt Ici !!!")
                        publishedDate = "2019-05-15T16684354"
                        title = "Yep, à tester aussi !"
                        multimedia = listOf(Multimedium())
                    },Result().apply {
                        section = "cheveux"
                        desFacet = listOf("tada", "tidi")
                        publishedDate = "2019-05-15T16684354"
                        title = "un gros titre scandaleux ici, come d'hab"
                        multimedia = listOf(Multimedium())
                    })
                }
            }

            override fun getScience(): ArticleApiResponse? {
                return ArticleApiResponse().apply {
                    results = listOf(Result().apply {
                        section = "lolilol"
                        desFacet = listOf("Ici !", "Vraim€nt Ici !!!")
                        publishedDate = "2019-05-15T16684354"
                        title = "Des mots à tester"
                        multimedia = listOf(Multimedium())
                    },Result().apply {
                        section = "Papier"
                        desFacet = listOf("Feuille", "Ciseaux")
                        publishedDate = "2019-05-15T16684354"
                        title = "Puit"
                        multimedia = listOf(Multimedium())
                    })
                }
            }

            override fun getTechnology(): ArticleApiResponse? {
                return ArticleApiResponse().apply {
                    results = listOf(Result().apply {
                        section = "lolilol"
                        desFacet = listOf("Ici ! ", "Vraim€nt Ici !!!")
                        publishedDate = "2019-05-15T16684354"
                        title = "Mouahahahahahahha"
                        multimedia = listOf(Multimedium())
                    },Result().apply {
                        section = "Cloud"
                        desFacet = listOf("Tifa", "Barret")
                        publishedDate = "2019-05-15T16684354"
                        title = "Aerith"
                        multimedia = listOf(Multimedium())
                    })
                }
            }
        }
    }

    @Test
    fun `should expose list of models (articles Most Popular) - get date`() = runBlockingTest {
        //given
        val viewModel = MostPopularViewModel(useCase)

        viewModel.fetchArticles(null)

        //then
        assertEquals("2019-05-15", viewModel.articles.value?.get(0)?.date)
    }

    @Test
    fun `should expose list of models (articles Most Popular) - get section`() = runBlockingTest {
        //given
        val viewModel = MostPopularViewModel(useCase)

        viewModel.fetchArticles("bonjour")

        //then

        assertEquals("lolilol", viewModel.articles.value?.get(0)?.categoryArticle)
    }

    @Test
    fun `should expose list of models (articles Most Popular) - get URL`() = runBlockingTest {
        //given
        val viewModel = MostPopularViewModel(useCase)

        viewModel.fetchArticles(null)

        //then
        assertEquals("Que de chemin pour cet URL !", viewModel.articles.value?.get(0)?.imageUrl)
    }

    @Test
    fun `should expose list of models (articles Most Popular) - get title`() = runBlockingTest {
        //given
        val viewModel = MostPopularViewModel(useCase)

        viewModel.fetchArticles(null)

        //then
        assertEquals("De\$ vol€ur\$ d'arg€nt vont €n prison !", viewModel.articles.value?.get(0)?.articleTitle)
    }

    @Test
    fun `should expose list of models (articles Most Popular) second article - get date`() = runBlockingTest {
        //given
        val viewModel = MostPopularViewModel(useCase)

        viewModel.fetchArticles("Bonjour")

        //then
        assertEquals("aujourd'hui", viewModel.articles.value?.get(1)?.date)
    }

    @Test
    fun`should expose list of models (articles Top Stories) with keyword Science - get Title`() = runBlockingTest {
        //given
        val viewModel = TopStoriesViewModel(useCase)

        //when
        viewModel.fetchArticles("science")

        //then

        assertEquals("Des mots à tester", viewModel.articles.value?.get(0)?.articleTitle)
    }

    @Test
    fun`should expose list of models (articles Top Stories) with keyword Technology - get Title`() = runBlockingTest {
        //given
        val viewModel = TopStoriesViewModel(useCase)

        //when
        viewModel.fetchArticles("technology")

        //then

        assertEquals("Mouahahahahahahha", viewModel.articles.value?.get(0)?.articleTitle)
    }

    @Test
    fun`should expose list of models (articles Top Stories) with keyword Home - get Title`() = runBlockingTest {
        //given
        val viewModel = TopStoriesViewModel(useCase)

        //when
        viewModel.fetchArticles("home")

        //then

        assertEquals("Yep, à tester aussi !", viewModel.articles.value?.get(0)?.articleTitle)
    }

    @Test
    fun`should expose null (articles Top Stories) with random keyword - get Title`() = runBlockingTest {
        //given
        val viewModel = TopStoriesViewModel(useCase)

        //when
        viewModel.fetchArticles("Lol")

        //then

        assertEquals(null, viewModel.articles.value?.get(0)?.articleTitle)
    }

    @Test
    fun`should expose second article (articles Top Stories) with science - get second Title`() = runBlockingTest {
        //given
        val viewModel = TopStoriesViewModel(useCase)

        //when
        viewModel.fetchArticles("science")

        //then

        assertEquals("Puit", viewModel.articles.value?.get(1)?.articleTitle)
    }

    @Test
    fun`should expose second article (articles Top Stories) with technology - get second date`() = runBlockingTest {
        //given
        val viewModel = TopStoriesViewModel(useCase)

        //when
        viewModel.fetchArticles("technology")

        //then

        assertEquals("2019-05-15", viewModel.articles.value?.get(1)?.date)
    }
}