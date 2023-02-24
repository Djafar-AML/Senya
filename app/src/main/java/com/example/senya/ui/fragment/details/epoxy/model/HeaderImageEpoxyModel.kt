package com.example.senya.ui.fragment.details.epoxy.model

import com.example.senya.R
import com.example.senya.databinding.ModelHeaderImageBinding
import com.example.senya.ui.fragment.epoxy.ViewBindingKotlinModel
import com.example.senya.utils.loadByCoil

data class HeaderImageEpoxyModel(private val imageUrl: String) :
    ViewBindingKotlinModel<ModelHeaderImageBinding>(R.layout.model_header_image) {

    override fun ModelHeaderImageBinding.bind() {
        headerImageView.loadByCoil(imageUrl)
    }
}
