package com.example.senya.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.senya.R
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

        homeAdapter.setData(attractions)
        setActionBarTitle()
        setupAdapterScrollListener()

    }

    private fun setActionBarTitle() {
        binding.titleTextView.text = getString(R.string.home)
    }

    private fun setupAdapterScrollListener() {

        val state = mutableListOf(0)

        binding.homeRecyclerView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    state[0] = newState
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (dy > 0 && (state[0] == 0 || state[0] == 2)) {
                        binding.actionBar.visibility = View.GONE
                    } else {
                        if (dy < -20) {
                            binding.actionBar.visibility = View.VISIBLE
                        }
                    }
                }

            }
        )
    }

    private fun initHomeFragmentAdapter(): HomeFragmentAdapter {

        val homeAdapter = HomeFragmentAdapter(::attractionOnClickCallback)
        binding.homeRecyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(this.context, RecyclerView.VERTICAL))
            adapter = homeAdapter
        }
        return homeAdapter
    }

    private fun attractionOnClickCallback(attractionId: String) {
        val toDetailFragment =
            HomeFragmentDirections.actionHomeFragmentToAttractionDetailFragment(attractionId)
        navController.navigate(toDetailFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}