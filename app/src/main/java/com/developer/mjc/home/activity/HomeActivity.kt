package com.developer.mjc.home.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.developer.mjc.R
import com.developer.mjc.databinding.ActivityHomeBinding
import com.developer.mjc.home.fragment.consumer.ConsumerLandingFragment
import com.developer.mjc.profile.ProfileFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setupBottomNav()
        mBinding.toolbarHome.tvAdminToolbarHeading.text = "Home"
        mBinding.navBarHome.selectedItemId = R.id.nav_action_home
        openFragment(ConsumerLandingFragment.newInstance())

    }

    private fun setupBottomNav() {

        mBinding.navBarHome.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.nav_action_profile ->{
                    mBinding.toolbarHome.tvAdminToolbarHeading.text = "Profile"
                    openFragment(ProfileFragment.newInstance("123",false))
                }
                R.id.nav_action_home ->{
                    mBinding.toolbarHome.tvAdminToolbarHeading.text = "Home"
                    openFragment(ConsumerLandingFragment.newInstance())
                }
                R.id.nav_action_request ->{
                    mBinding.toolbarHome.tvAdminToolbarHeading.text = "Request"
                    openFragment(Fragment())
                }
                else -> false
            }
        }
    }

    private fun openFragment(fragment: Fragment): Boolean {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(mBinding.frameHome.id,fragment)
        fragmentTransaction.commit()
        return true
    }
}