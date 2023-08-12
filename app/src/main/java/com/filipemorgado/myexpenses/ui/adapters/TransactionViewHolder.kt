package com.filipemorgado.myexpenses.ui.adapters

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.filipemorgado.myexpenses.R
import com.filipemorgado.myexpenses.databinding.RecentTransactionsItemBinding
import com.filipemorgado.myexpenses.model.DateRange
import com.filipemorgado.myexpenses.model.Transaction
import com.filipemorgado.myexpenses.utilities.TAG_TRANSACTION_VIEW_HOLDER

class TransactionViewHolder(private val binding: RecentTransactionsItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(transaction: Transaction, dateRange: DateRange) {
        Log.i(TAG_TRANSACTION_VIEW_HOLDER, "bind: dateRange=$dateRange")

        //todo check how to do it later on
        binding.icTransaction.setImageResource(R.drawable.ic_transaction)
        binding.tvTransactionCategory.text = transaction.category
        binding.tvTransactionDescription.text = transaction.description
        binding.tvAmount.text = transaction.amount.toString()
        //todo format as expected
        binding.tvTransactionTime.text = transaction.date.toString()
    }

    /**
     * Setups date format to be shown, depending on the dateRage
     */
    private fun setupDateFormat() {

    }
}