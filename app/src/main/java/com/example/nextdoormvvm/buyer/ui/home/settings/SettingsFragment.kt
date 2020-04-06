package com.example.nextdoormvvm.buyer.ui.home.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController

import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.common.ui.orderhistory.OrderHistoryViewModel
import com.example.nextdoormvvm.internal.ScopedFragment
import com.example.nextdoormvvm.internal.SocialMedia
import com.example.nextdoormvvm.internal.Utility
import com.example.nextdoormvvm.internal.Utility.CURRENT_DATE
import com.example.nextdoormvvm.internal.Utility.NETWORK_STANDARD_TIME_2
import com.example.nextdoormvvm.internal.Utility.USER_TYPE_ID
import com.example.nextdoormvvm.internal.Utility.currentDate
import kotlinx.android.synthetic.main.settings_fragment.*

class SettingsFragment : ScopedFragment(),View.OnClickListener {

    companion object {
        fun newInstance() =
            SettingsFragment()
    }

    private lateinit var viewModel: OrderHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_current_order.setOnClickListener(this)
        tv_order_history.setOnClickListener(this)
        tv_invite_neighbor.setOnClickListener(this)
        tv_edit_profile.setOnClickListener(this)
        tv_contact_us.setOnClickListener(this)
        tv_about_us.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        when(v){
            tv_current_order -> {
                val bundle = bundleOf(CURRENT_DATE to currentDate(NETWORK_STANDARD_TIME_2),
                    USER_TYPE_ID to Utility.UserType.BUYER.value)
                findNavController().navigate(R.id.action_settingsFragment_to_orderHistoryFragment,bundle)
            }
            tv_order_history ->{
                val bundle = bundleOf(CURRENT_DATE to "",
                    USER_TYPE_ID to Utility.UserType.BUYER.value)
                findNavController().navigate(R.id.action_settingsFragment_to_orderHistoryFragment,bundle)
            }
            tv_invite_neighbor -> {
                val socialMedia = SocialMedia()
                //socialMedia.shareWIthWhatsAppOrChooserResult(this,"Test string","http://youtube.com/")
                socialMedia.shareWIthWhatsAppOrChooser(
                    activity!!,
                    "Soumen Test string",
                    "http://youtube.com/"
                )
            }
            tv_edit_profile ->{
                val bundle = bundleOf(CURRENT_DATE to "",
                    USER_TYPE_ID to Utility.UserType.BUYER.value)
                findNavController().navigate(R.id.action_settingsFragment_to_editProfileFragment,bundle)
            }
        }
    }


}
