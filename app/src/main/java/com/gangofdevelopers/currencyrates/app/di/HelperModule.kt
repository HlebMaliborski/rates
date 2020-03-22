package com.gangofdevelopers.currencyrates.app.di

import com.gangofdevelopers.currencyrates.common.util.NetworkManager
import com.gangofdevelopers.currencyrates.common.util.NetworkManagerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class HelperModule {
    @Binds
    @Singleton
    abstract fun provideNetworkManager(network: NetworkManagerImpl): NetworkManager
}

