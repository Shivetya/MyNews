package com.gt.mynews.viewmodels

import android.content.Context
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gt.mynews.data.*
import com.gt.mynews.testutils.CoroutinesTestRule
import com.gt.mynews.usecases.NytUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import net.danlew.android.joda.JodaTimeAndroid
import org.mockito.Mockito.`when`
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.mock
import java.io.InputStream


@ExperimentalCoroutinesApi
class GenericViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private lateinit var useCase: NytUseCase

    val mostPopularResponse : ArticleApiResponse = ArticleApiResponse().apply {
            results = listOf(Result().apply {
                section = "lolilol"
                publishedDate = "2019-05-15T06:14:38-04:00"
                title = "De$ vol€ur$ d'arg€nt vont €n prison !"
                media = listOf(Medium().apply {
                    mediaMetadata = listOf(MediaMetadatum().apply {
                        url = "Que de chemin pour cet URL !"
                    })
                })
            },Result().apply {  })
    }

    val topStoriesResponse : ArticleApiResponse = ArticleApiResponse().apply {
            results = listOf(Result().apply {
                section = "lolilol"
                publishedDate = "2019-05-05T06:14:38-04:00"
                title = "Yep, à tester aussi !"
                url = "Ceci est une URL"
                multimedia = listOf(Multimedium())
            },Result().apply {  })
    }

    val scienceResponse : ArticleApiResponse = ArticleApiResponse().apply {
        results = listOf(Result().apply {
            section = "lolilol"
            publishedDate = "2019-05-15T06:14:38-04:00"
            title = "Des mots à tester"
            multimedia = listOf(Multimedium())
        },Result().apply {  })
    }

    val technologyResponse : ArticleApiResponse = ArticleApiResponse().apply {
            results = listOf(Result().apply {
                section = "lolilol"
                publishedDate = "2019-05-15T06:14:38-04:00"
                title = "Mouahahahahahahha"
                multimedia = listOf(Multimedium())
            },Result().apply {

            })
    }


    // region @Before initialization
    @Before
    fun setUseCase() {
        useCase = object : NytUseCase {
            override fun getMostPopular(): ArticleApiResponse? = mostPopularResponse

            override fun getTopStories(): ArticleApiResponse? = topStoriesResponse

            override fun getScience(): ArticleApiResponse? = scienceResponse

            override fun getTechnology(): ArticleApiResponse? = technologyResponse

        }
    }

    //This fun is for mock context to allow init of jodatime in tests.
    @Before
    fun prepareTimeBeforeTests() {
        val context = mock(Context::class.java)
        val appContext = mock(Context::class.java)
        val resources = mock(Resources::class.java)
        `when`(resources.openRawResource(anyInt())).thenReturn(mock(InputStream::class.java))
        `when`(appContext.resources).thenReturn(resources)
        `when`(context.applicationContext).thenReturn(appContext)
        JodaTimeAndroid.init(context)
    }

    @Test
    fun `should expose list of models (articles Most Popular) - get date`() = runBlockingTest {
        //given
        mostPopularResponse.results[0].apply {
            publishedDate = "2019-05-15T06:14:38-04:00"
        }
        val viewModel = MostPopularViewModel(useCase)

        viewModel.fetchArticles(null)

        //then
        assertEquals("2019-05-15", viewModel.articles.value?.get(0)?.date)
    }

    @Test
    fun `should expose list of models (articles Most Popular) - get section`() = runBlockingTest {
        //given
        mostPopularResponse.results[0].apply {
            section = "lolilol"
        }
        val viewModel = MostPopularViewModel(useCase)

        viewModel.fetchArticles("bonjour")

        //then

        assertEquals("lolilol", viewModel.articles.value?.get(0)?.categoryArticle)
    }

    @Test
    fun `should expose list of models (articles Most Popular) - get URL`() = runBlockingTest {
        //given
        mostPopularResponse.results[0].apply {
            media = listOf(Medium().apply {
                mediaMetadata = listOf(MediaMetadatum().apply {
                    url = "Que de chemin pour cet URL !"
                })
            })
        }
        val viewModel = MostPopularViewModel(useCase)

        viewModel.fetchArticles(null)

        //then
        assertEquals("Que de chemin pour cet URL !", viewModel.articles.value?.get(0)?.imageUrl)
    }

    @Test
    fun `should expose list of models (articles Most Popular) - get title`() = runBlockingTest {
        //given
        mostPopularResponse.results[0].apply {
            title = "De\$ vol€ur\$ d'arg€nt vont €n prison !"
        }
        val viewModel = MostPopularViewModel(useCase)

        viewModel.fetchArticles(null)

        //then
        assertEquals("De\$ vol€ur\$ d'arg€nt vont €n prison !", viewModel.articles.value?.get(0)?.articleTitle)
    }

    @Test
    fun `should expose list of models (articles Most Popular) second article - get date`() = runBlockingTest {
        //given
        mostPopularResponse.results[1].apply {
            publishedDate = "2019-06-04T06:14:38-04:00"
        }
        val viewModel = MostPopularViewModel(useCase)

        viewModel.fetchArticles("Bonjour")

        //then
        assertEquals("2019-06-04", viewModel.articles.value?.get(1)?.date)
    }

    @Test
    fun`should expose list of models (articles Top Stories) with keyword Science - get Title`() = runBlockingTest {
        //given
        scienceResponse.results[0].apply {
            title = "Des mots à tester"
        }
        val viewModel = TopStoriesViewModel(useCase)

        //when
        viewModel.fetchArticles("science")

        //then

        assertEquals("Des mots à tester", viewModel.articles.value?.get(0)?.articleTitle)
    }

    @Test
    fun`should expose list of models (articles Top Stories) with keyword Technology - get Title`() = runBlockingTest {
        //given
        technologyResponse.results[0].apply {
            title = "Mouahahahahahahha"
        }
        val viewModel = TopStoriesViewModel(useCase)

        //when
        viewModel.fetchArticles("technology")

        //then

        assertEquals("Mouahahahahahahha", viewModel.articles.value?.get(0)?.articleTitle)
    }

    @Test
    fun`should expose list of models (articles Top Stories) with keyword Home - get Title`() = runBlockingTest {
        //given
        topStoriesResponse.results[0].apply {
            title = "Yep, à tester aussi !"
        }
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

        assertNull(viewModel.articles.value?.get(0)?.articleTitle)
    }

    @Test
    fun`should expose second article (articles Top Stories) with science - get second Title`() = runBlockingTest {
        //given
        scienceResponse.results[1].apply {
            title = "Puit"
        }
        val viewModel = TopStoriesViewModel(useCase)

        //when
        viewModel.fetchArticles("science")

        //then

        assertEquals("Puit", viewModel.articles.value?.get(1)?.articleTitle)
    }

    @Test
    fun`should expose second article (articles Top Stories) with technology - get second date`() = runBlockingTest {
        //given
        technologyResponse.results[1].apply {
            publishedDate = "2019-06-04T06:14:38-04:00"
        }
        val viewModel = TopStoriesViewModel(useCase)

        //when
        viewModel.fetchArticles("technology")

        //then

        assertEquals("2019-06-04", viewModel.articles.value?.get(1)?.date)
    }

    @Test
    fun`should expose first article (articles Top Stories) with home - get URL`() = runBlockingTest {
        //given
        topStoriesResponse.results[0].apply {
            url = "Ceci est une URL"
        }
        val viewModel = TopStoriesViewModel(useCase)

        //when
        viewModel.fetchArticles("home")

        //then

        assertEquals("Ceci est une URL", viewModel.articles.value?.get(0)?.url)
    }

    @Test
    fun`should not crash and return null (articles Top Stories) with home - get date null`() = runBlockingTest {
        //given
        topStoriesResponse.results[0].apply {
            publishedDate = null
        }

        val viewModel = TopStoriesViewModel(useCase)

        //when
        viewModel.fetchArticles("home")

        //then

        assertEquals(null.toString(), viewModel.articles.value?.get(0)?.date)
    }
}