package com.gangofdevelopers.currencyrates.presentation.di

import com.gangofdevelopers.currencyrates.presentation.screens.currency.view.CurrencyFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {
    @ContributesAndroidInjector
    abstract fun provideCurrencyFragment(): CurrencyFragment
}