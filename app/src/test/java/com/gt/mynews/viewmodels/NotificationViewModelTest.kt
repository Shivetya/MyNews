package com.gt.mynews.viewmodels

import androidx.test.core.app.ApplicationProvider
import com.gt.mynews.data.repositories.NotificationSettingsArticlesSaved
import com.gt.mynews.usecases.ApiSettingsSaveUseCase
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class NotificationViewModelTest {

    private val useCase: ApiSettingsSaveUseCase = ApiSettingsSaveUseCase(NotificationSettingsArticlesSaved(ApplicationProvider.getApplicationContext()))

    @Test
    fun `should transform keyword and keywordFilter to query ready`(){
        //given
        val viewModel = NotificationViewModel(useCase)
        val keyword = "black cat"
        //sortir la fonction du assert
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