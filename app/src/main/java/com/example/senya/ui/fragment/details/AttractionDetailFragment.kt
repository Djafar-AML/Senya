package com.example.senya.ui.fragment.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.senya.R
import com.example.senya.data.Attraction
import com.example.senya.databinding.FragmentAttractionDetailBinding
import com.example.senya.ui.fragment.BaseFragment
import com.example.senya.ui.fragment.details.epoxy.ContentEpoxyController
import com.example.senya.ui.fragment.details.epoxy.HeaderImageEpoxyController

class AttractionDetailFragment : BaseFragment() {

    private var _binding: FragmentAttractionDetailBinding? = null
    private val binding by lazy { _binding!! }
    private lateinit var contentEpoxyController: ContentEpoxyController

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
        setupObservers()

    }

    private fun setupObservers() {
        activityViewModel.selectedAttractionLiveData.observe(viewLifecycleOwner) { attraction ->
            attraction?.let {
                setActionBarTitle(attraction.title)
                initViews(attraction)
                initContentEpoxyController(attraction)
                setupEpoxyLayoutManager()
                initContentEpoxy()
                initImagesRecyclerview(attraction.image_urls)
                setupClickListeners(attraction)
            }
        }
    }


    private fun setActionBarTitle(title: String) {
        binding.actionBarTitleTextView.text =
            getString(R.string.attraction_action_bar_title, title)
    }

    private fun initViews(_attraction: Attraction) {

        binding.apply {
            titleTextView.text = _attraction.title
        }
    }

    private fun initContentEpoxyController(_attraction: Attraction) {
        contentEpoxyController = ContentEpoxyController(_attraction)
    }

    private fun setupEpoxyLayoutManager() {

        var isGridMode: Boolean =
            binding.contentEpoxyRecyclerview.layoutManager is GridLayoutManager

        contentEpoxyController.isGridMode = isGridMode

        contentEpoxyController.onChangeLayoutCallback = {

            if (isGridMode) {
                binding.contentEpoxyRecyclerview.layoutManager =
                    LinearLayoutManager(requireContext())
            } else {
                binding.contentEpoxyRecyclerview.layoutManager =
                    GridLayoutManager(requireContext(), 2)
            }

            isGridMode = !isGridMode
            contentEpoxyController.isGridMode = isGridMode
            contentEpoxyController.requestModelBuild()
        }

    }

    private fun initContentEpoxy() {
        binding.contentEpoxyRecyclerview.setControllerAndBuildModels(
            contentEpoxyController
        )
    }

    private fun initImagesRecyclerview(imageUrls: List<String>) {

        binding.apply {
            headerEpoxyRecyclerview.setControllerAndBuildModels(HeaderImageEpoxyController(imageUrls))
            LinearSnapHelper().attachToRecyclerView(headerEpoxyRecyclerview)
            indicator.attachToRecyclerView(headerEpoxyRecyclerview)
        }

    }

    private fun setupClickListeners(attraction: Attraction) {

        binding.apply {

            backImageView.setOnClickListener { popBackStack() }

            mapImageView.setOnClickListener { openInGoogleMap(attraction) }

        }

    }


    private fun openInGoogleMap(_attraction: Attraction) {

        val uri =
            Uri.parse("geo:${_attraction.location.latitude},${_attraction.location.longitude}")
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)

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

    private fun factAlertDialog(_attraction: Attraction, factsString: String) {

        AlertDialog.Builder(requireContext())
            .setTitle("${_attraction.title} facts")
            .setMessage(factsString)
            .setPositiveButton("Ok") { dialog, which ->
                dialog.dismiss()
            }
            .show()

    }

    override fun onDestroyView() {
        activityViewModel.cancelCoroutineScope()
        super.onDestroyView()
        activityViewModel.resetSelectedAttractionLiveData()
        _binding = null
    }

}