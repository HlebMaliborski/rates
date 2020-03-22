package com.gangofdevelopers.currencyrates.app.di

import android.app.Application
import com.gangofdevelopers.currencyrates.app.App
import com.gangofdevelopers.currencyrates.common.api.di.ApiServiceModule
import com.gangofdevelopers.currencyrates.data.di.DataModule
import com.gangofdevelopers.currencyrates.domain.di.DomainModule
import com.gangofdevelopers.currencyrates.presentation.di.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

//In future nice to have separate scopes for screen/feature and so on.
//Right now I use make all object Singleton, just for test.
@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        DomainModule::class,
        ApiServiceModule::class,
        DataModule::class,
        ContextModule::class,
        HelperModule::class]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
    fun inject(application: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}