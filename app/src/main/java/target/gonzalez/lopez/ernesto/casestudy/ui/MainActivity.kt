package target.gonzalez.lopez.ernesto.casestudy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import target.gonzalez.lopez.ernesto.casestudy.R
import target.gonzalez.lopez.ernesto.casestudy.databinding.ActivityMainBinding
import target.gonzalez.lopez.ernesto.casestudy.ui.views.payment.PaymentFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    lateinit var mainActivityViewBinding: ActivityMainBinding
    lateinit var appNavBarConfig: AppBarConfiguration

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        mainActivityViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityViewBinding.root)
        configAppBarForNavigation()
        setupActionBarWithNavController(getNavController(), appNavBarConfig)

    }
    override fun onSupportNavigateUp(): Boolean {
        return getNavController().navigateUp() || super.onSupportNavigateUp()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    private fun configAppBarForNavigation() {
        appNavBarConfig = AppBarConfiguration(
            setOf(R.id.productsListFragment,
            R.id.productDetailsFragment)
        )
        setSupportActionBar(mainActivityViewBinding.appNavBar)
    }
    private fun getNavController():NavController {
        return supportFragmentManager.findFragmentById(
            R.id.fragment_container_view) as NavHostController
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.credit_card -> {
                PaymentFragment().show(supportFragmentManager, "CreditCardValidation")
                true
            }
            else -> false
        }
    }
}