package com.filipemorgado.myexpenses.db

import androidx.room.Entity
import com.filipemorgado.myexpenses.model.TransactionType
import com.filipemorgado.myexpenses.utilities.EMPTY_STRING
import java.util.Date

@Entity(tableName = "transaction_table")
data class TransactionEntity(
    val transactionType: TransactionType,
    val category: String = EMPTY_STRING,
    val description: String = EMPTY_STRING,
    val amount: Long = 0L,
    val date: Date,
)