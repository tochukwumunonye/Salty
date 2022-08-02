package com.example.salty.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.salty.databinding.SearchAlbumItemBinding
import com.example.salty.domain.model.Album
import com.example.salty.domain.model.SearchAlbum

class SearchAdapter(
    private val onItemClick: (SearchAlbum) -> Unit,
) : ListAdapter<SearchAlbum, SearchAdapter.SearchAdapterViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapterViewHolder {
        val binding =
            SearchAlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchAdapterViewHolder, position: Int) {
        val currentAlbum = getItem(position)

        if(currentAlbum != null) {
            holder.bind(currentAlbum)
        }
    }

    inner class SearchAdapterViewHolder(private val binding: SearchAlbumItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {

                    binding.productSave.setOnClickListener {
                        val position =   adapterPosition
                        if(position != RecyclerView.NO_POSITION) {
                            val album = getItem(position)
                            onItemClick(album)
                        }
                }
            }
        }

        fun bind(item: SearchAlbum) {
           Glide.with(binding.root.context)
                .load(item.artworkUrl)
                .centerCrop()
                .into(binding.productThumbnail)

            binding.productTitle.text = item.artistName
            binding.productPrice.text = item.trackName
        }
    }
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SearchAlbum>() {
            override fun areItemsTheSame(oldItem: SearchAlbum, newItem: SearchAlbum): Boolean {
                return oldItem.artistName == newItem.artistName
            }
            override fun areContentsTheSame(oldItem: SearchAlbum, newItem: SearchAlbum): Boolean {
                return oldItem == newItem
            }
        }
    }
}
/*
 */
