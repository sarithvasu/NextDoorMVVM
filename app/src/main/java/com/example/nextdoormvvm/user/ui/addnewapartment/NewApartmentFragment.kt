package com.example.nextdoormvvm.user.ui.addnewapartment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.internal.ScopedFragment
import com.example.nextdoormvvm.manualunittesting.ObjectGenerator
import com.example.nextdoormvvm.user.network.injection.UserModelGenerator
import com.example.nextdoormvvm.user.network.injection.UserViewModelTypeEnum
import kotlinx.android.synthetic.main.ongoing_dishes_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewApartmentFragment : ScopedFragment() {

    companion object {
        fun newInstance() = NewApartmentFragment()
    }

    private lateinit var viewModel: NewApartmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_apartment_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        postNewApartmentRequest()
    }


    private fun postNewApartmentRequest() {
        val newApartmentPost = ObjectGenerator.generateNewApartmentPost()
        viewModel = UserModelGenerator.getModel(
            this,
            UserViewModelTypeEnum.NewApartmentViewModel,
            newApartmentPost
        ) as NewApartmentViewModel

        launch(Dispatchers.Main) {
            val userInfoResponse = viewModel.insertNewApartment.await()
            //textView.text = userInfoResponse.toString()

        }
    }

}
