package com.example.senya.ui.fragment.epoxy.model

import com.example.senya.R
import com.example.senya.data.Attraction
import com.example.senya.databinding.ViewHolderAttractionBinding
import com.example.senya.ui.fragment.epoxy.ViewBindingKotlinModel
import com.example.senya.utils.loadByCoil

data class AttractionEpoxyModel(
    private val attraction: Attraction,
    private val attractionOnClickCallback: (String) -> Unit
) : ViewBindingKotlinModel<ViewHolderAttractionBinding>(R.layout.view_holder_attraction) {

    override fun ViewHolderAttractionBinding.bind() {

        titleTextView.text = attraction.title
        monthsToVisitTextView.text = attraction.months_to_visit
        val imageUrl = attraction.image_urls[0]
        headerImageView.loadByCoil(imageUrl)
        root.setOnClickListener {
            attractionOnClickCallback(attraction.id)
        }
    }
}
