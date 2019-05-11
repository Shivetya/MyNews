package com.gt.mynews.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gt.mynews.data.ArticleApiResponse
import com.gt.mynews.usecases.NytUseCase
import kotlinx.coroutines.Job


class GenericViewModel (private val useCase : NytUseCase) : ViewModel() {

    private val _mutableLiveData = MutableLiveData<ArticleApiResponse>()
    val mutableListOfLiveData : LiveData<ArticleApiResponse> = _mutableLiveData

    private var currentJob : Job? = null

    init {

    }
}