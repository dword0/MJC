package com.developer.mjc.consumer.engineerslist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developer.mjc.R
import com.developer.mjc.consumer.viewprofile.ViewProfileActivity
import com.developer.mjc.model.User
import com.developer.mjc.util.MjcConstants
import com.developer.mjc.util.TextUtil
import kotlinx.android.synthetic.main.layout_engineer_list_item.view.*

class EngineerListAdapter(val context: Context,
val listEngineer: ArrayList<User>,
val itemClickListener: ItemClickListener): RecyclerView.Adapter<EngineerListAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {

        val name = view.tvNameEngineerList
        val works = view.tvWorksCompletedEngineerList
        val profileImage = view.profile_imageEngineer_list
        val fee = view.tvFeesEngineerList
        val root = view.root_engineer_listItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_engineer_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user = listEngineer[position]

        if (user.profileImage.isNotBlank()){
            Glide.with(context)
                .load(user.profileImage)
                .centerCrop()
                .placeholder(ContextCompat.getDrawable(context,R.drawable.ic_engineer))
                .into(holder.profileImage)
        }
        holder.profileImage.setPadding(5)
        holder.name.text = user.name
        holder.fee.text = TextUtil.getIndianCurrencyFormat(Integer.valueOf(user.fees))
        holder.works.text = (if (user.works == null) 0 else user.works.size).toString()
        holder.root.setOnClickListener{
            itemClickListener.onItemClick(user.id)

        }
    }

    override fun getItemCount(): Int {
        return listEngineer.size
    }
    interface ItemClickListener{
        fun onItemClick(userId: String)
    }
}