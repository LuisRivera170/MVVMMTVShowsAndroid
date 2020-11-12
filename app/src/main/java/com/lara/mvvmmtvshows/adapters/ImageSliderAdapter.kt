package com.lara.mvvmmtvshows.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lara.mvvmmtvshows.R
import com.lara.mvvmmtvshows.databinding.ItemContainerSliderImageBinding

class ImageSliderAdapter(private val sliderImages: Array<String>): RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder>() {

    private lateinit var layoutInflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSliderViewHolder {
        if (!this::layoutInflater.isInitialized) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val itemContainerSliderImageBinding: ItemContainerSliderImageBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_container_slider_image, parent, false)
        return ImageSliderViewHolder(itemContainerSliderImageBinding)
    }

    override fun onBindViewHolder(holder: ImageSliderViewHolder, position: Int) {
        holder.bindSliderImage(sliderImages[position])
    }

    override fun getItemCount() = sliderImages.size

    class ImageSliderViewHolder(private val itemContainerSliderImageBinding: ItemContainerSliderImageBinding): RecyclerView.ViewHolder(itemContainerSliderImageBinding.root) {

        fun bindSliderImage(imageURL: String) {
            itemContainerSliderImageBinding.imageURL = imageURL
        }

    }

}