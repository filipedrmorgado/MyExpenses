package com.filipemorgado.myexpenses.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.filipemorgado.myexpenses.databinding.FragmentTransactionBinding

class TransactionFragment : Fragment() {

    private lateinit var binding: FragmentTransactionBinding
    private val dashBoardViewModel: TransactionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTransactionBinding.inflate(inflater, container, false)
        dashBoardViewModel.text.observe(viewLifecycleOwner) {
            binding.textTransaction.text = it
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}