package com.example.senya.ui.fragment.details.epoxy.model

import com.example.senya.R
import com.example.senya.databinding.ModelDescriptionBinding
import com.example.senya.ui.fragment.epoxy.ViewBindingKotlinModel

data class DescriptionEpoxyModel(private val description: String) :
    ViewBindingKotlinModel<ModelDescriptionBinding>(R.layout.model_description) {

    override fun ModelDescriptionBinding.bind() {
        descriptionTextView.text = description
    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }

}
