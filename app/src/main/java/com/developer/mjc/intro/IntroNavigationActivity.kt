package com.developer.mjc.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.developer.mjc.R
import com.developer.mjc.databinding.ActivityIntroNavigationBinding
import com.developer.mjc.home.activity.HomeActivity
import com.developer.mjc.login.LoginActivity
import com.developer.mjc.util.MjcConstants.Companion.KEY_USER_TYPE
import com.developer.mjc.util.MjcConstants.Companion.USER_TYPE_CONSUMER
import com.developer.mjc.util.MjcConstants.Companion.USER_TYPE_ENGINEER

class IntroNavigationActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityIntroNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityIntroNavigationBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.llStartConsume.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            intent.putExtra(KEY_USER_TYPE, USER_TYPE_CONSUMER)
            startActivity(intent)
        }
        mBinding.llStartEngineer.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            intent.putExtra(KEY_USER_TYPE, USER_TYPE_ENGINEER)
            startActivity(intent)
        }
    }
}