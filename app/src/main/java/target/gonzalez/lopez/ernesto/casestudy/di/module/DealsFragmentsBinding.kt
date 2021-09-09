package target.gonzalez.lopez.ernesto.casestudy.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import target.gonzalez.lopez.ernesto.casestudy.ui.views.DealItemFragment
import target.gonzalez.lopez.ernesto.casestudy.ui.views.DealListFragment
import target.gonzalez.lopez.ernesto.casestudy.ui.views.payment.PaymentFragment

@Module
abstract class DealsFragmentsBinding {

    @ContributesAndroidInjector
    abstract fun contributeDealListFragment(): DealListFragment

    @ContributesAndroidInjector
    abstract fun contributeDealItemFragment(): DealItemFragment

    @ContributesAndroidInjector
    abstract fun contributePaymentFragment(): PaymentFragment

}