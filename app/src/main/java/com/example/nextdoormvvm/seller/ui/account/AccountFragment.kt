package com.example.nextdoormvvm.seller.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.seller.network.injection.SellerViewModelTypeEnum
import com.example.nextdoormvvm.seller.network.injection.SellerModelGenerator
import kotlinx.android.synthetic.main.account_fragment.*
import kotlinx.android.synthetic.main.ongoing_dishes_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AccountFragment : Fragment() {

    companion object {
        fun newInstance() = AccountFragment()
    }

    private lateinit var viewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.account_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val queryParameterMap = mapOf("chefId" to "51")

        val model = SellerModelGenerator.getModel(this, SellerViewModelTypeEnum.AccountViewModel, queryParameterMap) as AccountViewModel
        GlobalScope.launch(Dispatchers.Main){
            val ledgerFeedResponse = model.ledgerFeed.await()
            ledgerFeedResponse.observe(viewLifecycleOwner, Observer {
                textView.text = it.currentYearLedgerSummery.toString()
            })
        }
    }
}
