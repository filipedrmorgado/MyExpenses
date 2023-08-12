package com.filipemorgado.myexpenses.utilities

import com.filipemorgado.myexpenses.model.Transaction
import com.filipemorgado.myexpenses.model.TransactionType
import java.util.Calendar

object TransactionsUtils {

    // Example of possible transactions to be added by the user
    fun getMockedTransactions(): List<Transaction> {
        val today = Calendar.getInstance().time
        val oneDayAgo = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -1) }.time
        val oneMonthAgo = Calendar.getInstance().apply { add(Calendar.MONTH, - 1) }.time
        val sixMonthAgo = Calendar.getInstance().apply { add(Calendar.MONTH, - 5) }.time

        return listOf(
            Transaction(TransactionType.EXPENSE, "Food", "Francesinha", 10L, today),
            Transaction(TransactionType.EXPENSE, "Subscription", "Netflix", 5L, today),
            Transaction(TransactionType.INCOME, "Salary", "Base Salary", 5000L, oneDayAgo),
            Transaction(TransactionType.INCOME, "Transference", "Ines", 50L, oneDayAgo),
            Transaction(TransactionType.EXPENSE, "Food", "Burger", 15L, oneMonthAgo),
            Transaction(TransactionType.EXPENSE, "Shopping", "T-Shirt", 20L, sixMonthAgo),
        )
    }
}