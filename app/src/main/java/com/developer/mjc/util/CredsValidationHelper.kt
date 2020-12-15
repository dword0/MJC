package com.developer.mjc.util

class CredsValidationHelper() {

companion object{

    fun isValidEmail(email: String): Boolean{
        return if(email.isNullOrEmpty())
            false
        else android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun isValidPassword(password: String):Boolean{
       val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%!\\-_?&])(?=\\S+\$).{8,}".toRegex()
        return if(password.isNullOrEmpty())
            false
        else passwordRegex.matches(password)
    }

    fun isValidInputText(inputText: String): Boolean{
        return inputText.isNotEmpty()
    }

    fun isValidPhone(phone: String):Boolean{
        return if(phone.isNullOrEmpty())
            false
        else android.util.Patterns.PHONE.matcher(phone).matches()
    }
}
}