package com.example.senya.ui.fragment.details.epoxy

import com.airbnb.epoxy.EpoxyController
import com.example.senya.ui.fragment.details.epoxy.model.HeaderImageEpoxyModel

class HeaderImageEpoxyController(private val imageUrls: List<String>) : EpoxyController() {

    override fun buildModels() {

        if (imageUrls.isEmpty())
            return

        imageUrls.forEachIndexed { index, imageUrl ->
            HeaderImageEpoxyModel(imageUrl).id(index).addTo(this)
        }
    }

}