package com.example.nextdoormvvm.user.ui.userInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.internal.ScopedFragment
import com.example.nextdoormvvm.user.network.injection.UserModelGenerator
import com.example.nextdoormvvm.user.network.injection.UserViewModelTypeEnum
import kotlinx.android.synthetic.main.ongoing_dishes_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserInfoFragment : ScopedFragment() {

    companion object {
        fun newInstance() = UserInfoFragment()
    }

    private lateinit var viewModel: UserInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
         bindUI()
    }


    private fun bindUI() {
        val queryParameterMap = mapOf("mobileNumber" to "919739209885")
        viewModel = UserModelGenerator.getModel(this,  UserViewModelTypeEnum.UserInfoViewModel,queryParameterMap) as UserInfoViewModel

        // Get from Network
//        GlobalScope.launch(Dispatchers.Main){
//            val userInfoResponse = viewModel.userInfo.await()
//            userInfoResponse.observe(this@UserInfoFragment, Observer {
//                textView.text =  it.toString()
//            })
//        }


        // Get from Local DB
        GlobalScope.launch(Dispatchers.Main){
            val userInfoResponse = viewModel.userInfoFromDB.await()
           // textView.text =  userInfoResponse.toString()
        }

    }




}
