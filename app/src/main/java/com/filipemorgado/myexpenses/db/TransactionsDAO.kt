package com.filipemorgado.myexpenses.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionsDAO {

    @Insert
    suspend fun addTransaction(transaction: TransactionEntity)

    @Delete
    suspend fun deleteTransaction(transaction: TransactionEntity)

    @Update
    suspend fun updateTransaction(transaction: TransactionEntity)

    @Query("SELECT * FROM transaction_table ORDER BY date ASC")
    fun getTransactionByDate(): Flow<List<TransactionEntity>>
}
