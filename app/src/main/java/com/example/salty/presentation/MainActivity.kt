package com.example.salty.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.salty.R
import com.example.salty.application.FragmentFactory
import com.example.salty.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: FragmentFactory

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
        binding = ActivityMainBinding.inflate(layoutInflater)
       // setContentView(R.layout.activity_main)
        setContentView(binding.root)

        binding.apply {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

            bottomNavigationView.apply {
                background = null
                setupWithNavController(navHostFragment.findNavController())
                //setOnNavigationItemReselectedListener { Unit }
                NavigationBarView.OnItemReselectedListener{Unit}
            }

        }
    }
}

/**
 *

binding.apply {

bottomNavigationView.apply {
background = null
menu.getItem(2).isEnabled = false
setupWithNavController(navHostFragment.findNavController())
setOnNavigationItemReselectedListener { Unit }
}

fabNewPost.setOnClickListener {
navHostFragment.findNavController().navigate(
R.id.globalActionToCreatePostFragment
)
}
}
}**/