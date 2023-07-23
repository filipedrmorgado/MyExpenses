package com.filipemorgado.myexpenses

import android.app.Application
import com.filipemorgado.myexpenses.ui.dashboard.DashboardViewModelFactory
import com.filipemorgado.myexpenses.ui.home.HomeViewModelFactory
import com.filipemorgado.myexpenses.ui.notifications.NotificationsViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider


class KeepCountApplication: Application(), KodeinAware {

    // Kotlin Dependency Injection
    override val kodein = Kodein.lazy {
        import(androidXModule(this@KeepCountApplication))

        // Instance() automatically finds the correct class to constructor
        // bind() from singleton { NetworkConnectionInterceptor(instance()) }

        // bind() from singleton { Repository(instance(),instance()) }

        // ViewModelFactories
        bind() from provider { DashboardViewModelFactory() }
        bind() from provider { NotificationsViewModelFactory() }
        bind() from provider { HomeViewModelFactory() }
    }
}