package com.filipemorgado.myexpenses.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.filipemorgado.myexpenses.databinding.FragmentHomeBinding
import com.filipemorgado.myexpenses.databinding.TransactionsTimeSelectorBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var daySelectorBinding: TransactionsTimeSelectorBinding
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        daySelectorBinding = TransactionsTimeSelectorBinding.bind(binding.daySelector.root)


        setRadioButtonSelectors()

        return binding.root
    }

    private fun setRadioButtonSelectors() {

    }



    override fun onDestroyView() {
        super.onDestroyView()
    }
}