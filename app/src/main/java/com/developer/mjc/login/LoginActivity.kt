package com.developer.mjc.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.developer.mjc.R
import com.developer.mjc.databinding.ActivityLoginBinding
import com.developer.mjc.home.activity.HomeActivity
import com.developer.mjc.signup.SignupActivity
import com.developer.mjc.util.CredsValidationHelper
import com.developer.mjc.util.ErrorViewHelper.Companion.disableErrorBlueBg
import com.developer.mjc.util.ErrorViewHelper.Companion.enableError
import com.developer.mjc.util.MjcConstants

class LoginActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setupListeners()
    }

    private fun setupListeners() {
        mBinding.btLogin.setOnClickListener{
            validateAndProcessinput()
        }
        mBinding.tvNavSignup.setOnClickListener{
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }
    }
    private fun validateAndProcessinput(){
        val email = mBinding.tvLoginEmail.text.toString()
        val password = mBinding.tvLoginPassword.text.toString()
        if (!CredsValidationHelper.isValidEmail(email))
        {
            mBinding.tvLoginEmail.enableError("Invalid email!")
            return
        }
        else {
            mBinding.tvLoginEmail.disableErrorBlueBg()

        }
        if (!CredsValidationHelper.isValidPassword(password))
        {
            mBinding.tvLoginPassword.enableError("Weak Password!")
            return
        }
        else {
            mBinding.tvLoginPassword.disableErrorBlueBg()

        }

        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra(MjcConstants.KEY_USER_TYPE, MjcConstants.USER_TYPE_CONSUMER)
        startActivity(intent)


    }

}