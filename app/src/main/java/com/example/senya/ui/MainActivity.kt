package com.example.senya.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.senya.R
import com.example.senya.data.Attraction
import com.example.senya.databinding.ActivityMainBinding
import com.example.senya.utils.MoshiParse

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var navController: NavController
        private set

    private val appBarConfiguration by lazy { initAppBarConfiguration() }


    val attractionList: List<Attraction> by lazy {
        parseRawAttractions(rawAttractions)
    }

    private val rawAttractions by lazy {
        readRawAttractions()
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

    private fun initAppBarConfiguration(): AppBarConfiguration {
        return AppBarConfiguration(navController.graph)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun readRawAttractions() =
        resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }

    private fun parseRawAttractions(rawAttractions: String): List<Attraction> {
        return MoshiParse().parseRawAttractions(rawAttractions)
    }

}