package com.example.salty.presentation.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.salty.R
import com.example.salty.databinding.FragmentAlbumListBinding
import com.example.salty.databinding.FragmentSearchAlbumBinding
import com.example.salty.domain.model.SearchAlbum
import com.example.salty.presentation.albums.AlbumViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment @Inject constructor(
   // var searchAdapter: SearchAdapter,
) : Fragment(R.layout.fragment_search_album){

    lateinit var searchAdapter: SearchAdapter
    private val viewModel: SearchViewModel by viewModels()

    private var _binding: FragmentSearchAlbumBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding =  FragmentSearchAlbumBinding.bind(view)
        searchAdapter = SearchAdapter(
            onItemClick = {
                viewModel.save(it)
            }
        )
        /**  val newsArticleAdapter = NewsArticleListAdapter(
        onItemClick = { article ->
        val uri = Uri.parse(article.url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        requireActivity().startActivity(intent)
        },
        onBookmarkClick = { article ->
        viewModel.onBookmarkClick(article)
        }
        )**/

        binding.apply {
            searchAlbumRecyclerView.apply {
                adapter = searchAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                itemAnimator?.changeDuration = 0
            }
        }

        binding.searchButton.setOnClickListener {
            val text = binding.editText.text.toString()
            viewModel.search(text)
            Toast.makeText(
                requireContext(),
                "toast occurred",
                Toast.LENGTH_SHORT
            ).show()
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect {viewState->
                    when(viewState) {
                        is SearchViewState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            searchAdapter.submitList(viewState.albums)
                        }

                        is SearchViewState.Error -> {
                            binding.progressBar.visibility =  View.GONE
                            Toast.makeText(
                                requireContext(),
                                viewState.error,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is SearchViewState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

/**
}**/

/**
 *
 *
binding.searchButton.setOnClickListener {
val text = binding.editText.text.toString()
viewModel.search(text)
}

binding.visitButton.setOnClickListener {
val action = ProductListFragmentDirections.actionProductListFragmentToProductHistoryFragment()
findNavController().navigate(action)
}

viewLifecycleOwner.lifecycleScope.launch {**/