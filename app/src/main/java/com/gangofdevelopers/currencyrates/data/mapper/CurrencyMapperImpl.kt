package com.gangofdevelopers.currencyrates.data.mapper

import com.gangofdevelopers.currencyrates.common.mapper.Mapper
import com.gangofdevelopers.currencyrates.data.model.CurrencyDto
import com.gangofdevelopers.currencyrates.domain.model.CurrencyDomainItem
import com.gangofdevelopers.currencyrates.domain.model.CurrencyDomainModel
import javax.inject.Inject

class CurrencyMapperImpl @Inject constructor() : CurrencyMapper {
    override fun map(data: CurrencyDto): CurrencyDomainModel {
        val list: MutableList<CurrencyDomainItem> = mutableListOf()
        list.add(CurrencyDomainItem("EUR", "EUR", data.rates.EUR))
        list.add(CurrencyDomainItem("AUD", "AUD", data.rates.AUD))
        list.add(CurrencyDomainItem("BGN", "BGN", data.rates.BGN))
        list.add(CurrencyDomainItem("BRL", "BRL", data.rates.BRL))
        list.add(CurrencyDomainItem("CAD", "CAD", data.rates.CAD))
        list.add(CurrencyDomainItem("CHF", "CHF", data.rates.CHF))
        list.add(CurrencyDomainItem("CNY", "CNY", data.rates.CNY))
        list.add(CurrencyDomainItem("CZK", "CZK", data.rates.CZK))
        list.add(CurrencyDomainItem("DKK", "DKK", data.rates.DKK))
        list.add(CurrencyDomainItem("GBP", "GBP", data.rates.GBP))
        list.add(CurrencyDomainItem("HKD", "HKD", data.rates.HKD))
        list.add(CurrencyDomainItem("HRK", "HRK", data.rates.HRK))
        list.add(CurrencyDomainItem("HUF", "HUF", data.rates.HUF))
        list.add(CurrencyDomainItem("IDR", "IDR", data.rates.IDR))
        list.add(CurrencyDomainItem("ILS", "ILS", data.rates.ILS))
        list.add(CurrencyDomainItem("INR", "INR", data.rates.INR))
        list.add(CurrencyDomainItem("ISK", "ISK", data.rates.ISK))
        list.add(CurrencyDomainItem("JPY", "JPY", data.rates.JPY))
        list.add(CurrencyDomainItem("KRW", "KRW", data.rates.KRW))
        list.add(CurrencyDomainItem("MXN", "MXN", data.rates.MXN))
        list.add(CurrencyDomainItem("MYR", "MYR", data.rates.MYR))
        list.add(CurrencyDomainItem("NOK", "NOK", data.rates.NOK))
        list.add(CurrencyDomainItem("NZD", "NZD", data.rates.NZD))
        list.add(CurrencyDomainItem("PHP", "PHP", data.rates.PHP))
        list.add(CurrencyDomainItem("PLN", "PLN", data.rates.PLN))
        list.add(CurrencyDomainItem("RON", "RON", data.rates.RON))
        list.add(CurrencyDomainItem("RUB", "RUB", data.rates.RUB))
        list.add(CurrencyDomainItem("SEK", "SEK", data.rates.SEK))
        list.add(CurrencyDomainItem("SGD", "SGD", data.rates.SGD))
        list.add(CurrencyDomainItem("THB", "THB", data.rates.THB))
        list.add(CurrencyDomainItem("USD", "USD", data.rates.USD))
        list.add(CurrencyDomainItem("ZAR", "ZAR", data.rates.ZAR))

        return CurrencyDomainModel(data.baseCurrency, list)
    }
}

interface CurrencyMapper : Mapper<CurrencyDto, CurrencyDomainModel>