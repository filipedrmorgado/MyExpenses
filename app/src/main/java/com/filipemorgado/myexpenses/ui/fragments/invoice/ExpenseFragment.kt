package com.filipemorgado.myexpenses.ui.fragments.invoice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.filipemorgado.myexpenses.databinding.FragmentExpenseBinding

class ExpenseFragment : Fragment() {

    private lateinit var binding: FragmentExpenseBinding
    private val expenseViewModel: ExpenseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExpenseBinding.inflate(inflater, container, false)
        return binding.root
    }
}