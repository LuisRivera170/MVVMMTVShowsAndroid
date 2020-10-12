package com.lara.mvvmmtvshows.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lara.mvvmmtvshows.R
import com.lara.mvvmmtvshows.databinding.ItemContainerTvShowBinding
import com.lara.mvvmmtvshows.models.TVShow

class TVShowsAdapter(private val tvShows: List<TVShow>): RecyclerView.Adapter<TVShowsAdapter.TVShowsViewHolder>() {

    private lateinit var layoutInflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowsViewHolder {
        if (!this::layoutInflater.isInitialized) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val itemShowBinding: ItemContainerTvShowBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_container_tv_show, parent,false)
        return TVShowsViewHolder(itemShowBinding)
    }

    override fun onBindViewHolder(holder: TVShowsViewHolder, position: Int) {
        holder.bindTVShow(tvShows[position])
    }

    override fun getItemCount() = tvShows.size

    class TVShowsViewHolder(private val itemContainerTvShowBinding: ItemContainerTvShowBinding): RecyclerView.ViewHolder(itemContainerTvShowBinding.root) {

        fun bindTVShow(tvShow: TVShow) {
            itemContainerTvShowBinding.tvShow = tvShow
            itemContainerTvShowBinding.executePendingBindings()
        }

    }

}