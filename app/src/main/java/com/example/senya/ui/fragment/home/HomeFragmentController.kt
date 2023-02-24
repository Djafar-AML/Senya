package com.example.senya.ui.fragment.home

import com.airbnb.epoxy.EpoxyController
import com.example.senya.data.Attraction
import com.example.senya.ui.fragment.epoxy.model.AttractionEpoxyModel
import com.example.senya.ui.fragment.epoxy.model.LoadingEpoxyModel

class HomeFragmentController(
    private val attractionOnClickCallback: (String) -> Unit
) : EpoxyController() {

    var isLoading = false
        set(value) {
            field = value
            if (field)
                requestModelBuild()
        }

    var attractions = ArrayList<Attraction>()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }


    override fun buildModels() {

        if (isLoading) {
            LoadingEpoxyModel().id("loading_state").addTo(this)
            return
        }

        if (attractions.isEmpty()) {
            // todo show empty state
            return
        }

        attractions.forEach { attraction ->
            AttractionEpoxyModel(attraction, attractionOnClickCallback)
                .id(attraction.id)
                .addTo(this)
        }

    }

}