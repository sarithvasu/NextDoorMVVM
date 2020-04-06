package com.example.nextdoormvvm.common.ui.launch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.buyer.ui.BuyerActivity
import com.example.nextdoormvvm.common.ui.CommonActivity
import com.example.nextdoormvvm.seller.ui.SellerActivity
import com.example.nextdoormvvm.user.ui.UserActivity

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
    }
    open fun clickedBuyer(view:View){
        startActivity(Intent(this,BuyerActivity::class.java))
    }
    open fun clickedSeller(view:View){
        startActivity(Intent(this,SellerActivity::class.java))
    }

    open fun clickedCommon(view:View){
        startActivity(Intent(this,
            CommonActivity::class.java))
    }

    open fun clickedUser(view:View){
        startActivity(Intent(this, UserActivity::class.java))
    }





}
