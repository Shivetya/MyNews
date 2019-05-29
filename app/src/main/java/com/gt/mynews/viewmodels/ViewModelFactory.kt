package com.gt.mynews.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gt.mynews.usecases.NytUseCase
import com.gt.mynews.usecases.NytUseCaseImpl
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val nytUseCase: NytUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(MostPopularViewModel::class.java)){
            return MostPopularViewModel(nytUseCase) as T
        }
        else if(modelClass.isAssignableFrom(TopStoriesViewModel::class.java)){
            return TopStoriesViewModel(nytUseCase) as T
        }
        throw IllegalArgumentException("Wrong UseCase Parameter")
    }

    companion object{

        val INSTANCE = ViewModelFactory(NytUseCaseImpl())
    }

}