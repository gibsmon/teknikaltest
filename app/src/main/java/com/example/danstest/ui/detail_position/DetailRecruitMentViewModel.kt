package com.example.danstest.ui.detail_position

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.danstest.data.dto.recruitment.RecruitmentResponseItem
import com.example.danstest.data.repo.RecruitmentRepo

class DetailRecruitMentViewModel() :ViewModel(){

    var rcItem = MutableLiveData<RecruitmentResponseItem>();

}