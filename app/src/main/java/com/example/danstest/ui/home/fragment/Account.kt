package com.example.danstest.ui.home.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.danstest.R
import com.example.danstest.base.BaseFragment
import com.example.danstest.databinding.FragmentAccountBinding
import com.example.danstest.ui.detail_position.DetailRecruitment
import com.example.danstest.ui.home.MainHomeViewModel
import com.example.danstest.ui.home.MainHomeViewModelFactory
import com.example.danstest.ui.login.MainActivity
import com.google.gson.Gson
import org.kodein.di.instance

class Account : BaseFragment() {

    private val factory: MainHomeViewModelFactory by instance()
    private lateinit var binding: FragmentAccountBinding
    private lateinit var viewModel: MainHomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)
        viewModel = ViewModelProvider(this,factory).get(MainHomeViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.logout.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        return binding.root;
    }

}