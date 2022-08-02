package com.example.salty.application

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.salty.presentation.albums.AlbumFragment
import javax.inject.Inject


class FragmentFactory @Inject constructor(): FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            AlbumFragment::class.java.name -> AlbumFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}
