package com.gangofdevelopers.currencyrates.domain.usecase

import com.gangofdevelopers.currencyrates.domain.common.UseCase
import com.gangofdevelopers.currencyrates.domain.model.CurrencyDomainModel

abstract class GetCurrencyUseCase : UseCase<CurrencyDomainModel, Params>()

/**
 * @param baseCurrency, currency type
 */
data class Params(val baseCurrency: String)