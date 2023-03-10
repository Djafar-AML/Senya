package com.example.senya.utils

import com.example.senya.data.Attraction
import com.example.senya.data.AttractionsResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MoshiParse {

    private val moshi by lazy {
        Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    }

    private val jsonAdapter by lazy {
        initJsonAdapter()
    }

    private fun initJsonAdapter(): JsonAdapter<AttractionsResponse>? {
        val type = AttractionsResponse::class.java
        return moshi.adapter(type)
    }

    fun parseRawAttractions(rawAttractions: String): List<Attraction> {
        return jsonAdapter?.fromJson(rawAttractions)?.attractions ?: emptyList()
    }

}