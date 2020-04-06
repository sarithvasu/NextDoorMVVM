package com.example.nextdoormvvm.internal

import java.util.regex.Pattern

object Validator {
    fun validateMobileNumber(input:String):String?{
        var msg:String? = null
        val strPhonePattern = "[0-9]{10}"
        val pattern = Pattern.compile(strPhonePattern)
        val matcher = pattern.matcher(input)
        if (!matcher.matches()) msg = "invalid Mobile Number"
        return msg
    }


    fun isValidEmail(mStrEmail: String): Boolean {
        val emailPattern =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

        val pattern = Pattern.compile(emailPattern)
        val matcher = pattern.matcher(mStrEmail)
        return matcher.matches()
    }


    fun isValidFullName(mStrName: String): Boolean {
        val strNamepattern = "^[A-Za-z\\s]+[.]?[A-Za-z\\s]*\$"
        val pattern = Pattern.compile(strNamepattern)
        val matcher = pattern.matcher(mStrName)
        return matcher.matches()
    }

    fun isValidPhone(mStrPhone: String): Boolean {
        val mStrPhonePatternattern = "[0-9]{10}"
        val pattern = Pattern.compile(mStrPhonePatternattern)
        val matcher = pattern.matcher(mStrPhone)
        return matcher.matches()
    }

    private fun isValidAddress(mStrAddress: String): Boolean {
        val strAddress = "[A-Za-z0-9.\\-\\s,]{3,100}"
        val pattern = Pattern.compile(strAddress)
        val matcher = pattern.matcher(mStrAddress)
        return matcher.matches()
    }


    fun validateFlatNumber(flatNumber: String): Boolean {
        var msg = ""
        if(flatNumber=="")
            msg = "Please enter your flat number"
        else if (!isValidAddress(flatNumber))
            msg = "Invalid flat number"
        if (msg != "") {
            Utility.toastThis( msg )
            return false
        }
        return true
    }

}