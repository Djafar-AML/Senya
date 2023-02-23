package com.example.senya.arch

import com.example.senya.R
import com.example.senya.application
import com.example.senya.data.Attraction
import com.example.senya.utils.MoshiParse


class AttractionsRepo {


    private val rawAttractions by lazy { readRawAttractions() }

    val attractionList: List<Attraction> by lazy { parseRawAttractions(rawAttractions) }

    private fun parseRawAttractions(rawAttractions: String): List<Attraction> {
        return MoshiParse().parseRawAttractions(rawAttractions)
    }

    private fun readRawAttractions() =
        application.resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }

}