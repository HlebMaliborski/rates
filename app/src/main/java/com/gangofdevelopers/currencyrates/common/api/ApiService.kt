package com.gangofdevelopers.currencyrates.common.api

import com.gangofdevelopers.currencyrates.data.model.CurrencyDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(ApiPaths.CURRENCY)
    suspend fun getCurrency(@Query("base") base: String): Response<CurrencyDto>
}