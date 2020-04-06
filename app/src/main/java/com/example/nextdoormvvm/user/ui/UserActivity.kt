package com.example.nextdoormvvm.user.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.common.ui.CommonActivity
import com.example.nextdoormvvm.internal.Preference
import com.example.nextdoormvvm.user.ui.verifyuser.VerifyUserByMobileNumberFragment
import kotlinx.android.synthetic.main.activity_buyer.*
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(Preference.getUserId()!=-1) {
            startActivity(Intent(this, CommonActivity::class.java))
            finish()
        }
        else {
            setContentView(R.layout.activity_user) // Set initial Fragment
            UserBaseFragment.gotoFragment(VerifyUserByMobileNumberFragment(), this)
        }
    }
}