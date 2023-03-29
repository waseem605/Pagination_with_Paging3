package com.example.paging3.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3.R

class LoaderAdapter: LoadStateAdapter<LoaderAdapter.LoaderViewHolder>() {


    class LoaderViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val progressBar = itemView.findViewById<ProgressBar>(R.id.progress_bar)

        fun bind(state:LoadState){
            progressBar.isVisible = state is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.loader_item,parent,false)
        return LoaderViewHolder(view)
    }
}