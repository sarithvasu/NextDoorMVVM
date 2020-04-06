package com.example.nextdoormvvm.user.ui.registerseller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.manualunittesting.ObjectGenerator
import com.example.nextdoormvvm.user.network.injection.UserModelGenerator
import com.example.nextdoormvvm.user.network.injection.UserViewModelTypeEnum
import kotlinx.android.synthetic.main.ongoing_dishes_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterSellerFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterSellerFragment()
    }

    private lateinit var viewModel: RegisterSellerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.register_seller_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        upsertChefDetail()
    }


    private fun upsertChefDetail() {
        val chefDetail = ObjectGenerator.generateUpsertChefDetailObject()
        viewModel = UserModelGenerator.getModel(
            this,
            UserViewModelTypeEnum.RegisterSellerViewModel,
            chefDetail
        ) as RegisterSellerViewModel

        GlobalScope.launch(Dispatchers.Main) {
            val userInfoResponse = viewModel.upsertChefDetail.await()
            //textView.text = userInfoResponse.toString()
        }
    }

}
