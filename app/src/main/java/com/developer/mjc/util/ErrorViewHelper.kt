package com.developer.mjc.util

import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.developer.mjc.R
import com.developer.mjc.util.ErrorViewHelper.Companion.enableError

class ErrorViewHelper() {
    companion object{

        fun EditText.enableError(errorText:String){
            background = ContextCompat.getDrawable(this.context, R.drawable.field_bg_red)
            this.setError(errorText)
        }
        fun EditText.disableErrorBlueBg(){

            background = ContextCompat.getDrawable(this.context, R.drawable.field_bg_blue)
        }
        fun EditText.disableErrorGreyBg(){

            background = ContextCompat.getDrawable(this.context, R.drawable.field_bg_grey)
        }
    }
}