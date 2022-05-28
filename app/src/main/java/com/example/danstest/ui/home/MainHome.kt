package com.example.danstest.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.danstest.R
import com.example.danstest.base.BaseActivity
import com.example.danstest.databinding.ActivityMainBinding
import com.example.danstest.databinding.ActivityMainHomeBinding
import org.kodein.di.instance
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainHome : BaseActivity() {

    private val factory: MainHomeViewModelFactory by instance()
    private lateinit var binding: ActivityMainHomeBinding
    private lateinit var viewModel: MainHomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_home)
        viewModel = ViewModelProvider(this, factory).get(MainHomeViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val navView: BottomNavigationView = binding.bottomNavigation

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_account, R.id.navigation_dashboard
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


}