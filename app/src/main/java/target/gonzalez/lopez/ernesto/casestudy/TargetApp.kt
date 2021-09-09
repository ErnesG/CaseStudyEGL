package target.gonzalez.lopez.ernesto.casestudy

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import target.gonzalez.lopez.ernesto.casestudy.di.DaggerAppComponent
import javax.inject.Inject

class TargetApp: Application(), HasAndroidInjector {
    /**
     * 09.03.21
     * ToDo Add injection sentence to dealItem fragment, and import the viewModel
     * send parameter from list fragment to itemFragment
     * */
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        DaggerAppComponent.create().inject(this)
        super.onCreate()
    }
}