package com.example.nextdoormvvm.seller.ui.dish.newdish

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

class AddDishFragment : Fragment() {

    companion object {
        fun newInstance() = AddDishFragment()
    }

    private lateinit var viewModel: AddDishViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_dish_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        postNewDish()
    }
    /* working code acll from appropriate method*/
    private fun postNewDish() {
        val newDishPost = ObjectGenerator.generateNewDishPost()
        val viewModel = SellerModelGenerator.getModel(
            this,
            SellerViewModelTypeEnum.AddDishViewModel,
            newDishPost
        ) as AddDishViewModel

        GlobalScope.launch(Dispatchers.Main) {
            val newDishResponse = viewModel.upsertDish.await()
            //textView.text = newDishResponse.toString()
        }
    }


}
