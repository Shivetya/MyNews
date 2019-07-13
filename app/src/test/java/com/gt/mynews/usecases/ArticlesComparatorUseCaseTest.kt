package com.gt.mynews.usecases

import com.gt.mynews.data.repositories.NotificationSettingsArticlesSaved
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArticlesComparatorUseCaseTest {

    val mockNytUseCase = Mockito.mock(NytUseCase::class.java)
    val mockApiSettingsSaveUseCase = Mockito.mock(ApiSettingsSaveUseCase::class.java)
    val mockNotificationSettingsArticlesSaved = mock<NotificationSettingsArticlesSaved>{
        given {getNewArticle()} willReturn "New Article"
    }

    @Test
    fun `should save new title`(){
        //given
        val newArticleTitle = "This is a title"
        var newArticleTitleToTest = null


    }
}