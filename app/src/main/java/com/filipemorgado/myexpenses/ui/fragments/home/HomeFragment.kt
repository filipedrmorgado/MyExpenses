package com.filipemorgado.myexpenses.ui.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.filipemorgado.myexpenses.R
import com.filipemorgado.myexpenses.databinding.FragmentHomeBinding
import com.filipemorgado.myexpenses.databinding.TransactionsTimeSelectorBinding
import com.filipemorgado.myexpenses.model.DateRange
import com.filipemorgado.myexpenses.model.Transaction
import com.filipemorgado.myexpenses.ui.adapters.TransactionAdapter
import com.filipemorgado.myexpenses.utilities.TAG_HOME_FRAGMENT
import com.filipemorgado.myexpenses.utilities.TransactionsUtils

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var daySelectorBinding: TransactionsTimeSelectorBinding
    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var navController : NavController

    // Transaction Recycler
    private lateinit var transactionAdapter: TransactionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        daySelectorBinding = TransactionsTimeSelectorBinding.bind(binding.homeBottom.daySelector.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setupObservers()
        setupRecyclerView()
        setRadioButtonSelectors()
        setupDropDown()
    }


    private fun setupObservers() {
        binding.homeTop.llIncomeBox.setOnClickListener {
            navigateToNewScreen(R.id.action_navigation_home_to_navigation_expense)
        }
    }

    private fun navigateToNewScreen(navigationAction: Int) {
        navController.navigate(navigationAction)
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.homeBottom.rvTransactions.layoutManager = layoutManager
        //todo remove this mock, retrieve from user stored data. If none, replace with text in screen.
        val transactions: List<Transaction> = TransactionsUtils.getMockedTransactions()
        val initialDateRange = setupInitialRadioFilter()
        Log.i(TAG_HOME_FRAGMENT, "updateDateRange: transactions=$transactions, initialDateRange=$initialDateRange")

        transactionAdapter = TransactionAdapter(transactions, initialDateRange)
        binding.homeBottom.rvTransactions.adapter = transactionAdapter
    }

    /**
     * Sets the dropdown custom text view
     */
    private fun setupDropDown() {
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.dropdown_items,
            R.layout.spinner_layout //todo Custom spinner, to be traded by a recycler view
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.homeTop.topToolbar.spinnerDropdown.adapter = adapter
    }

    /**
     * Set Radio Button text and font family based on if it is selected or not
     */
    private fun setRadioButtonSelectors() {
        // Set a listener to the RadioGroup to detect selection changes
        daySelectorBinding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            // Set the font family for all RadioButtons
            setFontFamilyToRadioButtons()
            // Determine the selected date range based on the checked radio button
            val selectedDateRange = when (checkedId) {
                R.id.radioToday -> DateRange.TODAY
                R.id.radioWeek -> DateRange.WEEK
                R.id.radioMonth -> DateRange.MONTH
                R.id.radioYear -> DateRange.YEAR
                else -> DateRange.TODAY
            }
            Log.i(TAG_HOME_FRAGMENT, "setRadioButtonSelectors: selectedDateRange=$selectedDateRange")

            // Update date Range
            homeViewModel.updateDateRange(selectedDateRange)
            // Filter transactions based on the selected date range
            transactionAdapter.filterTransactionsByDateRange(selectedDateRange)
        }
        // Sets initial values
        setFontFamilyToRadioButtons()
    }

    /**
     * Sets filter based on the default selected RadioButton (if any)
     */
    private fun setupInitialRadioFilter(): DateRange {
        val defaultSelectedRadioButton = daySelectorBinding.radioGroup.checkedRadioButtonId
        val initialDateRange = when (defaultSelectedRadioButton) {
            R.id.radioToday -> DateRange.TODAY
            R.id.radioWeek -> DateRange.WEEK
            R.id.radioMonth -> DateRange.MONTH
            R.id.radioYear -> DateRange.YEAR
            else -> DateRange.TODAY
        }
        homeViewModel.updateDateRange(initialDateRange)
        return initialDateRange
    }

    /**
     * Set the initial font family based on the default selected RadioButton (if any)
     */
    private fun setFontFamilyToRadioButtons() {
        for (i in 0 until daySelectorBinding.radioGroup.childCount) {
            val radioButton = daySelectorBinding.radioGroup.getChildAt(i) as? RadioButton
            val font = if (radioButton?.isChecked == true) {
                resources.getFont(R.font.inter_bold) // Replace with the font resource for the selected state
            } else {
                resources.getFont(R.font.inter_medium) // Replace with the font resource for the unselected state
            }
            radioButton?.typeface = font
        }
    }
}