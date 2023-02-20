package com.example.senya.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        initViews(attraction)

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
            numberOfFactsTextView.setOnClickListener {
                // todo
            }
//            root.setOnClickListener {
//                attractionOnClickCallback(this@AttractionDetailFragment.attraction.id)
//            }

        }
    }

    private fun findAttractionById(attractionId: String): Attraction {
        return attractions.find { it.id == attractionId } ?: Attraction()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}