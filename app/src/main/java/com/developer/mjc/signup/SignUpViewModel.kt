package com.developer.mjc.signup

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.developer.mjc.firebase.FirestoreClass
import com.developer.mjc.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase


class SignUpViewModel(): ViewModel() {

     val signLiveData = MutableLiveData<User>()


    fun signUp(signupData: User){
// Need to replace "Abcd@1234" with the password from user
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(signupData.email,"Abcd@1234").addOnCompleteListener {
            task ->
            if(task.isSuccessful){
                val firebaseUser : FirebaseUser = task.result!!.user!!
                val user = User(firebaseUser.uid,signupData.name,signupData.email,signupData.phone)
                FirestoreClass().registerUser(this,user)
                signupData.id = firebaseUser.uid
                signLiveData.postValue(signupData)
            }
        }

    }
}