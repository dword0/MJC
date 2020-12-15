package com.developer.mjc.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.developer.mjc.databinding.ActivitySignupBinding
import com.developer.mjc.model.User
import com.developer.mjc.util.CredsValidationHelper
import com.developer.mjc.util.ErrorViewHelper.Companion.disableErrorBlueBg
import com.developer.mjc.util.ErrorViewHelper.Companion.enableError

class SignupActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupListeners()
    }

    private fun setupListeners() {
        mBinding.btSignup.setOnClickListener{



            validateAndProcessInput()
        }
    }

    private fun validateAndProcessInput() {
        val name = mBinding.tvSignupName.text.toString()
        val email = mBinding.tvSignupEmail.text.toString()
        val phone = mBinding.tvSignupPhone.text.toString()
        val password = mBinding.tvSignupPassword.text.toString()

        if(!CredsValidationHelper.isValidInputText(name)){
            mBinding.tvSignupName.enableError("Invalid Name")
            return
        }
        else{
            mBinding.tvSignupName.disableErrorBlueBg()
        }
        if(!CredsValidationHelper.isValidEmail(email)){
            mBinding.tvSignupEmail.enableError("Invalid Email")
            return
        }
        else{
            mBinding.tvSignupEmail.disableErrorBlueBg()
        }
        if(!CredsValidationHelper.isValidPhone(phone)){
            mBinding.tvSignupPhone.enableError("Invalid phone")
            return
        }
        else{
            mBinding.tvSignupPhone.disableErrorBlueBg()
        }
        if(!CredsValidationHelper.isValidPassword(password)){
            mBinding.tvSignupPassword.enableError("Weak Password")
            return
        }
        else{
            mBinding.tvSignupPassword.disableErrorBlueBg()
        }

        //val user = User(name = name,email = email,isEngineer = false)
        val signupViewMode = SignUpViewModel()
        signupViewMode.signLiveData.observe(this, Observer {
        })
    }
}