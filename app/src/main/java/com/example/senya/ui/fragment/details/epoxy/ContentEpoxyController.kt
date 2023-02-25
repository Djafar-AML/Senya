package com.example.senya.ui.fragment.details.epoxy

import com.airbnb.epoxy.EpoxyController
import com.example.senya.data.Attraction
import com.example.senya.ui.fragment.details.epoxy.model.DescriptionEpoxyModel
import com.example.senya.ui.fragment.details.epoxy.model.FactEpoxyModel
import com.example.senya.ui.fragment.details.epoxy.model.FactsHeaderEpoxyModel
import com.example.senya.ui.fragment.details.epoxy.model.MonthsToVisitEpoxyModel

class ContentEpoxyController(private val attraction: Attraction) : EpoxyController() {

    lateinit var onChangeLayoutCallback: () -> Unit
    var isGridMode: Boolean = false

    override fun buildModels() {

        DescriptionEpoxyModel(attraction.description).id("description").addTo(this)

        FactsHeaderEpoxyModel(
            "${attraction.facts.size} Facts",
            isGridMode,
            onChangeLayoutCallback
        ).id("factsHeader").addTo(this)

        attraction.facts.forEachIndexed { index, fact ->
            FactEpoxyModel(fact).id("fact_$index").addTo(this)
        }

        MonthsToVisitEpoxyModel(attraction.months_to_visit).id("monthsToVisit").addTo(this)

    }
}