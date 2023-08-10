package com.filipemorgado.myexpenses.model

import com.filipemorgado.myexpenses.utilities.EMPTY_STRING
import java.util.Date

/**
 * Transaction data class that will contain expenses and income registries
 */
data class Transaction(
    val transactionType: TransactionType,
    val category: String = EMPTY_STRING,
    val description: String = EMPTY_STRING,
    val amount: Long = 0L,
    val date: Date,
)
