package com.example.senya.ui.fragment.epoxy.model

import com.example.senya.R
import com.example.senya.databinding.EpoxyModelHeaderBinding
import com.example.senya.ui.fragment.epoxy.ViewBindingKotlinModel

data class HeaderEpoxyModel(private val headerText: String) :
    ViewBindingKotlinModel<EpoxyModelHeaderBinding>(
        R.layout.epoxy_model_header
    ) {
    override fun EpoxyModelHeaderBinding.bind() {
        headerTextView.text = headerText
    }
}
