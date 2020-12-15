package com.developer.mjc.consumer.engineerslist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer.mjc.R
import com.developer.mjc.consumer.viewprofile.ViewProfileActivity
import com.developer.mjc.model.Address
import com.developer.mjc.model.User
import com.developer.mjc.util.MjcConstants
import kotlinx.android.synthetic.main.activity_engineer_list.*
import kotlinx.android.synthetic.main.main_toolbar.view.*

class EngineerListActivity : AppCompatActivity() {
    var planlist = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_engineer_list)

        if (intent != null){
            planlist.addAll(intent.getStringArrayListExtra(MjcConstants.KEY_PLAN_LIST)!!)
        }

        toolbar_engineer.tvAdminToolbarHeading.text = "Hire Engineer"
        toolbar_engineer.button_back_toolbar.setOnClickListener{
            onBackPressed()
        }
        setupViews()
    }

    private fun setupViews() {

        val engineerList = ArrayList<User>()
        val user =  User("1232323",
            "Chithlal","ChithlalKrishna@gmil.com",
            "871413584",
            "",
            true,
            "",
            Address("","","",""),
            about = "",
            "",
            "1000",
            "Software Engineer"
        )
        engineerList.add(user)
        engineerList.add(user)
        engineerList.add(user)
        engineerList.add(user)
        engineerList.add(user)
        engineerList.add(user)



        rvEngineerList.layoutManager = LinearLayoutManager(this)
        rvEngineerList.adapter = EngineerListAdapter(this,engineerList,
        object: EngineerListAdapter.ItemClickListener{
            override fun onItemClick(userId: String) {
                val intent = Intent(this@EngineerListActivity, ViewProfileActivity::class.java)
                intent.putExtra(MjcConstants.KEY_USER_ID,user.id)
                intent.putStringArrayListExtra(MjcConstants.KEY_PLAN_LIST,planlist)
                startActivity(intent)
            }

        })
    }
}