package com.filipemorgado.myexpenses.ui.fragments.invoice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.filipemorgado.myexpenses.R
import com.filipemorgado.myexpenses.databinding.FragmentExpenseBinding
import com.filipemorgado.myexpenses.model.TransactionType
import com.filipemorgado.myexpenses.utilities.TAG_EXPENSE_FRAGMENT

class ExpenseFragment : Fragment() {

    private lateinit var binding: FragmentExpenseBinding
    private val expenseViewModel: ExpenseViewModel by activityViewModels()
    private lateinit var transactionType: TransactionType
    private lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExpenseBinding.inflate(inflater, container, false)
        transactionType = arguments?.getSerializable("transactionType") as? TransactionType ?: TransactionType.INCOME
        setupView()
        setupObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setupView()
        setupObservers()
    }

    private fun setupObservers() {
        binding.topExpenseToolbar.ivToolbarBackIcon.setOnClickListener {
            navController.navigateUp()
        }
    }

    /**
     * Setup background color and custom toolbar text
     */
    private fun setupView() {
        when(transactionType) {
            TransactionType.EXPENSE -> {
                binding.topExpenseToolbar.tvToolbarExpenseTitle.text = context?.getString(R.string.expense)
                val backgroundColor = context?.getColor(R.color.negativeBalance)
                Log.i(TAG_EXPENSE_FRAGMENT, "setupView: Expense | backgroundColor=$backgroundColor")
                backgroundColor?.let {
                    binding.transactionLayout.setBackgroundColor(it)
                    binding.topExpenseToolbar.toolbarExpense.setBackgroundColor(it)
                }
            }
            TransactionType.INCOME -> {
                binding.topExpenseToolbar.tvToolbarExpenseTitle.text = context?.getString(R.string.income)
                val backgroundColor = context?.getColor(R.color.positiveBalance)
                Log.i(TAG_EXPENSE_FRAGMENT, "setupView: Income | backgroundColor=$backgroundColor")
                backgroundColor?.let {
                    binding.transactionLayout.setBackgroundColor(it)
                    binding.topExpenseToolbar.toolbarExpense.setBackgroundColor(it)
                }
            }
        }

        binding.expenseDetails.spinnerWallet.apply {
            //todo set the items later from the user wallet list
            selectItemByIndex(0) // selected a default item
            lifecycleOwner = this@ExpenseFragment
        }
    }
}