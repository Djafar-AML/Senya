package com.example.senya.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.senya.R
import com.example.senya.data.Attraction
import com.example.senya.data.AttractionsResponse
import com.example.senya.databinding.ActivityMainBinding
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var navController: NavController
        private set

    val attractionList: List<Attraction> by lazy {
        parseRawAttractions(rawAttractions)
    }

    private val rawAttractions by lazy {
        readRawAttractions()
    }
    private val moshi by lazy {
        Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    }

    private val moshiAdapter by lazy {
        initMoshiAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavHostFragment()
    }

    private fun setupNavHostFragment() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun readRawAttractions() =
        resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }

    private fun initMoshiAdapter(): JsonAdapter<AttractionsResponse>? {
        val type = AttractionsResponse::class.java
        return moshi.adapter(type)
    }


    private fun parseRawAttractions(rawAttractions: String): List<Attraction> {
        return moshiAdapter?.fromJson(rawAttractions)?.attractions ?: emptyList()
    }

}