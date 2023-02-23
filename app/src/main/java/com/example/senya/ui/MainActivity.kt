package com.example.senya.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.senya.R
import com.example.senya.arch.AttractionsViewModel
import com.example.senya.data.Attraction
import com.example.senya.databinding.ActivityMainBinding
import com.example.senya.utils.MoshiParse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var navController: NavController
        private set

    private val rawAttractions by lazy { readRawAttractions() }

    val attractionList: List<Attraction> by lazy { parseRawAttractions(rawAttractions) }

    private val viewModel: AttractionsViewModel by viewModels()

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

    private fun parseRawAttractions(rawAttractions: String): List<Attraction> {
        return MoshiParse().parseRawAttractions(rawAttractions)
    }

}