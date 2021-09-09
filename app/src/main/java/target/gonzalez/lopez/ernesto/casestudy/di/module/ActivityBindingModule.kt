package target.gonzalez.lopez.ernesto.casestudy.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import target.gonzalez.lopez.ernesto.casestudy.ui.MainActivity
import target.gonzalez.lopez.ernesto.casestudy.di.scope.ActivityScope

@Module
abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}