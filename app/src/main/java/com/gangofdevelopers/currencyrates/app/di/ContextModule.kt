package com.gangofdevelopers.currencyrates.app.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ContextModule {
    @Singleton
    @Binds
    abstract fun bindsContext(application: Application): Context
}