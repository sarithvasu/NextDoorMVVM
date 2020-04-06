package com.example.nextdoormvvm.buyer.ui.productdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.buyer.network.injection.BuyerModelGenerator
import com.example.nextdoormvvm.buyer.network.injection.BuyerViewModelTypeEnum
import com.example.nextdoormvvm.manualunittesting.ObjectGenerator
import kotlinx.android.synthetic.main.product_detail_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductDetailFragment : Fragment() {

    companion object {
        fun newInstance() =
            ProductDetailFragment()
    }

    private lateinit var viewModel: ProductDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        //PostSharedDishDetail()
    }



    //This is working code, Just call this code from respective Fragment/ Activity. This model (ProductDetailViewModel) dont have any supporting ui so we are keeping the code here.
    private fun PostSharedDishDetail() {
        val sharedDishDetailPost =  ObjectGenerator.generateSharedDishDetailPost()

        val model = BuyerModelGenerator.getModel(
            this,
            BuyerViewModelTypeEnum.ProductDetailViewModel,
            sharedDishDetailPost
        ) as ProductDetailViewModel

       /* GlobalScope.launch(Dispatchers.Main) {
            val httpResponseMessage = model.insertSharedDishDetail.await()
            textViewproductdetail.text = httpResponseMessage.toString()
        }*/
    }

}
