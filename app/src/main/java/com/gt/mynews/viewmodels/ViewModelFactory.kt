package com.gt.mynews.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gt.mynews.MainApplication
import com.gt.mynews.data.repositories.NotificationSettingsArticlesSaved
import com.gt.mynews.usecases.ApiSettingsSaveUseCase
import com.gt.mynews.usecases.NytUseCase
import com.gt.mynews.usecases.NytUseCaseImpl
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(
        private val nytUseCase: NytUseCase,
        private val apiSettingsSaveUseCase: ApiSettingsSaveUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(MostPopularViewModel::class.java) -> MostPopularViewModel(nytUseCase) as T
            modelClass.isAssignableFrom(TopStoriesViewModel::class.java) -> TopStoriesViewModel(nytUseCase) as T
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> SearchViewModel(nytUseCase) as T
            modelClass.isAssignableFrom(NotificationViewModel::class.java) -> NotificationViewModel(apiSettingsSaveUseCase) as T
            else -> throw IllegalArgumentException("Wrong UseCase Parameter")
        }
    }

    companion object{

        val INSTANCE = ViewModelFactory(NytUseCaseImpl(), ApiSettingsSaveUseCase(NotificationSettingsArticlesSaved(MainApplication.getContext())))
    }

}