package com.developer.mjc.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.developer.mjc.R
import com.developer.mjc.consumer.requirements.RequirementActivity
import com.developer.mjc.databinding.LayoutConsumerHomeItemBinding
import com.developer.mjc.model.home.ConsumerHomeItem

class HomeItemAdapter(val context: Context,val itemList: List<ConsumerHomeItem>): RecyclerView.Adapter<HomeItemAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val binding = LayoutConsumerHomeItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.name.text = item.name
        holder.icon.setImageDrawable(ContextCompat.getDrawable(context,item.icon))
        holder.layout.setOnClickListener{
            context.startActivity(Intent(context,RequirementActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    class ViewHolder(view:LayoutConsumerHomeItemBinding): RecyclerView.ViewHolder(view.root) {

        val name = view.tvHomeItemName
        val icon = view.ivHomeItemIcon
        val layout = view.llHomeTileView
    }
}