package com.gangofdevelopers.currencyrates.data.di

import com.gangofdevelopers.currencyrates.data.datasource.CurrencyNetworkDataSource
import com.gangofdevelopers.currencyrates.data.datasource.CurrencyNetworkDataSourceImpl
import com.gangofdevelopers.currencyrates.data.mapper.CurrencyMapper
import com.gangofdevelopers.currencyrates.data.mapper.CurrencyMapperImpl
import com.gangofdevelopers.currencyrates.data.repository.CurrencyRepositoryImpl
import com.gangofdevelopers.currencyrates.domain.repository.CurrencyRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun bindsCurrencyRepository(repository: CurrencyRepositoryImpl): CurrencyRepository

    @Singleton
    @Binds
    abstract fun bindsNetworkDataSource(dataSource: CurrencyNetworkDataSourceImpl): CurrencyNetworkDataSource

    @Singleton
    @Binds
    abstract fun bindsCurrencMapper(dataMapper: CurrencyMapperImpl): CurrencyMapper
}