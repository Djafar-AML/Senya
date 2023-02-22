package com.example.senya.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.navArgs
import com.example.senya.R
import com.example.senya.data.Attraction
import com.example.senya.databinding.FragmentAttractionDetailBinding
import com.example.senya.utils.loadByCoil

class AttractionDetailFragment : BaseFragment() {

    private var _binding: FragmentAttractionDetailBinding? = null
    private val binding by lazy { _binding!! }

    private val safeArgs: AttractionDetailFragmentArgs by navArgs()

    private val attraction by lazy {
        findAttractionById(safeArgs.attractionId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAttractionDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBarTitle()
        initViews(attraction)
        setupClickListeners()

    }

    private fun setActionBarTitle() {
        binding.actionBarTitleTextView.text =
            getString(R.string.attraction_action_bar_title, attraction.title)
    }

    private fun initViews(_attraction: Attraction) {

        binding.apply {

            titleTextView.text = _attraction.title
            descriptionTextView.text = _attraction.description
            monthsToVisitTextView.text = _attraction.months_to_visit
            val imageUrl = _attraction.image_urls[0]
            headerImageView.loadByCoil(imageUrl)
            numberOfFactsTextView.text =
                getString(R.string.attraction_facts_count, _attraction.facts.size)

        }
    }

    private fun setupClickListeners() {

        binding.apply {

            backImageView.setOnClickListener { popBackStack() }

            mapImageView.setOnClickListener { openInGoogleMap(attraction) }

            numberOfFactsTextView.setOnClickListener {
                val factsString = attractionFacts(attraction.facts)
                factAlertDialog(factsString)
            }

        }

    }


    private fun openInGoogleMap(_attraction: Attraction) {

        val uri =
            Uri.parse("geo:${_attraction.location.latitude},${attraction.location.longitude}")
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)

    }


    private fun findAttractionById(attractionId: String): Attraction {
        return attractions.find { it.id == attractionId } ?: Attraction()
    }

    private fun attractionFacts(facts: List<String>): String {

        val stringBuilder = StringBuilder("")

        facts.forEach {
            stringBuilder.append("\u2022 $it")
            stringBuilder.append("\n\n")
        }

        val stringifiedFacts = stringBuilder.toString()
        return stringifiedFacts.substring(0, stringifiedFacts.lastIndexOf("\n\n"))

    }

    private fun factAlertDialog(factsString: String) {

        AlertDialog.Builder(requireContext())
            .setTitle("${attraction.title} facts")
            .setMessage(factsString)
            .setPositiveButton("Ok") { dialog, which ->
                dialog.dismiss()
            }
            .show()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}