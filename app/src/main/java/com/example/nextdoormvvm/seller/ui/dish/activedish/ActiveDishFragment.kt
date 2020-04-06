package com.example.nextdoormvvm.seller.ui.dish.activedish

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.manualunittesting.ObjectGenerator
import com.example.nextdoormvvm.seller.network.injection.SellerModelGenerator
import com.example.nextdoormvvm.seller.network.injection.SellerViewModelTypeEnum
import kotlinx.android.synthetic.main.ongoing_dishes_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ActiveDishFragment : Fragment() {

    companion object {
        fun newInstance() = ActiveDishFragment()
    }

    private lateinit var viewModel: ActiveDishViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.active_dish_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ActiveDishViewModel::class.java)
        // TODO: Use the ViewModel
    }

    /* working code acll from appropriate method*/
    private fun postNewDish() {
        val activeDishPost = ObjectGenerator.generateActiveDishPost()
        val activewModel = SellerModelGenerator.getModel(
            this,
            SellerViewModelTypeEnum.ActiveDishViewModel,
            activeDishPost
        ) as ActiveDishViewModel

        GlobalScope.launch(Dispatchers.Main) {
            val newDishResponse = activewModel.upsertActiveDish.await()
            //textView.text = newDishResponse.toString()

        }
    }

}
