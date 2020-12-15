package com.developer.mjc.util

import java.text.NumberFormat
import java.util.*

class TextUtil {
    companion object{
        fun getIndianCurrencyFormat(value: Int): String {
            val formatter = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
            return formatter.format(value)
        }
    }
}