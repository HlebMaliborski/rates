package com.gangofdevelopers.currencyrates.data.datasource

import com.gangofdevelopers.currencyrates.common.util.Either
import com.gangofdevelopers.currencyrates.common.api.ApiService
import com.gangofdevelopers.currencyrates.common.exception.Failure
import com.gangofdevelopers.currencyrates.data.common.BaseDataStore
import com.gangofdevelopers.currencyrates.data.common.runRequestTryCatch
import com.gangofdevelopers.currencyrates.data.model.CurrencyDto
import javax.inject.Inject

class CurrencyNetworkDataSourceImpl @Inject constructor(
    private val api: ApiService
) : BaseDataStore(), CurrencyNetworkDataSource {

    override suspend fun getCurrency(base: String): Either<Failure, CurrencyDto> =
        runRequestTryCatch {
            runRequest(api.getCurrency(base))
        }
}