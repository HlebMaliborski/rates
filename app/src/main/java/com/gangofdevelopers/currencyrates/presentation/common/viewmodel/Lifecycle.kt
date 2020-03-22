package com.gangofdevelopers.currencyrates.presentation.common.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.gangofdevelopers.currencyrates.common.exception.Failure

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <L : LiveData<Failure>> LifecycleOwner.failure(liveData: L, body: (Failure?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <T : Any, L : LiveData<T>> LifecycleOwner.observeViewState(
    liveData: L,
    body: (T) -> Unit
) = liveData.observe(this, Observer { stateView ->
    stateView?.let {
        body(it)
    }
})