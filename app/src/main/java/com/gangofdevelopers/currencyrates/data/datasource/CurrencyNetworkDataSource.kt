package com.gangofdevelopers.currencyrates.data.datasource

import com.gangofdevelopers.currencyrates.common.util.Either
import com.gangofdevelopers.currencyrates.common.exception.Failure
import com.gangofdevelopers.currencyrates.data.model.CurrencyDto

interface CurrencyNetworkDataSource {
    suspend fun getCurrency(base: String): Either<Failure, CurrencyDto>
}