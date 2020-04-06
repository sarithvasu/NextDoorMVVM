package com.example.nextdoormvvm.buyer.ui.ratingandreview

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.buyer.network.post.PostRatingAndReviewPopup
import com.example.nextdoormvvm.buyer.repository.BuyerRepository
import com.example.nextdoormvvm.internal.BusinessObject
import com.example.nextdoormvvm.internal.lazyDeferred


@Suppress("UNCHECKED_CAST")
class RatingAndReviewPopupViewModel(private val buyerRepository: BuyerRepository, private val businessObjects: ArrayList<BusinessObject>): ViewModel() {

    val insertRatingAndReviewPopup by lazyDeferred {
        buyerRepository.postRatingAndReviewPopup(businessObjects as ArrayList<PostRatingAndReviewPopup>)
    }
}