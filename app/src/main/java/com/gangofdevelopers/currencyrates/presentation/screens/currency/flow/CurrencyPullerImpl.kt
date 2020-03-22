package com.gangofdevelopers.currencyrates.presentation.screens.currency.flow

import com.gangofdevelopers.currencyrates.common.exception.Failure
import com.gangofdevelopers.currencyrates.domain.model.CurrencyDomainModel
import com.gangofdevelopers.currencyrates.domain.usecase.GetCurrencyUseCase
import com.gangofdevelopers.currencyrates.domain.usecase.Params
import com.gangofdevelopers.currencyrates.presentation.common.flow.Puller
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CurrencyPullerImpl @Inject constructor(
    private val getCurrencyUseCase: GetCurrencyUseCase
) : CurrencyPuller {
    private val dispatcher = Dispatchers.IO
    private val ioScope = CoroutineScope(dispatcher)
    override var defaultCurrency: String = "EUR"
        set(value) {
            field = if (value.isEmpty()) {
                "EUR"
            } else {
                value
            }
        }

    override fun pull(delayTime: Long, fnL: (Failure) -> Any): Flow<CurrencyDomainModel> {
        return channelFlow {
            while (!isClosedForSend) {
                delay(delayTime)
                getCurrencyUseCase(
                    ioScope,
                    Params(defaultCurrency)
                ) {
                    it.either(fnL, { model ->
                        launch {
                            send(model)
                        }
                    })
                }
            }
        }.flowOn(dispatcher)
    }

    override fun close() {
        ioScope.cancel()
        dispatcher.cancel()
    }
}

interface CurrencyPuller : Puller<CurrencyDomainModel> {
    var defaultCurrency: String
}