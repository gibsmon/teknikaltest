package com.example.danstest.data.adapter

import com.example.danstest.data.dto.recruitment.RecruitmentResponseItem

interface RVListener {
    fun toDetail(value:RecruitmentResponseItem)
}