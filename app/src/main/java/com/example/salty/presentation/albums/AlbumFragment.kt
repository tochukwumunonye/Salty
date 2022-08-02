package com.example.salty.presentation.albums

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salty.R
import com.example.salty.databinding.FragmentAlbumListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlbumFragment: Fragment(R.layout.fragment_album_list) {

    //val albumAdapter = AlbumAdapter()

    private val viewModel: AlbumViewModel by viewModels()

    private var _binding: FragmentAlbumListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentAlbumListBinding.bind(view)
        val albumAdapter = AlbumAdapter()

       /** albumAdapter.stateRestorationPolicy =
            RecyclerView.Adapter. StateRestorationPolicy.PREVENT_WHEN_EMPTY **/

      //  setUpRecyclerView()
        binding.apply {
            productVisitedRecyclerView.apply {
                adapter = albumAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                itemAnimator?.changeDuration = 0
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { viewState ->
                    when(viewState) {
                        is AlbumViewState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            albumAdapter.submitList(viewState.albums)
                        }
                        is AlbumViewState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is AlbumViewState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                viewState.error,
                                Toast.LENGTH_SHORT
                            ).show()
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
 *@AndroidEntryPoint
class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news),
MainActivity.OnBottomNavigationFragmentReselectedListener {

private val viewModel: BreakingNewsViewModel by viewModels()

private var currentBinding: FragmentBreakingNewsBinding? = null
private val binding get() = currentBinding!!

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
super.onViewCreated(view, savedInstanceState)

currentBinding = FragmentBreakingNewsBinding.bind(view)

val newsArticleAdapter = NewsArticleListAdapter(
onItemClick = { article ->
val uri = Uri.parse(article.url)
val intent = Intent(Intent.ACTION_VIEW, uri)
requireActivity().startActivity(intent)
},
onBookmarkClick = { article ->
viewModel.onBookmarkClick(article)
}
)

newsArticleAdapter.stateRestorationPolicy =
RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

binding.apply {
recyclerView.apply {
adapter = newsArticleAdapter
layoutManager = LinearLayoutManager(requireContext())
setHasFixedSize(true)
itemAnimator?.changeDuration = 0
}

viewLifecycleOwner.lifecycleScope.launchWhenStarted {
viewModel.breakingNews.collect {
val result = it ?: return@collect


 */