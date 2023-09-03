package com.filipemorgado.myexpenses.domain.interactor

import android.util.Log
import com.filipemorgado.myexpenses.domain.repository.TransactionsRepository
import com.filipemorgado.myexpenses.model.Transaction
import com.filipemorgado.myexpenses.utilities.TAG_TRANSACTIONS_REPOSITORY
import com.filipemorgado.myexpenses.utilities.mapToLib

class TransactionsInteractor(
    private val transactionsRepository: TransactionsRepository,
) {

    suspend fun addTransaction(transaction: Transaction) {
        Log.d(TAG_TRANSACTIONS_REPOSITORY, "insertTransaction: transaction=$transaction")
        transactionsRepository.addTransaction(transaction)
    }

    suspend fun deleteTransaction(transaction: Transaction) {
        Log.d(TAG_TRANSACTIONS_REPOSITORY, "deleteTransaction: transaction=$transaction")
        transactionsRepository.deleteTransaction(transaction)
    }

    suspend fun updateTransaction(transaction: Transaction) {
        Log.d(TAG_TRANSACTIONS_REPOSITORY, "updateTransaction: transaction=$transaction")
        transactionsRepository.updateTransaction(transaction)
    }
}