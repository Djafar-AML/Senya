package com.example.senya.ui.fragment.details.epoxy.model

import com.example.senya.R
import com.example.senya.databinding.ModelFactBinding
import com.example.senya.ui.fragment.epoxy.ViewBindingKotlinModel

data class FactEpoxyModel(private val fact: String) :
    ViewBindingKotlinModel<ModelFactBinding>(R.layout.model_fact) {

    override fun ModelFactBinding.bind() {
        factTextView.text = fact
    }

}
