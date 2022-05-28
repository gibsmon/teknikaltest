package com.example.danstest.ui.detail_position

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.danstest.R
import com.example.danstest.base.BaseActivity
import com.example.danstest.data.dto.recruitment.RecruitmentResponseItem
import com.example.danstest.databinding.ActivityDetailRecruitmentBinding
import com.example.danstest.ui.login.MainActivityViewModelFactory
import com.google.gson.Gson
import org.kodein.di.instance

class DetailRecruitment : BaseActivity() {

    private val factory: DetailRecruitmentViewModelFactory by instance()
    private lateinit var viewModel: DetailRecruitMentViewModel
    private lateinit var binding: ActivityDetailRecruitmentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_recruitment)
        viewModel = ViewModelProvider(this, factory).get(DetailRecruitMentViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val ss:String = intent.getStringExtra("objDetail").toString()
        viewModel.rcItem.value = Gson().fromJson(ss, RecruitmentResponseItem::class.java)
    }
}