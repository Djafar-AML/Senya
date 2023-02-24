package com.example.senya.ui.fragment.home

import com.airbnb.epoxy.EpoxyController
import com.example.senya.data.Attraction
import com.example.senya.ui.fragment.epoxy.model.AttractionEpoxyModel
import com.example.senya.ui.fragment.epoxy.model.HeaderEpoxyModel
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

        val filteredAttractions = filterAttractionList()

        if (filteredAttractions.isNotEmpty()) {

            HeaderEpoxyModel("Recently Viewed").id("header_1").addTo(this)

            filteredAttractions.forEach { attraction ->
                AttractionEpoxyModel(attraction, attractionOnClickCallback)
                    .id(attraction.id)
                    .addTo(this)
            }
        }

        HeaderEpoxyModel("All Attractions").id("header_2").addTo(this)

        attractions.forEach { attraction ->
            AttractionEpoxyModel(attraction, attractionOnClickCallback)
                .id(attraction.id)
                .addTo(this)
        }

    }

    private fun filterAttractionList(): List<Attraction> {
        return attractions.filter {
            it.title.startsWith("s", true) || it.title.startsWith("D", true)
        }
    }

}