package com.filipemorgado.myexpenses.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.filipemorgado.myexpenses.R
import com.filipemorgado.myexpenses.databinding.RecentTransactionsItemBinding
import com.filipemorgado.myexpenses.model.DateRange
import com.filipemorgado.myexpenses.model.Transaction
import com.filipemorgado.myexpenses.utilities.DateUtils.isWithinFilter

class TransactionAdapter(private val allTransactions: List<Transaction>) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    private var filteredTransactions: List<Transaction> = allTransactions

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecentTransactionsItemBinding.inflate(inflater, parent, false)
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = filteredTransactions[position]
        holder.bind(transaction)
    }

    override fun getItemCount(): Int = filteredTransactions.size

    /**
     * Filters our transaction list based on TODAY, WEEK, MONTH and YEAR.
     */
    @SuppressLint("NotifyDataSetChanged")
    fun filterTransactionsByDateRange(dateRange: DateRange) {
        filteredTransactions = allTransactions.filter { transaction ->
            when (dateRange) {
                DateRange.TODAY -> transaction.date.isWithinFilter(DateRange.TODAY)
                DateRange.WEEK -> transaction.date.isWithinFilter(DateRange.WEEK)
                DateRange.MONTH -> transaction.date.isWithinFilter(DateRange.MONTH)
                DateRange.YEAR -> transaction.date.isWithinFilter(DateRange.YEAR)
            }
        }
        notifyDataSetChanged()
    }

    inner class TransactionViewHolder(private val binding: RecentTransactionsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            //todo check how to do it later on
            binding.icTransaction.setImageResource(R.drawable.ic_transaction)
            binding.tvTransactionCategory.text = transaction.category
            binding.tvTransactionDescription.text = transaction.description
            binding.tvAmount.text = transaction.amount.toString()
            //todo format as expected
            binding.tvTransactionTime.text = transaction.date.toString()
        }
    }
}
