package com.example.nextdoormvvm.user.ui.updateprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.internal.ScopedFragment
import com.example.nextdoormvvm.manualunittesting.ObjectGenerator
import com.example.nextdoormvvm.user.network.injection.UserModelGenerator
import com.example.nextdoormvvm.user.network.injection.UserViewModelTypeEnum
import kotlinx.android.synthetic.main.ongoing_dishes_fragment.*
import kotlinx.android.synthetic.main.update_profile_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException

class UpdateProfileFragment : ScopedFragment() {

    companion object {
        fun newInstance() = UpdateProfileFragment()
    }

    private lateinit var viewModel: UpdateProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.update_profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        updateUserProfile()
    }


    private fun updateUserProfile() {

        val queryParameterMap =
            mapOf("userId" to "86", // Soumen
                "columnName" to "FlatNumber", // FlatNumber | ProfileImageUrl | FullName | Gender | Email | FlatNumber | SpecializedOption | AboutChef
                "columnValue" to "A2-503")


        val newbuyerPost = ObjectGenerator.generateNewBuyerPost()
        viewModel = UserModelGenerator.getModel(
            this,
            UserViewModelTypeEnum.UpdateProfileViewModel,
            queryParameterMap
        ) as UpdateProfileViewModel

        GlobalScope.launch(Dispatchers.Main) {
            try{
                val httpResponseMessage = viewModel.updateProfile.await()
                textViewUpdateProfile.text = httpResponseMessage.toString()
            }catch (e: IOException) {
                textViewUpdateProfile.text = e.toString()
            }
        }
    }


}
