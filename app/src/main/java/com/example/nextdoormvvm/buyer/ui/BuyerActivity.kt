package com.example.nextdoormvvm.buyer.ui

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.nextdoormvvm.R
import kotlinx.android.synthetic.main.activity_buyer.*


class BuyerActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer)


           setSupportActionBar(toolbar)

           navController = Navigation.findNavController(this, R.id.buyer_nav_host_fragment)

           // Now setup bottom navigation menu
           buyer_bottom_nav.setupWithNavController(navController)

           // setup Actionbar with nav controller
           NavigationUI.setupActionBarWithNavController(this, navController)

    }

     // This provide action on the back arrow in the toolbar, which take us back to home fragment
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}


