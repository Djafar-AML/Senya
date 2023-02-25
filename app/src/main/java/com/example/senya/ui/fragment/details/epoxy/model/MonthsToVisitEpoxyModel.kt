package com.example.senya.ui.fragment.details.epoxy.model

import com.example.senya.R
import com.example.senya.databinding.ModelMonthsToVisitBinding
import com.example.senya.ui.fragment.epoxy.ViewBindingKotlinModel

data class MonthsToVisitEpoxyModel(private val monthsToVisit: String) :
    ViewBindingKotlinModel<ModelMonthsToVisitBinding>(R.layout.model_months_to_visit) {

    override fun ModelMonthsToVisitBinding.bind() {
        monthsToVisitTextView.text = monthsToVisit
    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}
