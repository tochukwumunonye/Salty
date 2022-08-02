package com.example.salty.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.salty.presentation.albums.AlbumFragment
import com.example.salty.presentation.saved.SavedFragment
import com.example.salty.presentation.search.SearchAdapter
import com.example.salty.presentation.search.SearchFragment
import javax.inject.Inject


class TestFragmentFactory @Inject constructor(
   // private val searchAdapter: SearchAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
           AlbumFragment::class.java.name -> AlbumFragment()
            SavedFragment::class.java.name -> SavedFragment()
            SearchFragment::class.java.name -> SearchFragment()
            else -> super.instantiate(classLoader, className)
        }
    }

}