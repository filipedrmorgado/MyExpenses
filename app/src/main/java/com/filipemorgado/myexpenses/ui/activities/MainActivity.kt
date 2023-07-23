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
import com.filipemorgado.myexpenses.ui.dashboard.DashboardViewModel
import com.filipemorgado.myexpenses.ui.dashboard.DashboardViewModelFactory
import com.filipemorgado.myexpenses.ui.home.HomeViewModel
import com.filipemorgado.myexpenses.ui.home.HomeViewModelFactory
import com.filipemorgado.myexpenses.ui.notifications.NotificationsViewModel
import com.filipemorgado.myexpenses.ui.notifications.NotificationsViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private lateinit var binding: ActivityMainBinding

    // ViewModel factories
    private val dashboardFactory: DashboardViewModelFactory by instance()
    private val notificationsFactory: NotificationsViewModelFactory by instance()
    private val homeFactory: HomeViewModelFactory by instance()

    // ViewModel initialization
    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var notificationsViewModel: NotificationsViewModel
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
        dashboardViewModel = ViewModelProvider(this, dashboardFactory)[DashboardViewModel::class.java]
        notificationsViewModel = ViewModelProvider(this, notificationsFactory)[NotificationsViewModel::class.java]
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
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

}