package com.gangofdevelopers.currencyrates.app.di

import com.gangofdevelopers.currencyrates.app.MainActivity
import com.gangofdevelopers.currencyrates.presentation.di.FragmentBindingModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBindingModule::class])
    abstract fun bindMainActivity(): MainActivity
}