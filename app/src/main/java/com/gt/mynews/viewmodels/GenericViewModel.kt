package com.gt.mynews.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gt.mynews.data.ArticleApiResponse
import com.gt.mynews.usecases.NytUseCase
import kotlinx.coroutines.Job


class GenericViewModel (private val useCase : NytUseCase) : ViewModel() {

    private val _mutableListOfLiveData = mutableListOf<LiveData<String>>()
    val mutableListOfLiveData : MutableList<LiveData<String>> = _mutableListOfLiveData

    private var currentJob : Job? = null

    init {

    }
}