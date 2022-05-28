package com.example.danstest.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.danstest.data.dto.recruitment.RecruitmentResponse
import com.example.danstest.data.repo.RecruitmentRepo
import com.example.danstest.di.MVVMApplication
import com.example.danstest.util.ApiException

class MainHomeViewModel(val recruitmentRepo: RecruitmentRepo):ViewModel(){

    var cari =  MutableLiveData<String>()
    var global = MVVMApplication

    suspend fun getListRecruitmentByPage(page:Int): RecruitmentResponse {
        try{
            val map: MutableMap<String, String> = HashMap()
            map["page"] = page.toString()
            return recruitmentRepo.getListRecruitment(map.toMap())
        }catch (e:Exception){
            throw ApiException(e.message!!)
        }
    }


    suspend fun getListRecruitmentByPencarian(value:String): RecruitmentResponse {
        try{
            val map: MutableMap<String, String> = HashMap()
            map[global.searchBy] = value;
            return recruitmentRepo.getListRecruitment(map.toMap())
        }catch (e:Exception){
            throw ApiException(e.message!!)
        }
    }



}