package com.example.nextdoormvvm.seller.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.seller.network.injection.SellerViewModelTypeEnum
import com.example.nextdoormvvm.seller.network.injection.SellerModelGenerator
import kotlinx.android.synthetic.main.dashboard_fragment.*
import kotlinx.android.synthetic.main.ongoing_dishes_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    companion object {
        fun newInstance() = DashboardFragment()
    }

    private lateinit var viewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dashboard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val queryParameterMap = mapOf("chefId" to "51")
        val model = SellerModelGenerator.getModel(this, SellerViewModelTypeEnum.DashboardViewModel,queryParameterMap) as DashboardViewModel
        GlobalScope.launch(Dispatchers.Main){
            val chefOrdersResponse = model.chefOrders.await()
            chefOrdersResponse.observe(this@DashboardFragment, Observer {
                textView.text =  it.todaysOrder.toString()
            })
        }




    }

}
