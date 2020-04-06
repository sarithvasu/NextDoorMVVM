package com.example.nextdoormvvm.buyer.ui.chef.testimonial

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.buyer.network.post.Testimonial
import com.example.nextdoormvvm.buyer.repository.BuyerRepository
import com.example.nextdoormvvm.internal.BusinessObject
import com.example.nextdoormvvm.internal.lazyDeferred


@Suppress("UNCHECKED_CAST")
class TestimonialViewModel(private val buyerRepository: BuyerRepository, private val businessObject: BusinessObject): ViewModel() {

    val postTestimonial by lazyDeferred {
        buyerRepository.postTestimonial(businessObject as Testimonial)
    }
}