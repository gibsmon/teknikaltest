package com.example.danstest.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.danstest.R
import com.example.danstest.base.BaseActivity
import com.example.danstest.databinding.ActivityMainBinding
import com.example.danstest.ui.home.MainHome
import org.kodein.di.instance

class MainActivity : BaseActivity() {

    private val factory: MainActivityViewModelFactory by instance()
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        mainAction()
    }

    private fun mainAction() {
        viewModel.mainActivityListener = object : MainActivityListener {
            override fun intentToHome() {
                val intent = Intent(applicationContext,MainHome::class.java)
                startActivity(intent)
            }
        }
    }

}