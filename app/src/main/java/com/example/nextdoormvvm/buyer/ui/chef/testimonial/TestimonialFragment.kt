package com.example.nextdoormvvm.buyer.ui.chef.testimonial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.buyer.network.injection.BuyerModelGenerator
import com.example.nextdoormvvm.buyer.network.injection.BuyerViewModelTypeEnum
import com.example.nextdoormvvm.manualunittesting.ObjectGenerator
import kotlinx.android.synthetic.main.testimonial_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TestimonialFragment : Fragment() {

    companion object {
        fun newInstance() =
            TestimonialFragment()
    }

    private lateinit var viewModel: TestimonialViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.testimonial_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        PostTestimonial()
    }


    //This is working code, Just call this code from respective Fragment/ Activity. This model (ProductDetailViewModel) dont have any supporting ui so we are keeping the code here.
    private fun PostTestimonial() {
        val testimonial =  ObjectGenerator.generatePostTestimonialObject()

        val model = BuyerModelGenerator.getModel(
            this,
            BuyerViewModelTypeEnum.TestimonialViewModel,
            testimonial
        ) as TestimonialViewModel

        GlobalScope.launch(Dispatchers.Main) {
            val httpResponseMessage = model.postTestimonial.await()
            textViewTestimonial.text = httpResponseMessage.toString()
        }
    }





}
