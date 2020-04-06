package com.example.nextdoormvvm.common.ui.orderhistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.common.network.injection.CommonModelGenerator
import com.example.nextdoormvvm.common.network.injection.CommonViewModelTypeEnum
import com.example.nextdoormvvm.common.network.response.OrderSummary
import com.example.nextdoormvvm.common.ui.adapter.OrderHistoryAdapter
import com.example.nextdoormvvm.internal.Preference
import com.example.nextdoormvvm.internal.ScopedFragment
import com.example.nextdoormvvm.internal.Utility
import kotlinx.android.synthetic.main.order_history_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderHistoryFragment : ScopedFragment() {

    companion object {
        fun newInstance() = OrderHistoryFragment()
    }

    private lateinit var mOrderSummery: List<OrderSummary>
    private lateinit var mOrderHistoryAdapter: OrderHistoryAdapter
    private lateinit var mViewModel: OrderHistoryViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.order_history_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userTypeId = arguments?.getInt(Utility.USER_TYPE_ID)
        val date = arguments?.getString(Utility.CURRENT_DATE)
        val queryParameterMap = mapOf(
            "userId" to Preference.getUserId().toString(),
            "userTypeId" to userTypeId!!.toString(),
            "date" to date!!
        )
        mViewModel = CommonModelGenerator.getModel(
            this,
            CommonViewModelTypeEnum.OrderHistoryViewModel,
            queryParameterMap
        ) as OrderHistoryViewModel
        loadOrderHistory()
    }

    private fun loadOrderHistory() = launch(Dispatchers.Main) {
        val orderHistoryResponse = mViewModel.orderHistory.await()
        orderHistoryResponse.observe(viewLifecycleOwner, Observer {
            mOrderSummery = it.OrderSummary
            order_history_loading.visibility = View.GONE
            if (mOrderSummery.isNotEmpty())
                initBasicSetup()
            else
                no_item_found_layout.visibility = View.VISIBLE

        })
    }

    private fun initBasicSetup() {


        // Just initialize recyclerview
        initOrderHistoryRecyclerView()

        // Load  Ongoing Dish Recycler View with data
        loadOrderHistoryhRecyclerView()
    }


    private fun initOrderHistoryRecyclerView() {
        rv_oder_history.apply {
            layoutManager = LinearLayoutManager(activity)
            mOrderHistoryAdapter = OrderHistoryAdapter()
            adapter = mOrderHistoryAdapter
        }

    }

    private fun loadOrderHistoryhRecyclerView() {
        mOrderHistoryAdapter.submitRequestDishData(mOrderSummery as ArrayList<OrderSummary>)
    }


}
