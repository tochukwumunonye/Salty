package com.example.salty.presentation.saved

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.salty.databinding.SavedAlbumItemBinding
import com.example.salty.domain.model.SearchAlbum

class SavedAdapter(
    private val onDeleteItemClick: (SearchAlbum) -> Unit,
) : ListAdapter<SearchAlbum, SavedAdapter.SavedAdapterViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedAdapterViewHolder {
        val binding =
            SavedAlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedAdapterViewHolder, position: Int) {
        val currentAlbum = getItem(position)

        if(currentAlbum != null) {
            holder.bind(currentAlbum)
        }
    }

    inner class SavedAdapterViewHolder(private val binding: SavedAlbumItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {

                binding.productDelete.setOnClickListener {
                    val position =   adapterPosition
                    if(position != RecyclerView.NO_POSITION) {
                        val album = getItem(position)
                        onDeleteItemClick(album)
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
