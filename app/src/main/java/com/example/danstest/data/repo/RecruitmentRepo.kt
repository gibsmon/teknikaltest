package com.example.danstest.data.repo

import com.example.danstest.data.dto.recruitment.RecruitmentResponse
import com.example.danstest.network.MyApi
import com.example.danstest.network.SafeApiRequest

class RecruitmentRepo(private val api: MyApi) : SafeApiRequest() {
    suspend fun getListRecruitment(option:Map<String, String>):RecruitmentResponse{
        return apiRequest {
            api.getListRecruitment(option)
        }
    }
}