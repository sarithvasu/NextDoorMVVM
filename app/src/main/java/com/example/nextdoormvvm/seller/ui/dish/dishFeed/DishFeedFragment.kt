package com.example.nextdoormvvm.seller.ui.dish.dishFeed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.seller.network.injection.SellerViewModelTypeEnum
import com.example.nextdoormvvm.seller.network.injection.SellerModelGenerator
import kotlinx.android.synthetic.main.ongoing_dishes_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DishFeedFragment : Fragment() {

    companion object {
        fun newInstance() =
            DishFeedFragment()
    }

    private lateinit var feedViewModel: DishFeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindUi()
    }

    private fun bindUi() {
        val queryParameterMap = mapOf("chefId" to "51")
        val model = SellerModelGenerator.getModel(
            this,
            SellerViewModelTypeEnum.DishFeedViewModel,
            queryParameterMap
        ) as DishFeedViewModel
        GlobalScope.launch(Dispatchers.Main) {
            val chefFeedResponse = model.dishFeed.await()
            chefFeedResponse.observe(this@DishFeedFragment, Observer {
                //textView.text = it.InactiveDishFeed.get(0).DishName
                //textView.text = it.InactiveDishFeed.toString()
            })
        }
    }

}
