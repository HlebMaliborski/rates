package com.gangofdevelopers.currencyrates.presentation.screens.currency.mapper

import com.gangofdevelopers.currencyrates.R
import com.gangofdevelopers.currencyrates.common.mapper.MapperParams
import com.gangofdevelopers.currencyrates.common.mapper.MapperWithParams
import com.gangofdevelopers.currencyrates.domain.model.CurrencyDomainModel
import com.gangofdevelopers.currencyrates.presentation.screens.currency.model.CurrencyItem
import com.gangofdevelopers.currencyrates.presentation.screens.currency.model.CurrencyPresentationModel
import javax.inject.Inject

class CurrencyModelMapperImpl @Inject constructor() : CurrencyModelMapper {
    override fun map(
        data: CurrencyDomainModel,
        params: PresentationParams
    ): CurrencyPresentationModel {
        val items = mutableListOf<CurrencyItem>()
        data.items.forEach {
            val imgRes = when (it.title) {
                "SEK" -> R.drawable.ic_sec
                "CAD" -> R.drawable.ic_cad
                else -> R.drawable.ic_like
            }

            if (it.title != data.baseCurrency) {
                items.add(
                    CurrencyItem(
                        it.title,
                        it.subtitle,
                        String.format("%.2f", (it.value * params.value)).toDouble(),
                        imgRes
                    )
                )
            } else {
                items.add(
                    0,
                    CurrencyItem(
                        it.title,
                        it.subtitle,
                        String.format("%.2f", (params.value)).toDouble(),
                        imgRes,
                        true
                    )
                )
            }
        }

        return CurrencyPresentationModel(
            data.baseCurrency,
            String.format("%.2f", (params.value)).toDouble(),
            items
        )
    }
}

class PresentationParams(val value: Double) : MapperParams()
interface CurrencyModelMapper :
    MapperWithParams<CurrencyDomainModel, PresentationParams, CurrencyPresentationModel>