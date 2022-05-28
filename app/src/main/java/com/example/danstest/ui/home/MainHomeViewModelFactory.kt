package com.example.danstest.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.danstest.data.repo.RecruitmentRepo

class MainHomeViewModelFactory(private val employeesRepo: RecruitmentRepo) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainHomeViewModel(employeesRepo) as T
    }
}