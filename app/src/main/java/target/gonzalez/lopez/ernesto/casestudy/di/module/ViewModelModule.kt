package target.gonzalez.lopez.ernesto.casestudy.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import target.gonzalez.lopez.ernesto.casestudy.di.viewmodelkey.ViewModelKey
import target.gonzalez.lopez.ernesto.casestudy.factory.ViewModelFactory
import target.gonzalez.lopez.ernesto.casestudy.viewmodel.DealsViewModel

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DealsViewModel::class)
    abstract fun bindDealViewModel(dealsViewModel: DealsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}