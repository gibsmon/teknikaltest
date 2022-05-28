package com.example.danstest.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.example.danstest.R
import com.example.danstest.data.dto.recruitment.RecruitmentResponseItem
import com.example.danstest.databinding.LayoutListRecruitmentBinding


class RVAdapter(var listRec: List<RecruitmentResponseItem>, var rvListener: RVListener) : RecyclerView.Adapter<RVAdapter.ViewHolder>(){

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    inner class ViewHolder(val binding: LayoutListRecruitmentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = LayoutListRecruitmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.data = listRec[position]
        holder.binding.layoutMain.setOnClickListener {
            rvListener.toDetail(listRec[position])
        }
    }

    override fun getItemCount(): Int {
        return if (listRec == null) 0 else listRec.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (listRec[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    private class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    }


}