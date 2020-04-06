package com.example.nextdoormvvm.internal

import android.content.Context
import android.content.SharedPreferences

object Preference {
    private const val USER_ID="USER_ID"
    private const val CHEF_ID="CHEF_ID"
    private const val NEXT_DOOR_PREFERENCE = "Next door preference"
    private var sharedRef: SharedPreferences? = null
    init {
        this.sharedRef = NextDoorApplication.context?.getSharedPreferences(NEXT_DOOR_PREFERENCE, Context.MODE_PRIVATE)
    }
    fun saveUserId(userId: Int) {
        val editor = sharedRef!!.edit()
        editor.putInt(USER_ID, userId)
        editor.apply()
    }
    fun getUserId() : Int = sharedRef!!.getInt(USER_ID, -1)
    fun saveUserChefId(chefId: Int) {
        val editor = sharedRef!!.edit()
        editor.putInt(USER_ID, chefId)
        editor.apply()
    }
    fun getUserChefId() : Int = sharedRef!!.getInt(CHEF_ID, -1)
}