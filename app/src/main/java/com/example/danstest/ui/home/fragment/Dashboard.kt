package com.example.danstest.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.danstest.R
import com.example.danstest.base.BaseFragment
import com.example.danstest.data.adapter.RVAdapter
import com.example.danstest.data.adapter.RVListener
import com.example.danstest.data.dto.recruitment.RecruitmentResponseItem
import com.example.danstest.databinding.FragmentDashboardBinding
import com.example.danstest.ui.detail_position.DetailRecruitment
import com.example.danstest.ui.home.MainHomeViewModel
import com.example.danstest.ui.home.MainHomeViewModelFactory
import com.example.danstest.util.Crt
import com.google.gson.Gson
import org.kodein.di.instance


class Dashboard : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit  var timer: CountDownTimer
    private val factory: MainHomeViewModelFactory by instance()
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewModel: MainHomeViewModel

    private lateinit var rvAdapter: RVAdapter
    private lateinit var listRecrut : MutableList<RecruitmentResponseItem>
    var isLoading: Boolean = false

    var position = 1;
    var lastText = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        viewModel = ViewModelProvider(this,factory).get(MainHomeViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        loadRC(position)
        searchListener()
        return binding.root
    }

    private fun searchListener() {
        binding.textField.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                lastText = s.toString()
                if (s.length == 2) {
                    println("Start Timer")
                    startTimer()
                } else if (s.length >= 3) {
                    println("Search Work")
                    timer.cancel()
                    startTimer()
                }
            }
        })

    }

    fun loadRC(page:Int){
        Crt.main {
            viewModel.getListRecruitmentByPage(page).let {
                listRecrut = it
            }
            initialRC()
            initScrollListener()
        }
    }

    private fun initialRC() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager
        rvAdapter = RVAdapter(listRecrut, object : RVListener{
            override fun toDetail(value: RecruitmentResponseItem) {
                val intent = Intent(context, DetailRecruitment::class.java)
                var passValue = Gson().toJson(value)
                intent.putExtra("objDetail", passValue);
                startActivity(intent)
            }
        })
        binding.recyclerView.adapter = rvAdapter
    }

    private fun initScrollListener(){
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == listRecrut.size - 1) {
                        //bottom of list!
//                        loadMore()
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun loadMore() {
        Crt.main {
            position++
            viewModel.getListRecruitmentByPage(position).let {
                if(it.size>0){
                    Handler(Looper.myLooper()!!).postDelayed({
                        rvAdapter.notifyDataSetChanged()
                        isLoading = false
                    }, 200)
                }
            }
        }
    }


    fun startTimer() {
        timer = object: CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                Crt.main {
                    listRecrut.removeAll(listRecrut);
                    if(viewModel.cari.value != ""){
                        viewModel.getListRecruitmentByPencarian(lastText).let {
                            listRecrut = it
                        }
                        initialRC()
                    }

                }
            }
        }
        timer.start()
    }


}