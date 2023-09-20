package com.filipemorgado.myexpenses.ui.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.filipemorgado.myexpenses.domain.interactor.TransactionsInteractor
import com.filipemorgado.myexpenses.domain.usecase.AddTransactionUseCase
import com.filipemorgado.myexpenses.domain.usecase.DeleteTransactionUseCase
import com.filipemorgado.myexpenses.domain.usecase.UpdateTransactionUseCase

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(
    /*private val addTransactionUseCase: AddTransactionUseCase,
    private val deleteTransactionUseCase: DeleteTransactionUseCase,
    private val updateTransactionUseCase: UpdateTransactionUseCase,*/
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
           /* addTransactionUseCase,
            deleteTransactionUseCase,
            updateTransactionUseCase*/
        ) as T
    }
}
