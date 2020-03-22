package com.gangofdevelopers.currencyrates.domain.usecase

import com.gangofdevelopers.currencyrates.common.util.Either
import com.gangofdevelopers.currencyrates.common.exception.Failure
import com.gangofdevelopers.currencyrates.domain.model.CurrencyDomainModel
import com.gangofdevelopers.currencyrates.domain.repository.CurrencyRepository
import javax.inject.Inject

class GetCurrencyUseCaseImpl @Inject constructor(
    private val repository: CurrencyRepository
) : GetCurrencyUseCase() {

    override suspend fun run(params: Params): Either<Failure, CurrencyDomainModel> {
        return when (val result = repository.getCurrency(params.baseCurrency)) {
            is Either.Error -> result
            is Either.Success -> Either.Success(result.data)
        }
    }
}