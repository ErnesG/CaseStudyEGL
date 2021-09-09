package target.gonzalez.lopez.ernesto.casestudy.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import target.gonzalez.lopez.ernesto.casestudy.TargetApp
import target.gonzalez.lopez.ernesto.casestudy.di.module.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        NetworkModule::class,
        ProductRepositoryModule::class,
        ViewModelModule::class,
        DealsFragmentsBinding::class
    ]
)
interface AppComponent:AndroidInjector<TargetApp>