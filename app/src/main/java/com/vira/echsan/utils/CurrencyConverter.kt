package com.vira.echsan.utils

import java.text.NumberFormat
import java.util.*

fun ConvertToCurrency(ammountDbl:Double?=null, ammountStr:String?=null): String{
    var value:Double? = null
    if (ammountStr!=null)
        value = ammountStr.toDouble()
    else if (ammountDbl!=null)
        value = ammountDbl
    val format = NumberFormat.getCurrencyInstance()
    format.setMaximumFractionDigits(0)
    format.setCurrency(Currency.getInstance("IDR"))
    return format.format(value)
}