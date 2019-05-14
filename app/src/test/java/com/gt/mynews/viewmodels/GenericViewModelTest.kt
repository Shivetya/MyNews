package com.gt.mynews.viewmodels

import com.gt.mynews.data.ArticleApiResponse
import com.gt.mynews.data.Doc
import com.gt.mynews.data.Response
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
                    response = Response().apply {
                        docs = listOf(Doc().apply {
                            snippet = "Snippet = titre de l'atricle lol !!@)"
                            pubDate = "2019-05-14"
                        })
                    }

                }
            }

            override fun getTopStories(): ArticleApiResponse? {
                return ArticleApiResponse().apply {
                    response = Response().apply {
                        docs = listOf(Doc().apply {
                            snippet = "Snippet = titre de l'atricle lol !!@)"
                            pubDate = "2019-05-14"
                        })
                    }

                }
            }

            override fun getScience(): ArticleApiResponse? {
                return ArticleApiResponse().apply {
                    response = Response().apply {
                        docs = listOf(Doc().apply {
                            snippet = "Snippet = titre de l'atricle lol !!@)"
                            pubDate = "2019-05-14"
                        })
                    }

                }
            }

            override fun getTechnology(): ArticleApiResponse? {
                return ArticleApiResponse().apply {
                    response = Response().apply {
                        docs = listOf(Doc().apply {
                            snippet = "Snippet = titre de l'atricle lol !!@)"
                            pubDate = "2019-05-14"
                        })
                    }

                }
            }
        }
        val viewModel = GenericViewModel(useCase)


        //then
        assertEquals("2019-05-14", viewModel.articles.value?.get(0)?.date)
    }
}