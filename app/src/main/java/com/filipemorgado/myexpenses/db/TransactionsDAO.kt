package com.filipemorgado.myexpenses.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.filipemorgado.myexpenses.model.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionsDAO {

    @Insert
    suspend fun addTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

    @Update
    suspend fun updateTransaction(transaction: Transaction)

    @Query("SELECT * FROM transaction_table ORDER BY date ASC")
    suspend fun getTransactionByDate(): Flow<List<Transaction>>
}
