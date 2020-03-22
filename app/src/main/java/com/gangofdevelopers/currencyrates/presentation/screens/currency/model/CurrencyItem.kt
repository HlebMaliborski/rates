package com.gangofdevelopers.currencyrates.presentation.screens.currency.model

import androidx.annotation.DrawableRes

data class CurrencyItem(
    val title: String,
    val subtitle: String,
    var value: Double,
    @DrawableRes val imgRes: Int,
    val isResponder: Boolean = false
)