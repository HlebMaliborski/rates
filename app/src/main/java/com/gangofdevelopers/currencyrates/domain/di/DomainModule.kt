package com.gangofdevelopers.currencyrates.domain.di

import com.gangofdevelopers.currencyrates.domain.usecase.GetCurrencyUseCase
import com.gangofdevelopers.currencyrates.domain.usecase.GetCurrencyUseCaseImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DomainModule {
    @Singleton
    @Binds
    abstract fun bindsGetCurrencyUseCase(useCase: GetCurrencyUseCaseImpl): GetCurrencyUseCase
}
