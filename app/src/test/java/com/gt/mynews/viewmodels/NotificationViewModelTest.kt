package com.gt.mynews.viewmodels

import com.gt.mynews.data.repositories.NotificationSettingsArticlesSaved
import com.gt.mynews.usecases.NotificationUseCase
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class NotificationViewModelTest {

    private val useCase: NotificationUseCase = NotificationUseCase(NotificationSettingsArticlesSaved(RuntimeEnvironment.application))

    @Test
    fun `should transform keyword and keywordFilter to query ready`(){
        //given
        val viewModel = NotificationViewModel(useCase)
        val keyword = "black cat"

        //then
        assertEquals("(\"black cat\")", viewModel.transformKeywordQueryReady(keyword))
    }

    @Test
    fun `should transform keyword filter to query ready`(){
        //giver
        val viewModel = NotificationViewModel(useCase)
        val keywordFilter = arrayListOf("\"arts\"", "\"politics\"")

        //then
        assertEquals("news_desk:(\"arts\" \"politics\")",
                viewModel.transformKeywordFilterToQueryReady(keywordFilter))
    }
}