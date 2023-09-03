package com.filipemorgado.myexpenses.domain.repository

import android.util.Log
import com.filipemorgado.myexpenses.db.TransactionsDAO
import com.filipemorgado.myexpenses.model.Transaction
import com.filipemorgado.myexpenses.utilities.TAG_TRANSACTIONS_REPOSITORY
import com.filipemorgado.myexpenses.utilities.mapToLib
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TransactionsRepository(
    private val transactionsDAO : TransactionsDAO,
) {

    // Obtains all the user transactions. Maps them from TransactionEntity to Transaction.
    private var userTransactions: Flow<List<Transaction>> = transactionsDAO.getTransactionByDate()
        .map { transactionEntities -> transactionEntities.map { it.mapToLib() } }

    suspend fun addTransaction(transaction: Transaction) {
        Log.d(TAG_TRANSACTIONS_REPOSITORY, "insertTransaction: transaction=$transaction")
        transactionsDAO.addTransaction(transaction.mapToLib())
    }

    suspend fun deleteTransaction(transaction: Transaction) {
        Log.d(TAG_TRANSACTIONS_REPOSITORY, "deleteTransaction: transaction=$transaction")
        transactionsDAO.deleteTransaction(transaction.mapToLib())
    }

    suspend fun updateTransaction(transaction: Transaction) {
        Log.d(TAG_TRANSACTIONS_REPOSITORY, "updateTransaction: transaction=$transaction")
        transactionsDAO.updateTransaction(transaction.mapToLib())
    }

}