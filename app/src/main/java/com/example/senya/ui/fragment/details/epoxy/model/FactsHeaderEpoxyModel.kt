package com.example.senya.ui.fragment.details.epoxy.model

import com.example.senya.R
import com.example.senya.databinding.ModelFactsHeaderBinding
import com.example.senya.ui.fragment.epoxy.ViewBindingKotlinModel

data class FactsHeaderEpoxyModel(
    private val factsHeader: String,
    private val isGridMode: Boolean,
    private val onChangeLayoutCallback: () -> Unit
) :
    ViewBindingKotlinModel<ModelFactsHeaderBinding>(R.layout.model_facts_header) {

    override fun ModelFactsHeaderBinding.bind() {

        factsTextView.text = factsHeader

        toggleImageView.setOnClickListener { onChangeLayoutCallback() }

        toggleImageView.setImageResource(if (isGridMode) R.drawable.ic_list_view_24 else R.drawable.ic_grid_view_24)
    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }

}
