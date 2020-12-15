package com.developer.mjc.firebase

import android.widget.Toast
import com.developer.mjc.model.User
import com.developer.mjc.signup.SignupActivity
import com.developer.mjc.util.MjcConstants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase

class FirestoreClass {

    private val mFirestore = FirebaseFirestore.getInstance()
    fun registerUser(activity: SignupActivity, userInfo:User){
        mFirestore.collection(MjcConstants.USERS).document(getCurrentUserID()).set(userInfo,
            SetOptions.merge()).addOnSuccessListener {

                // Method when successfully completed
                Toast.makeText(activity,"Successfully Registered",Toast.LENGTH_SHORT).show()
                FirebaseAuth.getInstance().signOut()
        }
    }

    //Function to get current user id
    fun getCurrentUserID():String{
        return FirebaseAuth.getInstance().currentUser!!.uid
    }
}