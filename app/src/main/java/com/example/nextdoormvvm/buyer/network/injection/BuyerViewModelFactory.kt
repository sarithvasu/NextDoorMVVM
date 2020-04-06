package com.example.nextdoormvvm.buyer.network.injection

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
import com.example.nextdoormvvm.internal.RepositoryInstance


class BuyerViewModelFactory(private val viewModelType: BuyerViewModelTypeEnum, private val queryParameterMap: Map<String, String>) : ViewModelProvider.NewInstanceFactory(){

    private lateinit var viewModel: ViewModel

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return if(viewModelType == BuyerViewModelTypeEnum.OngoingDishesViewModel){
            OngoingDishesViewModel(RepositoryInstance.buyerRepositoryInstance, queryParameterMap) as T
        } else {
            viewModel as T
        }
    }

}







class BuyerViewModelFactoryBO(private val viewModelType: BuyerViewModelTypeEnum, private val businessObject: BusinessObject) : ViewModelProvider.NewInstanceFactory(){

    private lateinit var viewModel: ViewModel

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        when (viewModelType) {
            BuyerViewModelTypeEnum.ProductDetailViewModel -> {
                return ProductDetailViewModel(RepositoryInstance.buyerRepositoryInstance, businessObject) as T
            }
            BuyerViewModelTypeEnum.ChefDetailViewModel -> {
                return ChefDetailViewModel(RepositoryInstance.buyerRepositoryInstance, businessObject) as T
            }
            BuyerViewModelTypeEnum.TestimonialViewModel -> {
                return TestimonialViewModel(RepositoryInstance.buyerRepositoryInstance, businessObject) as T
            }
            BuyerViewModelTypeEnum.CheckOutViewModel -> {
                return CheckOutViewModel(RepositoryInstance.buyerRepositoryInstance, businessObject ) as T
            }
            BuyerViewModelTypeEnum.CheckoutRequestViewModel -> {
                return CheckoutRequestViewModel(RepositoryInstance.buyerRepositoryInstance, businessObject ) as T
            }
            BuyerViewModelTypeEnum.RequestDishViewModel -> {
                return RequestDishViewModel(RepositoryInstance.buyerRepositoryInstance, businessObject ) as T
            }
            else -> {
                return viewModel as T
            }
        }

    }

}










class BuyerViewModelFactoryBOList(private val viewModelType: BuyerViewModelTypeEnum, private val businessObjects: ArrayList<BusinessObject>) : ViewModelProvider.NewInstanceFactory(){

    private lateinit var viewModel: ViewModel

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when (viewModelType) {
            BuyerViewModelTypeEnum.RatingAndReviewPopupViewModel -> {
                RatingAndReviewPopupViewModel(RepositoryInstance.buyerRepositoryInstance, businessObjects) as T
            }
            BuyerViewModelTypeEnum.CheckOutStockViewModel -> {
                CheckOutStockViewModel(RepositoryInstance.buyerRepositoryInstance, businessObjects) as T
            }
            else -> {
                viewModel as T
            }
        }
    }

}



