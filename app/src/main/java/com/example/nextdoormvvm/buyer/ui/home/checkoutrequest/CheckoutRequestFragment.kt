package com.example.nextdoormvvm.buyer.ui.home.checkoutrequest

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.buyer.network.injection.BuyerModelGenerator
import com.example.nextdoormvvm.buyer.network.injection.BuyerViewModelTypeEnum
import com.example.nextdoormvvm.manualunittesting.ObjectGenerator
import kotlinx.android.synthetic.main.ongoing_dishes_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class CheckoutRequestFragment : Fragment() {



    private lateinit var viewModel: CheckoutRequestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.checout_request_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CheckoutRequestViewModel::class.java)
        postCheckOutRequest()
    }

    /* working code. pls call from appropriate method*/
    private fun postCheckOutRequest() {
        val checkOutRequest = ObjectGenerator.generateCheckoutByRequest()
        val viewModel = BuyerModelGenerator.getModel(
            this,
            BuyerViewModelTypeEnum.CheckoutRequestViewModel,
            checkOutRequest
        ) as CheckoutRequestViewModel

        GlobalScope.launch(Dispatchers.Main) {

            try {
                val newDishResponse = viewModel.postOrderByRequest.await()
                //textView.text = newDishResponse.toString()
            } catch (e: HttpException) {
                /* val gson= Gson()
                var verifyDish = gson.fromJson(e.response().errorBody()?.string(), Array<VerifyDish>::class.java)
                textView.text =verifyDish[0].toString()*/

               // textView.text = e.response().errorBody()?.string()
            } catch (e: IOException) {
              //  textView.text = e.toString()
            }
        }

    }

}
