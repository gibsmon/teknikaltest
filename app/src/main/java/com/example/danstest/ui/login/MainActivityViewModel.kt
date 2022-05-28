package com.example.danstest.ui.login

import androidx.lifecycle.ViewModel
import com.example.danstest.data.repo.RecruitmentRepo

class MainActivityViewModel(employeesRepo: RecruitmentRepo) :ViewModel(){

    lateinit var mainActivityListener: MainActivityListener

    fun loginFacebbok(){
        mainActivityListener.intentToHome()
    }


    fun loginGoogle(){
        mainActivityListener.intentToHome()
    }


}