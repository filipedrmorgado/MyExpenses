package com.filipemorgado.myexpenses.ui.adapters

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.filipemorgado.myexpenses.R
import com.filipemorgado.myexpenses.databinding.RecentTransactionsItemBinding
import com.filipemorgado.myexpenses.model.DateRange
import com.filipemorgado.myexpenses.model.Transaction
import com.filipemorgado.myexpenses.model.TransactionType
import com.filipemorgado.myexpenses.utilities.DateUtils
import com.filipemorgado.myexpenses.utilities.TAG_TRANSACTION_VIEW_HOLDER
import java.util.Date

class TransactionViewHolder(private val binding: RecentTransactionsItemBinding,private val context: Context) : RecyclerView.ViewHolder(binding.root) {

    fun bind(transaction: Transaction, dateRange: DateRange) {
        Log.i(TAG_TRANSACTION_VIEW_HOLDER, "bind: dateRange=$dateRange")

        //todo check how to do it later on
        binding.icTransaction.setImageResource(R.drawable.ic_transaction)
        binding.tvTransactionCategory.text = transaction.category
        binding.tvTransactionDescription.text = transaction.description
        setTextAmount(transaction)
        //todo format as expected
        setupDateFormat(transaction.date, dateRange)
    }

    private fun setTextAmount(transaction: Transaction) {
        if(transaction.transactionType == TransactionType.INCOME) {
            binding.tvAmount.text = context.getString(R.string.positive_transaction_amount, transaction.amount.toString())
            binding.tvAmount.setTextColor(context.getColor(R.color.positiveBalance))
        } else {
            binding.tvAmount.text = context.getString(R.string.negative_transaction_amount, transaction.amount.toString())
            binding.tvAmount.setTextColor(context.getColor(R.color.negativeBalance))
        }
    }

    /**
     * Setups date format to be shown, depending on the dateRage
     */
    private fun setupDateFormat(dateToBeDisplayed: Date, dateRange: DateRange) {
        val formattedDate = when (dateRange) {
            DateRange.TODAY -> DateUtils.formatDate(dateToBeDisplayed, "hh:mm a")
            DateRange.WEEK, DateRange.MONTH, DateRange.YEAR ->
                DateUtils.formatDate(dateToBeDisplayed, "dd/MMM hh:mm a")
        }
        binding.tvTransactionTime.text = formattedDate
    }
}