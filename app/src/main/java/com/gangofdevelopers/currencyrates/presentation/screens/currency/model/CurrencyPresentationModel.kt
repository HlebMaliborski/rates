package com.gangofdevelopers.currencyrates.presentation.screens.currency.model

data class CurrencyPresentationModel(
    val baseCurrency: String,
    var baseCurrencyValue: Double = 1.0,
    val items: List<CurrencyItem>
)