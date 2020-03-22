package com.gangofdevelopers.currencyrates.domain.repository

import com.gangofdevelopers.currencyrates.common.util.Either
import com.gangofdevelopers.currencyrates.common.exception.Failure
import com.gangofdevelopers.currencyrates.domain.model.CurrencyDomainModel

interface CurrencyRepository {
    suspend fun getCurrency(base: String): Either<Failure, CurrencyDomainModel>
}