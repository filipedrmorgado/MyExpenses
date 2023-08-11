package com.filipemorgado.myexpenses.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.filipemorgado.myexpenses.R
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
        daySelectorBinding = TransactionsTimeSelectorBinding.bind(binding.homeBottom.daySelector.root)
        setRadioButtonSelectors()
        setupDropDown()
        return binding.root
    }

    /**
     * Sets the dropdown custom text view
     */
    private fun setupDropDown() {
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.dropdown_items,
            R.layout.spinner_layout // Use the custom layout for the spinner item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.homeTop.topToolbar.spinnerDropdown.adapter = adapter
    }

    /**
     * Set Radio Button text and font family based on if it is selected or not
     */
    private fun setRadioButtonSelectors() {
        // Set a listener to the RadioGroup to detect selection changes
        daySelectorBinding.radioGroup.setOnCheckedChangeListener { _, _ ->
            // Set the font family for all RadioButtons
            setFontFamilyToRadioButtons()
        }

        // Set the initial font family based on the default selected RadioButton (if any)
        setFontFamilyToRadioButtons()
    }

    private fun setFontFamilyToRadioButtons() {
        for (i in 0 until daySelectorBinding.radioGroup.childCount) {
            val radioButton = daySelectorBinding.radioGroup.getChildAt(i) as? RadioButton
            val font = if (radioButton?.isChecked == true) {
                resources.getFont(R.font.inter_bold) // Replace with the font resource for the selected state
            } else {
                resources.getFont(R.font.inter_medium) // Replace with the font resource for the selected state
            }
            radioButton?.typeface = font
        }
    }
}