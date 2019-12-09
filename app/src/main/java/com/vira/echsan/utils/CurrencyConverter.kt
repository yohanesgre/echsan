package com.vira.echsan.utils

import java.text.NumberFormat
import java.util.*

fun ConvertToCurrency(ammountDbl:Double?=null, ammountStr:String?=null): String{
    var value:Double? = null
    if (ammountStr!=null)
        value = ammountStr.toDouble()
    else if (ammountDbl!=null)
        value = ammountDbl
    val localeID = Locale("in", "ID")
    val format = NumberFormat.getCurrencyInstance(localeID)
    //format.setMaximumFractionDigits(0)
    //format.setCurrency(Currency.getInstance("IDR"))
    var result = format.format(value)
    result = StringBuffer(result).insert(3, ". ").toString()
    return result
}

fun ConvertCurrencyToDouble(input: String): Double {
    return input.replace("[Rp.\\s]".toRegex(), "").toDouble()
}