package com.gt.mynews.usecases

import com.gt.mynews.data.repositories.NotificationSettingsArticlesSaved
import com.gt.mynews.data.repositories.SharedPreferencesInterface
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NotificationUseCaseTest {

    private var stringToTestNewArticle: String? = null
    private var stringToTestKeyword: String? = null
    private var stringToTestKeywordFilter: String? = null
    private var stringToTestOldArticle: String? = null
    private lateinit var repo: SharedPreferencesInterface

    @Before
    fun setRepo(){
        repo = object : SharedPreferencesInterface {
            override fun saveString(key: String, stringToSave: String?) {
                when(key) {
                    NotificationSettingsArticlesSaved.KEY_NEW_ARTICLE -> {
                        val temp = stringToTestNewArticle
                        stringToTestNewArticle = stringToSave
                        stringToTestOldArticle = temp
                    }
                    NotificationSettingsArticlesSaved.KEY_KEYWORD -> stringToTestKeyword = stringToSave
                    NotificationSettingsArticlesSaved.KEY_KEYWORD_FILTER -> stringToTestKeywordFilter = stringToSave
                    else -> throw IllegalArgumentException("Wrong key !")
                }
            }

            override fun getString(key: String): String? {
                return when(key) {
                    NotificationSettingsArticlesSaved.KEY_NEW_ARTICLE -> stringToTestNewArticle
                    NotificationSettingsArticlesSaved.KEY_KEYWORD -> stringToTestKeyword
                    NotificationSettingsArticlesSaved.KEY_KEYWORD_FILTER -> stringToTestKeywordFilter
                    NotificationSettingsArticlesSaved.KEY_OLD_ARTICLE -> stringToTestOldArticle
                    else -> throw IllegalArgumentException("Wrong key !")
                }
            }
        }
    }


    @Test
    fun `should save new article in shared preferences`(){
        val useCase = NotificationUseCase(repo)

        //given
        val articleTitleToSave = "bonjour"

        //when
        useCase.saveTitle(articleTitleToSave)

        //then
        assertEquals("bonjour", stringToTestNewArticle)
    }

    @Test
    fun `should save keyword in shared preferences`(){
        val useCase = NotificationUseCase(repo)

        //given
        val keywordToSave = "Ceci est un keyword"

        //when
        useCase.saveKeyword(keywordToSave)

        //then
        assertEquals("Ceci est un keyword", stringToTestKeyword)
    }

    @Test
    fun `should save keyword filter in shared preferences`(){
        val useCase = NotificationUseCase(repo)

        //given
        val keywordFilterToSave = "Ceci est un keywordFilter"

        //when
        useCase.saveKeywordFilter(keywordFilterToSave)

        //then
        assertEquals("Ceci est un keywordFilter", stringToTestKeywordFilter)
    }

    @Test
    fun `should save the old "new article" in other key in shared preferences`(){
        val useCase = NotificationUseCase(repo)

        //given
        val articleFirstToSave = "Premier Article"
        val articleSecondToSave = "Second Article!"

        //when
        useCase.saveTitle(articleFirstToSave)
        useCase.saveTitle(articleSecondToSave)

        //then
        assertEquals("Premier Article", stringToTestOldArticle)
    }

    @Test
    fun `should return new article title from shared preferences`(){
        val useCase = NotificationUseCase(repo)

        //given
        val articleToSave = "Titre d'article : Vous n'en reviendrez pas !"

        //when
        stringToTestNewArticle = articleToSave

        //then
        assertEquals("Titre d'article : Vous n'en reviendrez pas !", useCase.getNewArticleTitle())
    }

    @Test
    fun `should return old article title from shared preferences`(){
        val useCase = NotificationUseCase(repo)

        //given
        val articleToSave = "Titre d'article : Vous n'en reviendrez pas ! C'était il y a déjà 10 ans"

        //when
        stringToTestOldArticle = articleToSave

        //then
        assertEquals("Titre d'article : Vous n'en reviendrez pas ! C'était il y a déjà 10 ans", useCase.getOldArticleTitle())
    }

    @Test
    fun `should return keyword from shared preferences`(){
        val useCase = NotificationUseCase(repo)

        //given
        val keywordToSave = "mot-clef ici !"

        //when
        stringToTestKeyword = keywordToSave

        //then
        assertEquals("mot-clef ici !", useCase.getKeyword())
    }

    @Test
    fun `should return keyword filter from shared preferences`(){
        val useCase = NotificationUseCase(repo)

        //given
        val keywordFilterToSave = "ahaha, ceci est un keywordFilter"

        //when
        stringToTestKeywordFilter = keywordFilterToSave

        //then
        assertEquals("ahaha, ceci est un keywordFilter", useCase.getKeywordFilter())
    }
}