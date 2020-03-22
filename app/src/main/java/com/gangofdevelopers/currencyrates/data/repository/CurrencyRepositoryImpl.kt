package com.gangofdevelopers.currencyrates.data.repository

import com.gangofdevelopers.currencyrates.common.util.Either
import com.gangofdevelopers.currencyrates.common.exception.Failure
import com.gangofdevelopers.currencyrates.common.util.NetworkManager
import com.gangofdevelopers.currencyrates.data.datasource.CurrencyNetworkDataSource
import com.gangofdevelopers.currencyrates.data.mapper.CurrencyMapper
import com.gangofdevelopers.currencyrates.domain.model.CurrencyDomainModel
import com.gangofdevelopers.currencyrates.domain.repository.CurrencyRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val networkManager: NetworkManager,
    private val currencyNetworkDataSource: CurrencyNetworkDataSource,
    private val mapper: CurrencyMapper
) : CurrencyRepository {

    override suspend fun getCurrency(base: String): Either<Failure, CurrencyDomainModel> {
        if (!networkManager.isNetworkAvailable()) {
            delay(NO_INTERNET_DELAY)
            return Either.Error(Failure.NetworkFailure.NetworkConnection)
        }

        return when (val result = currencyNetworkDataSource.getCurrency(base)) {
            is Either.Error -> result
            is Either.Success -> Either.Success(mapper.map(result.data))
        }
    }

    companion object {
        const val NO_INTERNET_DELAY = 800L
    }
}