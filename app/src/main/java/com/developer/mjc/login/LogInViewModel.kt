package com.developer.mjc.login

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.developer.mjc.model.User
import com.developer.mjc.home.activity.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase


class LogInViewModel(): ViewModel() {

    val signLiveData = MutableLiveData<User>()
    lateinit var auth: FirebaseAuth
    auth = Firebase.auth

    fun LogIn(email : String, password: String){

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->

                //LOGIN IS SUCCESSFUL
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("SignIN", "signInWithEmail:success")
                    val user = auth.currentUser
                }
                //LOGIN FAILED
                else {
                    // If sign in fails, display a message to the user.
                    Log.w("SignIN", "signInWithEmail:failure", task.exception)
                    Toast.makeText(this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }

            }
    }
}