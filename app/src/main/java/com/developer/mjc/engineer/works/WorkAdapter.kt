package com.developer.mjc.engineer.works

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developer.mjc.R
import com.developer.mjc.model.Work
import kotlinx.android.synthetic.main.adapter_my_works.view.*

class WorkAdapter(val context: Context, val workList: List<Work>):
    RecyclerView.Adapter<WorkAdapter.ViewHolder>() {
    class ViewHolder(view : View):RecyclerView.ViewHolder(view) {
        val workImag = view.ivImageWork
        val workName = view.tvWorkName
        val workComletionDate = view.tvWorkCompletionDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_my_works,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val work = workList[position]

        if (work.imageGallery.isNotEmpty()){
            Glide.with(context)
                .load(work.imageGallery[0])
                .into(holder.workImag)
        }

        holder.workName.text = work.name
        holder.workComletionDate.text = work.dateOfCompletion

        holder.itemView.setOnClickListener{

        }

    }

    override fun getItemCount(): Int {
       return workList.size
    }

}