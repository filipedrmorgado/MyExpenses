package com.filipemorgado.myexpenses

import android.app.Application
import com.filipemorgado.myexpenses.db.ExpensesDb
import com.filipemorgado.myexpenses.db.TransactionsDAO
import com.filipemorgado.myexpenses.domain.interactor.TransactionsInteractor
import com.filipemorgado.myexpenses.domain.repository.TransactionsRepository
import com.filipemorgado.myexpenses.domain.usecase.AddTransactionUseCase
import com.filipemorgado.myexpenses.domain.usecase.DeleteTransactionUseCase
import com.filipemorgado.myexpenses.domain.usecase.UpdateTransactionUseCase
import com.filipemorgado.myexpenses.ui.fragments.budget.BudgetViewModelFactory
import com.filipemorgado.myexpenses.ui.fragments.transaction.TransactionViewModelFactory
import com.filipemorgado.myexpenses.ui.fragments.home.HomeViewModelFactory
import com.filipemorgado.myexpenses.ui.fragments.invoice.ExpenseViewModelFactory
import com.filipemorgado.myexpenses.ui.fragments.notifications.ProfileViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class KeepCountApplication: Application(), KodeinAware {


    // Kotlin Dependency Injection
    override val kodein = Kodein.lazy {
        // Instance() will automatically finds the correct class to constructor
        import(androidXModule(this@KeepCountApplication))
        // Usecases
        bind<AddTransactionUseCase>() with singleton { AddTransactionUseCase(instance()) }
        bind<DeleteTransactionUseCase>() with singleton { DeleteTransactionUseCase(instance()) }
        bind<UpdateTransactionUseCase>() with singleton { UpdateTransactionUseCase(instance()) }

        // ViewModelFactories
        bind() from provider { TransactionViewModelFactory() }
        bind() from provider { BudgetViewModelFactory() }
        bind() from provider { ProfileViewModelFactory() }
        bind() from provider { HomeViewModelFactory(instance(), instance(), instance()) }
        bind() from provider { ExpenseViewModelFactory() }

        // Database related instances
        bind<ExpensesDb>() with singleton { ExpensesDb.getDatabase(this@KeepCountApplication) }
        bind<TransactionsDAO>() with singleton { instance<ExpensesDb>().getTransactionsDAO() }

        // Repositories
        // Provide TransactionsRepository with TransactionsDAO
        bind() from singleton { TransactionsRepository(instance()) }

        // Interactors
        bind() from singleton { TransactionsInteractor(instance()) }



        // bind() from singleton { NetworkConnectionInterceptor(instance()) }
    }
}