package com.developer.mjc.home.fragment.consumer

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.developer.mjc.R
import com.developer.mjc.databinding.FragmentConsumerLandingBinding
import com.developer.mjc.home.adapter.HomeItemAdapter
import com.developer.mjc.model.home.ConsumerHomeItem


class ConsumerLandingFragment : Fragment() {

    private lateinit var mBinding: FragmentConsumerLandingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentConsumerLandingBinding.inflate(inflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupHomeGrid()

    }

    private fun setupHomeGrid() {
        mBinding.rvConsumerHome.layoutManager = GridLayoutManager(activity,2)
        var itemList = ArrayList<ConsumerHomeItem>()

        itemList.add(ConsumerHomeItem("House",R.drawable.ic_house))
        itemList.add(ConsumerHomeItem("Mall",R.drawable.ic_mall))
        itemList.add(ConsumerHomeItem("Stadium",R.drawable.ic_stadium))
        itemList.add(ConsumerHomeItem("Auditorium",R.drawable.ic_theatre))
        itemList.add(ConsumerHomeItem("Bridge",R.drawable.ic_bridge))
        itemList.add(ConsumerHomeItem("Tunnel",R.drawable.ic_tunnel))

        mBinding.rvConsumerHome.adapter = HomeItemAdapter(context!!,itemList)

    }

    companion object {

        fun newInstance() =
            ConsumerLandingFragment()
    }
}