package com.gangofdevelopers.currencyrates.presentation.common.flow

import com.gangofdevelopers.currencyrates.common.exception.Failure
import kotlinx.coroutines.flow.Flow

interface Puller<out T> {
    fun pull(delayTime: Long, fnL: (Failure) -> Any): Flow<T>
    fun close()
}