package com.filipemorgado.myexpenses.ui.budget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.filipemorgado.myexpenses.databinding.FragmentBudgetBinding

class BudgetFragment : Fragment() {

    private lateinit var binding: FragmentBudgetBinding
    private val dashBoardViewModel: BudgetViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBudgetBinding.inflate(inflater, container, false)
        dashBoardViewModel.text.observe(viewLifecycleOwner) {
            binding.textBudget.text = it
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}