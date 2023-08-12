package com.filipemorgado.myexpenses.ui.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.filipemorgado.myexpenses.databinding.RecentTransactionsItemBinding
import com.filipemorgado.myexpenses.model.DateRange
import com.filipemorgado.myexpenses.model.Transaction
import com.filipemorgado.myexpenses.utilities.DateUtils.isWithinFilter
import com.filipemorgado.myexpenses.utilities.TAG_TRANSACTION_ADAPTER

class TransactionAdapter(
    private val allTransactions: List<Transaction>,
    initialDateRange: DateRange,
): RecyclerView.Adapter<TransactionViewHolder>() {

    private var filteredTransactions: List<Transaction> = filteredTransactions(initialDateRange)
    private var dateRange: DateRange = initialDateRange

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecentTransactionsItemBinding.inflate(inflater, parent, false)
        return TransactionViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = filteredTransactions[position]
        holder.bind(transaction, dateRange)
    }

    override fun getItemCount(): Int = filteredTransactions.size

    /**
     * Filters our transaction list based on TODAY, WEEK, MONTH and YEAR.
     */
    @SuppressLint("NotifyDataSetChanged")
    fun filterTransactionsByDateRange(dateRange: DateRange) {
        Log.i(TAG_TRANSACTION_ADAPTER, "filterTransactionsByDateRange: dateRange=$dateRange, filteredTransactions=$filteredTransactions")
        // Update the date range
        this.dateRange = dateRange
        filteredTransactions = filteredTransactions(dateRange)
        notifyDataSetChanged()
    }

    /**
     * Returns a list of transactions based on TODAY, WEEK, MONTH and YEAR.
     */
    private fun filteredTransactions(dateRange: DateRange) = allTransactions.filter { transaction ->
        when (dateRange) {
            DateRange.TODAY -> transaction.date.isWithinFilter(DateRange.TODAY)
            DateRange.WEEK -> transaction.date.isWithinFilter(DateRange.WEEK)
            DateRange.MONTH -> transaction.date.isWithinFilter(DateRange.MONTH)
            DateRange.YEAR -> transaction.date.isWithinFilter(DateRange.YEAR)
        }
    }

}
