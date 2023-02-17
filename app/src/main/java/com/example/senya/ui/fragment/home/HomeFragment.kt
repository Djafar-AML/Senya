package com.example.senya.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.senya.databinding.FragmentHomeBinding
import com.example.senya.ui.fragment.BaseFragment


class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding by lazy { _binding!! }

    private val homeAdapter by lazy { initHomeFragmentAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeAdapter.setData(emptyList())

    }

    private fun initHomeFragmentAdapter(): HomeFragmentAdapter {

        val homeAdapter = HomeFragmentAdapter(::attractionOnClickCallback)
        binding.homeRecyclerView.apply {
            setHasFixedSize(true)
            adapter = homeAdapter
        }
        return homeAdapter
    }

    private fun attractionOnClickCallback() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}