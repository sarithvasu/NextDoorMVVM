package com.example.nextdoormvvm.buyer.network.injection

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nextdoormvvm.buyer.ui.chef.chefdetail.ChefDetailViewModel
import com.example.nextdoormvvm.buyer.ui.chef.testimonial.TestimonialViewModel
import com.example.nextdoormvvm.buyer.ui.home.checkout.CheckOutStockViewModel
import com.example.nextdoormvvm.buyer.ui.home.checkout.CheckOutViewModel
import com.example.nextdoormvvm.buyer.ui.home.checkoutrequest.CheckoutRequestViewModel
import com.example.nextdoormvvm.buyer.ui.home.ongoing.OngoingDishesViewModel
import com.example.nextdoormvvm.buyer.ui.home.request.RequestDishViewModel
import com.example.nextdoormvvm.buyer.ui.productdetail.ProductDetailViewModel
import com.example.nextdoormvvm.buyer.ui.ratingandreview.RatingAndReviewPopupViewModel
import com.example.nextdoormvvm.internal.BusinessObject

class BuyerModelGenerator : ViewModel() {

    companion object {
        private lateinit var viewModel: ViewModel

        fun getModel(fragment: Fragment, viewModelType: BuyerViewModelTypeEnum, queryParameterMap: Map<String, String> ): ViewModel {

            if(viewModelType == BuyerViewModelTypeEnum.OngoingDishesViewModel){
                val modelFactory = BuyerViewModelFactory(BuyerViewModelTypeEnum.OngoingDishesViewModel, queryParameterMap)
                viewModel = ViewModelProvider(fragment,modelFactory).get(OngoingDishesViewModel::class.java)
            }

            return viewModel

        }


        fun getModel(fragment: Fragment, viewModelType: BuyerViewModelTypeEnum, businessObject: BusinessObject): ViewModel {

            when (viewModelType) {
                BuyerViewModelTypeEnum.ProductDetailViewModel -> {
                    val modelFactory = BuyerViewModelFactoryBO(BuyerViewModelTypeEnum.ProductDetailViewModel, businessObject)
                    viewModel = ViewModelProvider(fragment,modelFactory).get(ProductDetailViewModel::class.java)
                }
                BuyerViewModelTypeEnum.ChefDetailViewModel -> {
                    val modelFactory = BuyerViewModelFactoryBO(BuyerViewModelTypeEnum.ChefDetailViewModel, businessObject)
                    viewModel = ViewModelProvider(fragment,modelFactory).get(ChefDetailViewModel::class.java)
                }
                BuyerViewModelTypeEnum.TestimonialViewModel -> {
                    val modelFactory = BuyerViewModelFactoryBO(BuyerViewModelTypeEnum.TestimonialViewModel, businessObject)
                    viewModel = ViewModelProvider(fragment,modelFactory).get(TestimonialViewModel::class.java)
                }
                BuyerViewModelTypeEnum.CheckOutViewModel -> {
                    val modelFactory = BuyerViewModelFactoryBO(BuyerViewModelTypeEnum.CheckOutViewModel, businessObject)
                    viewModel = ViewModelProvider(fragment,modelFactory).get(CheckOutViewModel::class.java)
                }
                BuyerViewModelTypeEnum.CheckoutRequestViewModel -> {
                    val modelFactory = BuyerViewModelFactoryBO(BuyerViewModelTypeEnum.CheckoutRequestViewModel, businessObject)
                    viewModel = ViewModelProvider(fragment,modelFactory).get(CheckoutRequestViewModel::class.java)
                }
                BuyerViewModelTypeEnum.RequestDishViewModel -> {
                    val modelFactory = BuyerViewModelFactoryBO(BuyerViewModelTypeEnum.RequestDishViewModel, businessObject)
                    viewModel = ViewModelProvider(fragment,modelFactory).get(RequestDishViewModel::class.java)
                }
                else -> throw IllegalArgumentException("Wrong ViewModel Type")
            }
            return viewModel

        }



        fun getModel(fragment: Fragment, viewModelType: BuyerViewModelTypeEnum, businessObjects: ArrayList<BusinessObject>): ViewModel {

            if(viewModelType == BuyerViewModelTypeEnum.RatingAndReviewPopupViewModel){
                val modelFactory = BuyerViewModelFactoryBOList(BuyerViewModelTypeEnum.RatingAndReviewPopupViewModel, businessObjects)
                viewModel = ViewModelProvider(fragment,modelFactory).get(
                    RatingAndReviewPopupViewModel::class.java)
            }

            if(viewModelType == BuyerViewModelTypeEnum.CheckOutStockViewModel){
                val modelFactory = BuyerViewModelFactoryBOList(BuyerViewModelTypeEnum.CheckOutStockViewModel, businessObjects)
                viewModel = ViewModelProvider(fragment,modelFactory).get(
                    CheckOutStockViewModel::class.java)
            }

            return viewModel
        }

    }
}

