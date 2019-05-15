package com.gt.mynews.viewmodels

import com.gt.mynews.data.ArticleApiResponse
import com.gt.mynews.data.Doc
import com.gt.mynews.data.Response
import com.gt.mynews.data.Result
import com.gt.mynews.usecases.NytUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

class GenericViewModelTest{

    @Test
    fun `should expose list of models after initialisation`(){
        //given
        val useCase = object : NytUseCase{
            override fun getMostPopular(): ArticleApiResponse? {
                return ArticleApiResponse().apply {
                    results = listOf(Result().apply {
                        section = "lolilol"
                        geoFacet = listOf("Ici !", "Vraim€nt Ici !!!")
                        publishedDate = "2019-05-15"
                        title = "De$ vol€ur\$ d'arg€nt vont €n pri\$on !"
                    })
                }
            }

            override fun getTopStories(): ArticleApiResponse? {
                return ArticleApiResponse().apply {
                    results = listOf(Result().apply {
                        section = "lolilol"
                        geoFacet = listOf("Ici !", "Vraim€nt Ici !!!")
                        publishedDate = "2019-05-15"
                        title = "De$ vol€ur\$ d'arg€nt vont €n pri\$on !"
                    })
                }
            }

            override fun getScience(): ArticleApiResponse? {
                return ArticleApiResponse().apply {
                    results = listOf(Result().apply {
                        section = "lolilol"
                        geoFacet = listOf("Ici !", "Vraim€nt Ici !!!")
                        publishedDate = "2019-05-15"
                        title = "De$ vol€ur\$ d'arg€nt vont €n pri\$on !"
                    })
                }
            }

            override fun getTechnology(): ArticleApiResponse? {
                return ArticleApiResponse().apply {
                    results = listOf(Result().apply {
                        section = "lolilol"
                        geoFacet = listOf("Ici !", "Vraim€nt Ici !!!")
                        publishedDate = "2019-05-15"
                        title = "De$ vol€ur\$ d'arg€nt vont €n pri\$on !"
                    })
                }
            }
        }
        val viewModel = GenericViewModel(useCase)


        //then
        assertEquals("2019-05-15", viewModel.articles.value?.get(0)?.date)
    }
}