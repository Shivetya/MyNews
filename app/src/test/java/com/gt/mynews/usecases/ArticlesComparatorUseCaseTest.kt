package com.gt.mynews.usecases

import com.gt.mynews.data.ArticleApiResponse
import com.gt.mynews.data.Doc
import com.gt.mynews.data.Response
import com.gt.mynews.data.repositories.SharedPreferencesInterface
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArticlesComparatorUseCaseTest {

    private val mockNytUseCase = Mockito.mock(NytUseCase::class.java)
    private val mockApiSettingsSaveUseCase = Mockito.mock(SettingsSaveUseCaseInterface::class.java)
    private val mockSharedPreferencesInterface = Mockito.mock(SharedPreferencesInterface::class.java)

    lateinit var comparatorUseCase: ArticlesComparatorUseCase

    @Before
    fun setUpMock(){
        comparatorUseCase = ArticlesComparatorUseCase(mockSharedPreferencesInterface,
                mockNytUseCase,
                mockApiSettingsSaveUseCase)

        doReturn(ArticleApiResponse().apply{
            response = Response().apply {
                docs = listOf(Doc().apply {
                    sectionName = "NewTitle"
                }, Doc().apply {
                    sectionName = "Older Title"
                }, Doc().apply {
                    sectionName = "Very old Title"
                })
            }
        })
                .`when`(mockNytUseCase).getSearch("(\"keyword\")", "news_desk:(\"keywordFilter\")", null,null)

    }

    @Test
    fun `should return 2 when 2 new titles`(){
        //given
        doReturn("Very old Title").`when`(mockSharedPreferencesInterface).getOldArticle()
        doReturn("keyword").`when`(mockApiSettingsSaveUseCase).getKeyword()
        doReturn(listOf("keywordFilter")).`when`(mockApiSettingsSaveUseCase).getKeywordFilter()

        //then
        assertEquals(2, comparatorUseCase.howManyNewArticles())
    }

    @Test
    fun `should return 0 when no new article`(){
        doReturn("NewTitle").`when`(mockSharedPreferencesInterface).getOldArticle()
        doReturn("keyword").`when`(mockApiSettingsSaveUseCase).getKeyword()
        doReturn(listOf("keywordFilter")).`when`(mockApiSettingsSaveUseCase).getKeywordFilter()

        //then
        assertEquals(0, comparatorUseCase.howManyNewArticles())
    }

    @Test
    fun `should launch the search with the good keyword from repo`(){
        doReturn("keyword").`when`(mockApiSettingsSaveUseCase).getKeyword()
        doReturn(listOf("keywordFilter_1", "keywordFilter_2")).`when`(mockApiSettingsSaveUseCase).getKeywordFilter()

        doReturn(ArticleApiResponse().apply{
            response = Response().apply {
                docs = listOf(Doc().apply {
                    sectionName = "NewTitle"
                }, Doc().apply {
                    sectionName = "Older Title"
                }, Doc().apply {
                    sectionName = "Very old Title"
                })
            }
        })
                .`when`(mockNytUseCase).getSearch("(\"keyword\")", "news_desk:(\"keywordFilter_1\" \"keywordFilter_2\")", null,null)

        //when
        comparatorUseCase.howManyNewArticles()

        //then
        verify(mockNytUseCase).getSearch((eq("(\"keyword\")"))
                ,eq("news_desk:(\"keywordFilter_1\" \"keywordFilter_2\")")
                ,eq(null)
                ,eq(null))

    }

    @Test
    fun `should launch save title with good title`(){
        //given
        val newTitle = "newTitle"

        //when
        comparatorUseCase.saveTitle(newTitle)

        //then
        verify(mockSharedPreferencesInterface).putTitleNewArticleInSharedPreferences(eq(newTitle))
    }

    @Test
    fun `should transform keyword and keywordFilter to query ready`(){
        //given
        val keyword = "black cat"

        //when
        val keywordTransformed = comparatorUseCase.transformKeywordQueryReady(keyword)

        //then
        assertEquals("(\"black cat\")", keywordTransformed)
    }

    @Test
    fun `should transform keyword filter to query ready`(){
        //giver
        val keywordFilter = arrayListOf("arts", "politics")

        //when
        val keywordFilterTransformed = comparatorUseCase.transformKeywordFilterToQueryReady(keywordFilter)
        //then
        assertEquals("news_desk:(\"arts\" \"politics\")", keywordFilterTransformed)
    }
}