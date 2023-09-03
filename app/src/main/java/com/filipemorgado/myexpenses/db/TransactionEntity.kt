package com.filipemorgado.myexpenses.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.filipemorgado.myexpenses.model.TransactionType
import java.util.Date

@Entity(tableName = "transaction_table")
class TransactionEntity(
    val transactionType: TransactionType,
    val category: String,
    val description: String,
    val amount: Long,
    val date: Date,
) {
    @PrimaryKey(autoGenerate = true)
    var transactionId = 0
}