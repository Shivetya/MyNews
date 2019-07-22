package com.gt.mynews.usecases

import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.testing.TestListenableWorkerBuilder
import com.gt.mynews.Notifications.NotificationWorker
import com.nhaarman.mockitokotlin2.doReturn
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class NotificationWorkerTest {

    private val mockArticleComparatorUseCase = Mockito.mock(ArticlesComparatorUseCaseInterface::class.java)
    private val context = InstrumentationRegistry.getInstrumentation().context

    @Test
    fun `mNewArticle should be true if no new article`(){
        //given
        val notificationWorker = TestListenableWorkerBuilder<NotificationWorker>(context).build()

        //when
        notificationWorker.setMockedUseCaseForTest(mockArticleComparatorUseCase)
        doReturn(true).`when`(mockArticleComparatorUseCase).isThereNewArticle()

        notificationWorker.doWork()

        //then
        assertTrue( notificationWorker.mIsNewArticle)
    }

    @Test
    fun `mNewArticle should be false if no new article`(){
        //given
        val notificationWorker = TestListenableWorkerBuilder<NotificationWorker>(context).build()

        //when
        notificationWorker.setMockedUseCaseForTest(mockArticleComparatorUseCase)
        doReturn(false).`when`(mockArticleComparatorUseCase).isThereNewArticle()

        notificationWorker.doWork()

        //then
        assertFalse( notificationWorker.mIsNewArticle)
    }
}