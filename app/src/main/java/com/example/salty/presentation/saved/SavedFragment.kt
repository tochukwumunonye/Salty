package com.example.salty.presentation.saved

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salty.R
import com.example.salty.databinding.FragmentSavedAlbumBinding
import com.example.salty.domain.model.SearchAlbum
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SavedFragment : Fragment(R.layout.fragment_saved_album) {

    private val viewModel: SavedViewModel by viewModels()

    private var _binding: FragmentSavedAlbumBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSavedAlbumBinding.bind(view)

        val savedAdapter = SavedAdapter(
            onDeleteItemClick = {gee->
                Log.d("viv", gee.toString())
                viewModel.deleteDateFromDatabase(gee)

            }
        )

        binding.apply {
            productVisitedRecyclerView.apply {
                adapter = savedAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                itemAnimator?.changeDuration = 0
            }
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { viewState ->
                    when(viewState) {
                        is SavedViewState.Success -> {
                            savedAdapter.submitList(viewState.albums)
                        }
                        is SavedViewState.Error -> {
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


    /**

    private fun subscribeToObservers() { **/

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
