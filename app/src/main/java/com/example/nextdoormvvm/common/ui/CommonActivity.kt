package com.example.nextdoormvvm.common.ui

import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.buyer.ui.BuyerActivity
import com.example.nextdoormvvm.internal.Utility
import com.example.nextdoormvvm.seller.ui.SellerActivity
import kotlinx.android.synthetic.main.activity_common.*
import java.util.*


class CommonActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var adapter: ImageSliderAdapter
    private lateinit var timer: Timer
    private var currentPage=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)
        setImageSlideHeight()
        tv_sell.setOnClickListener(this)
        tv_buy.setOnClickListener(this)
        adapter = ImageSliderAdapter(this)
        SliderViewPager.adapter = adapter
        setSliderTimer()
    }


    private fun setImageSlideHeight() {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        // val width = size.x
        val height = size.y
        val lp = SliderViewPager.layoutParams as ConstraintLayout.LayoutParams
        lp.height = (height * 35) / 100
        SliderViewPager.layoutParams = lp
    }

    // Setting the slider scroll speed to 2 seconds
    private fun setSliderTimer() {
        val handler = Handler()
        val update = Runnable {
            if (currentPage == Utility.getImageSliderCount()) {
                currentPage = 0
            }
            SliderViewPager.setCurrentItem(currentPage++, true)
        }

        timer = Timer() // This will create a new Thread
        timer.schedule(object : TimerTask() { // task to be scheduled
            override fun run() {
                handler.post(update)
            }
        }, DELAY_MS, PERIOD_MS)
    }
    companion object {
        const val DELAY_MS: Long = 300//delay in milliseconds before task is to be executed
        const val PERIOD_MS: Long = 2000 // time in milliseconds between successive task executions.
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tv_buy -> startActivity(Intent(this, BuyerActivity::class.java))
            R.id.tv_sell -> startActivity(Intent(this, SellerActivity::class.java))
        }

    }
}
