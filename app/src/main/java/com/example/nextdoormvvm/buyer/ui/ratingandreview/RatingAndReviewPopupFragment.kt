package com.example.nextdoormvvm.buyer.ui.ratingandreview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.buyer.network.injection.BuyerModelGenerator
import com.example.nextdoormvvm.buyer.network.injection.BuyerViewModelTypeEnum
import com.example.nextdoormvvm.manualunittesting.ObjectGenerator
import kotlinx.android.synthetic.main.rating_and_review_popup_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RatingAndReviewPopupFragment : Fragment() {

    companion object {
        fun newInstance() = RatingAndReviewPopupFragment()
    }

    private lateinit var viewModel: RatingAndReviewPopupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rating_and_review_popup_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


       // PostRatingAndReviewPopup()
    }



    //This is working code, Just call this code from respective Fragment/ Activity. This model (ProductDetailViewModel) dont have any supporting ui so we are keeping the code here.
    private fun PostRatingAndReviewPopup() {
        val ratingAndReviewList =  ObjectGenerator.generateRatingAndReviewPopupPost()

        val model = BuyerModelGenerator.getModel(
            this,
            BuyerViewModelTypeEnum.RatingAndReviewPopupViewModel,
            ratingAndReviewList
        ) as RatingAndReviewPopupViewModel

        GlobalScope.launch(Dispatchers.Main) {
            val httpResponseMessage = model.insertRatingAndReviewPopup .await()
            textViewRatingAndReview.text = httpResponseMessage.toString()
        }
    }

}
