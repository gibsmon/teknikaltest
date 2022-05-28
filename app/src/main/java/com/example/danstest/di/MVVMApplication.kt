package com.example.danstest.di

import android.app.Application
import com.example.danstest.data.repo.RecruitmentRepo
import com.example.danstest.network.MyApi
import com.example.danstest.network.NetworkConnectionInterceptor
import com.example.danstest.ui.detail_position.DetailRecruitmentViewModelFactory
import com.example.danstest.ui.home.MainHomeViewModelFactory
import com.example.danstest.ui.login.MainActivityViewModelFactory
import org.kodein.di.*
import org.kodein.di.android.x.androidXModule


class MVVMApplication : Application(), DIAware {




    override val di by DI.lazy {
        var context = this@MVVMApplication
        import(androidXModule(context))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { MainActivityViewModelFactory(instance()) }
        bind() from singleton { MainHomeViewModelFactory(instance()) }
        bind() from singleton { DetailRecruitmentViewModelFactory() }
        bind() from singleton { RecruitmentRepo(instance()) }

    }

    companion object {
        var searchBy = "location";
    }
}