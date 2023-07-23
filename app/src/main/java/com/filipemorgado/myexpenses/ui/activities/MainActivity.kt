package com.filipemorgado.myexpenses.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.filipemorgado.myexpenses.R
import com.filipemorgado.myexpenses.databinding.ActivityMainBinding
import com.filipemorgado.myexpenses.ui.budget.BudgetViewModel
import com.filipemorgado.myexpenses.ui.budget.BudgetViewModelFactory
import com.filipemorgado.myexpenses.ui.transaction.TransactionViewModel
import com.filipemorgado.myexpenses.ui.transaction.TransactionViewModelFactory
import com.filipemorgado.myexpenses.ui.home.HomeViewModel
import com.filipemorgado.myexpenses.ui.home.HomeViewModelFactory
import com.filipemorgado.myexpenses.ui.notifications.ProfileViewModel
import com.filipemorgado.myexpenses.ui.notifications.ProfileViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private lateinit var binding: ActivityMainBinding

    // ViewModel factories
    private val dashboardFactory: TransactionViewModelFactory by instance()
    private val budgetFactory: BudgetViewModelFactory by instance()
    private val profileFactory: ProfileViewModelFactory by instance()
    private val homeFactory: HomeViewModelFactory by instance()

    // ViewModel initialization
    private lateinit var transactionViewModel: TransactionViewModel
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var budgetViewModel: BudgetViewModel
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModelFactories()
        initNavigation()
    }

    /**
     * Initialization of viewmodel factories
     */
    private fun initViewModelFactories() {
        transactionViewModel = ViewModelProvider(this, dashboardFactory)[TransactionViewModel::class.java]
        profileViewModel = ViewModelProvider(this, profileFactory)[ProfileViewModel::class.java]
        budgetViewModel = ViewModelProvider(this, budgetFactory)[BudgetViewModel::class.java]
        homeViewModel = ViewModelProvider(this, homeFactory)[HomeViewModel::class.java]
    }

    /**
     * Initialization of navigation
     */
    private fun initNavigation() {
        // Navigation Initialization
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_transaction,
                R.id.navigation_budget,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

}