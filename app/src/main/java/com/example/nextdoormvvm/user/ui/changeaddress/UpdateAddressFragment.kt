package com.example.nextdoormvvm.user.ui.changeaddress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.internal.ScopedFragment
import com.example.nextdoormvvm.manualunittesting.ObjectGenerator
import com.example.nextdoormvvm.user.network.injection.UserModelGenerator
import com.example.nextdoormvvm.user.network.injection.UserViewModelTypeEnum
import kotlinx.android.synthetic.main.update_address_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException

class UpdateAddressFragment : ScopedFragment() {

    companion object {
        fun newInstance() = UpdateAddressFragment()
    }

    private lateinit var viewModel: UpdateAddressViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.update_address_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        updateUserAddress()
    }



    private fun updateUserAddress() {
        //val address = ObjectGenerator.generateUpdateAddressObjectForBuyer()
        val address = ObjectGenerator.generateUpdateAddressObjectForSeller()


        viewModel = UserModelGenerator.getModel(
            this,
            UserViewModelTypeEnum.UpdateAddressViewModel,
            address
        ) as UpdateAddressViewModel

        launch(Dispatchers.Main) {
            try {
                val userInfoResponse = viewModel.updateAddress .await()
                textViewUpdateAddress.text = userInfoResponse.toString()
            }catch (e: IOException) {
                textViewUpdateAddress.text = e.toString()
            }
        }
    }

}
