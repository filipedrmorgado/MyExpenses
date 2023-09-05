package com.filipemorgado.myexpenses.domain.usecase

import com.filipemorgado.myexpenses.domain.interactor.TransactionsInteractor
import com.filipemorgado.myexpenses.model.Transaction

class UpdateTransactionUseCase(
    private val transactionInteractor: TransactionsInteractor,
) {

    suspend operator fun invoke(transaction: Transaction) {
        transactionInteractor.updateTransaction(transaction)
    }
}


