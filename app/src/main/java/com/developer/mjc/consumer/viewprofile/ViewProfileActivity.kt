package com.developer.mjc.consumer.viewprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.developer.mjc.R
import com.developer.mjc.consumer.workrequest.CreateRequestActivity
import com.developer.mjc.model.Address
import com.developer.mjc.model.User
import com.developer.mjc.util.MjcConstants
import com.developer.mjc.util.TextUtil
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_view_profile.*
import kotlinx.android.synthetic.main.main_toolbar.view.*

class ViewProfileActivity : AppCompatActivity() {

    private var userId = ""
    private var planList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_profile)

        toolbar_view_profile.tvAdminToolbarHeading.text = "Profile"
        toolbar_view_profile.button_back_toolbar.setOnClickListener{
            onBackPressed()
        }

        if(intent != null){
            userId = intent.getStringExtra(MjcConstants.KEY_USER_ID)!!
            planList.addAll(intent.getStringArrayListExtra(MjcConstants.KEY_PLAN_LIST)!!)
        }

        setupView()
    }

    private fun setupView() {
        val user =  User("1232323",
            "Chithlal","ChithlalKrishna@gmil.com",
            "871413584",
            "",
            true,
            "",
            Address("","","",""),
            about = "Android Developer, Software Engineer, Tech enthusiast, Following peace",
            "12 Nov 2020",
            "1000",
            "Software Engineer"
        )

        tvNameProfileView.text = user.name
        tvDojProfileView.text = "Member since ${user.doj}"
        tvProfileViewFee.text = TextUtil.getIndianCurrencyFormat(Integer.valueOf(user.fees!!)).dropLast(3)
        tvprofileViewAboutMe.text = user.about
        tvOccupationprofileView.text = user.occupation
        tvProfileViewWorks.text = if(user.works== null) "0" else user.works.size.toString()

        btHireMe.setOnClickListener{
            Prefs.putString(MjcConstants.KEY_ENGINEER_ID,user.id)
            val intent = Intent(this,CreateRequestActivity::class.java)
            intent.putStringArrayListExtra(MjcConstants.KEY_PLAN_LIST,planList)
            startActivity(intent)
        }
    }
}