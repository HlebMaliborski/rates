package com.gangofdevelopers.currencyrates.presentation.common.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gangofdevelopers.currencyrates.common.exception.Failure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

abstract class BaseViewModel : ViewModel() {

    private val viewModelJob = Job()

    protected val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    protected val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    var failure = MutableLiveData<Failure>()

    protected fun handleFailure(failure: Failure) {
        this.failure.postValue(failure)
    }

    open fun clearResources() {
        uiScope.cancel()
        ioScope.cancel()
    }
}