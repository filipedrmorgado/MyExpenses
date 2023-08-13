package com.filipemorgado.myexpenses.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.filipemorgado.myexpenses.R
import com.filipemorgado.myexpenses.databinding.ActivityMainBinding
import com.filipemorgado.myexpenses.ui.fragments.budget.BudgetViewModel
import com.filipemorgado.myexpenses.ui.fragments.budget.BudgetViewModelFactory
import com.filipemorgado.myexpenses.ui.fragments.transaction.TransactionViewModel
import com.filipemorgado.myexpenses.ui.fragments.transaction.TransactionViewModelFactory
import com.filipemorgado.myexpenses.ui.fragments.home.HomeViewModel
import com.filipemorgado.myexpenses.ui.fragments.home.HomeViewModelFactory
import com.filipemorgado.myexpenses.ui.fragments.notifications.ProfileViewModel
import com.filipemorgado.myexpenses.ui.fragments.notifications.ProfileViewModelFactory
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

    // Navigation
    private lateinit var navController: NavController
    private lateinit var destinationChangedListener: NavController.OnDestinationChangedListener

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
    //todo set a custom BottomNavigationView
    private fun initNavigation() {
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)
        // Set up OnDestinationChangedListener, we should hide navigation view when trying to go to fullscreen fragments
        destinationChangedListener =
            NavController.OnDestinationChangedListener { _, destination, _ ->
                if (destination.id == R.id.navigation_home || destination.id == R.id.navigation_budget
                    || destination.id == R.id.navigation_transaction || destination.id == R.id.navigation_profile) {
                    // Show the BottomNavigationView on the home screen
                    binding.navView.visibility = View.VISIBLE
                } else {
                    // Hide the BottomNavigationView on other screens
                    binding.navView.visibility = View.GONE
                }
            }
        navController.addOnDestinationChangedListener(destinationChangedListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Remove the listener when the activity is destroyed
        navController.removeOnDestinationChangedListener(destinationChangedListener)
    }
}