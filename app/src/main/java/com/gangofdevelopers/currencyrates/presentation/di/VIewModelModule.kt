package com.gangofdevelopers.currencyrates.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gangofdevelopers.currencyrates.presentation.common.viewmodel.ViewModelFactory
import com.gangofdevelopers.currencyrates.presentation.screens.currency.flow.CurrencyPuller
import com.gangofdevelopers.currencyrates.presentation.screens.currency.flow.CurrencyPullerImpl
import com.gangofdevelopers.currencyrates.presentation.screens.currency.mapper.CurrencyModelMapper
import com.gangofdevelopers.currencyrates.presentation.screens.currency.mapper.CurrencyModelMapperImpl
import com.gangofdevelopers.currencyrates.presentation.screens.currency.viewmodel.CurrencyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModule {
    @Singleton
    @Binds
    @IntoMap
    @ViewModelKey(CurrencyViewModel::class)
    abstract fun bindsCurrencyViewModel(currencyViewModel: CurrencyViewModel): ViewModel

    @Singleton
    @Binds
    abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Singleton
    @Binds
    abstract fun bindsCurrencyDataMapper(mapper: CurrencyModelMapperImpl): CurrencyModelMapper

    @Singleton
    @Binds
    abstract fun bindsCurrencyDataPuller(dataPuller: CurrencyPullerImpl): CurrencyPuller
}