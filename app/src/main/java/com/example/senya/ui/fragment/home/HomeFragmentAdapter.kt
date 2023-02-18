package com.example.senya.ui.fragment.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.senya.R
import com.example.senya.data.Attraction
import com.example.senya.databinding.ViewHolderAttractionBinding
import com.example.senya.utils.loadByCoil
import com.example.senya.utils.loadImageByPicasso

class HomeFragmentAdapter(
    private val attractionOnClickCallback: (String) -> Unit
) : RecyclerView.Adapter<HomeFragmentAdapter.AttractionViewHolder>() {

    private lateinit var ctx: Context
    private val attractions = ArrayList<Attraction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {

        if (!::ctx.isInitialized)
            ctx = parent.context

        val layoutInflater = LayoutInflater.from(ctx)

        val binding = ViewHolderAttractionBinding.inflate(layoutInflater, parent, false)

        return AttractionViewHolder(binding)

    }

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        holder.onBind(attractions[position], attractionOnClickCallback)
    }

    override fun getItemCount() = attractions.size

    fun setData(attractions: List<Attraction>) {
        this.attractions.clear()
        this.attractions.addAll(attractions)
        notifyDataSetChanged()
    }

    inner class AttractionViewHolder(private val binding: ViewHolderAttractionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(attraction: Attraction, attractionOnClickCallback: (String) -> Unit) {

            binding.apply {

                titleTextView.text = attraction.title
                monthsToVisitTextView.text = attraction.months_to_visit
                val imageUrl = attraction.image_urls[0]
                headerImageView.loadByCoil(imageUrl)
                root.setOnClickListener {
                    attractionOnClickCallback(attraction.id)
                }

            }
        }
    }

}