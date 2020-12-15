package com.developer.mjc.engineer.works

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developer.mjc.R
import kotlinx.android.synthetic.main.adapter_work_image.view.*

class AddWorkImageAdapter(val context: Context, val imageList: ArrayList<String>):
    RecyclerView.Adapter<AddWorkImageAdapter.ViewHolder>() {
    private var progressPosition = -1
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val image = view.ivWorkImage
        val progress = view.imageUploadingProgress
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_work_image,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (progressPosition != 1 && position == progressPosition){
            holder.progress.visibility = View.VISIBLE
        }
        else{
            holder.progress.visibility = View.GONE
        }
        if (!imageList[position].isNullOrEmpty()){
            Glide.with(context)
                .load(imageList[position])
                .into(holder.image)
        }

    }

    fun addImage(imageUrl: String){
        imageList.add(imageUrl)
        notifyItemChanged(imageList.size-1)
    }
    fun updateImage(imageUrl: String){
        imageList[imageList.size-1] = imageUrl
        notifyItemChanged(imageList.size-1)
    }
    fun enableProgress(isEnabled: Boolean){

        if (isEnabled){
            progressPosition = imageList.size - 1
        }
        else{
            progressPosition = -1
        }
    }

    override fun getItemCount(): Int {
       return imageList.size
    }


}