package com.example.salty.presentation.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.salty.databinding.AlbumItemBinding
import com.example.salty.domain.model.Album

class AlbumAdapter: ListAdapter<Album, AlbumAdapter.AlbumViewHolder>(DIFF_CALLBACK){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding =
            AlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val currentProduct = getItem(position)

        if(currentProduct != null) {
            holder.bind(currentProduct)
        }
    }
    inner class AlbumViewHolder(private val binding: AlbumItemBinding) :
        RecyclerView.ViewHolder(binding.root) {



        /** init{
        binding.apply{
        binding.favouriteImageView.setOnClickListener {
        val position = bindingAdapterPosition
        if(position != RecyclerView.NO_POSITION) {
        val collection = getItem(position)
        if(collection.isLiked) {
        val unlikeCollection = collection.copy(isLiked = false)
        listener.onItemClick(unlikeCollection)
        } else {
        val likeCollection = collection.copy(isLiked = true)
        listener.onItemClick(likeCollection)
        }
        }
        }
        }
        }

         **/
        fun bind(item: Album) {
            Glide.with(binding.root.context)
                .load(item.albumImage)
                .centerCrop()
                .into(binding.productThumbnail)

            binding.productPrice.text = item.albumArtist
            binding.productTitle.text = item.albumName
        }
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Album>() {
            override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
                return oldItem.albumName == newItem.albumName
            }
            override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
                return oldItem.albumArtist == newItem.albumArtist
            }
        }
    }
}

/**
 *
class ProductListAdapter(
private val listener :OnItemClickListener
) : ListAdapter<Product, ProductListAdapter.ProductListViewHolder>(DIFF_CALLBACK) {

 */