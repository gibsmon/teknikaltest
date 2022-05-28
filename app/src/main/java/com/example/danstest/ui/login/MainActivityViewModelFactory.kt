package com.example.danstest.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.danstest.data.repo.RecruitmentRepo

class MainActivityViewModelFactory(private val employeesRepo: RecruitmentRepo) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(employeesRepo) as T
    }
}