package com.filipemorgado.myexpenses.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.filipemorgado.myexpenses.utilities.DateTypeConverter
import com.filipemorgado.myexpenses.utilities.TAG_DATABASE

@Database(entities = [TransactionEntity::class], version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class ExpensesDb: RoomDatabase() {

    abstract fun getTransactionsDAO(): TransactionsDAO

    companion object {
        @Volatile
        private var dbInstance: ExpensesDb? = null

        fun getDatabase(context: Context): ExpensesDb {
            Log.i(TAG_DATABASE, "getDatabase: dbInstance=$dbInstance")
            return dbInstance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    ExpensesDb::class.java,
                    "expenses_database"
                    ).build()
                dbInstance = instance
                Log.i(TAG_DATABASE, "getDatabase: new dbInstance=$dbInstance")
                return instance
            }
        }
    }

}