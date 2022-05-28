package com.example.danstest.ui.detail_position

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.danstest.data.repo.RecruitmentRepo

class DetailRecruitmentViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailRecruitMentViewModel() as T
    }
}