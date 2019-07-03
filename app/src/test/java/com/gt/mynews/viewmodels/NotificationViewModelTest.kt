package com.gt.mynews.viewmodels

import junit.framework.Assert.assertEquals
import org.junit.Test

class NotificationViewModelTest {

    @Test
    fun `should transform keyword and keywordFilter to query ready`(){
        //given
        val viewModel = NotificationViewModel()
        val keyword = "black cat"

        //then
        assertEquals("(\"black cat\")", viewModel.transformKeywordQueryReady(keyword))
    }

    @Test
    fun `should transform keyword filter to query ready`(){
        //giver
        val viewModel = NotificationViewModel()
        val keywordFilter = arrayListOf("\"arts\"", "\"politics\"")

        //then
        assertEquals("news_desk:(\"arts\" \"politics\")",
                viewModel.transformKeywordFilterToQueryReady(keywordFilter))
    }

}