package com.example.nextdoormvvm.common.ui.userStatus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.common.network.injection.CommonModelGenerator
import com.example.nextdoormvvm.common.network.injection.CommonViewModelTypeEnum
import kotlinx.android.synthetic.main.user_status_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserStatusFragment : Fragment() {

    companion object {
        fun newInstance() =
            UserStatusFragment()
    }

    private lateinit var viewModel: UserStatusViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_status_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadUserStatus()

    }


    private fun loadUserStatus() {

        // Case: Buyer
        val queryParameterMap = mapOf("userId" to "85", "userTypeId" to "1", "chefId" to "-1")

        // Case: Seller
        //val queryParameterMap = mapOf("userId" to "86", "userTypeId" to "2", "chefId" to "51")


        val model = CommonModelGenerator.getModel(
            this,
            CommonViewModelTypeEnum.UserStatusViewModel,
            queryParameterMap
        ) as UserStatusViewModel

        GlobalScope.launch(Dispatchers.Main) {
            val userStatusResponse = model.userStatus.await()
            userStatusResponse.observe(viewLifecycleOwner, Observer {
                textView.text = it.toString()
            })
        }
    }

    private fun callUpdateOrderStatusList() {

        // Case: Buyer
        val queryParameterMap = mapOf("orderIds" to "30172, 30173, 30174", "orderTypeId" to "1")

        // Case: Seller
        //val queryParameterMap = mapOf("userId" to "86", "userTypeId" to "2", "chefId" to "51")


        val model = CommonModelGenerator.getModel(
            this,
            CommonViewModelTypeEnum.UserStatusViewModel,
            queryParameterMap
        ) as UserStatusViewModel

        GlobalScope.launch(Dispatchers.Main) {
            val userStatusResponse = model.acceptOrderByOrderIds.await()
            textView.text = userStatusResponse.toString()
        }
    }
    private fun callUpdateDeliveryStatusList() {

        // Case: Buyer
        val queryParameterMap = mapOf("orderIds" to "30171, 30172, 30173, 30174, 30175, 30176, 30177, 30178, 30179, 30180, 30181, 30182"
            , "orderTypeId" to "2"
        )

        // Case: Seller
        //val queryParameterMap = mapOf("userId" to "86", "userTypeId" to "2", "chefId" to "51")


        val model = CommonModelGenerator.getModel(
            this,
            CommonViewModelTypeEnum.UserStatusViewModel,
            queryParameterMap
        ) as UserStatusViewModel

        GlobalScope.launch(Dispatchers.Main) {
            val userStatusResponse = model.updateDeliveryStatusByOrderIds.await()
            textView.text = userStatusResponse.toString()
        }
    }
    private fun callUpdateRejectRequestedOrder() {

        // Case: Buyer
        val queryParameterMap = mapOf("orderId" to "30170", "rejectNote" to "not relevant")

        // Case: Seller
        //val queryParameterMap = mapOf("userId" to "86", "userTypeId" to "2", "chefId" to "51")


        val model = CommonModelGenerator.getModel(
            this,
            CommonViewModelTypeEnum.UserStatusViewModel,
            queryParameterMap
        ) as UserStatusViewModel

        GlobalScope.launch(Dispatchers.Main) {
            val userStatusResponse = model.rejectRequestedOrderByOrderId.await()

            textView.text = userStatusResponse.toString()

        }
    }
}
