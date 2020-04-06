package com.example.nextdoormvvm.user.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.internal.ScopedFragment
import com.example.nextdoormvvm.user.ui.apartments.ApartmentListFragment
import com.example.nextdoormvvm.user.ui.otp.VerifyOtpFragment
import com.example.nextdoormvvm.user.ui.registerbuyer.RegisterBuyerFragment

open class UserBaseFragment : ScopedFragment() {
    companion object {

        fun gotoFragment(fragmentInstance: Fragment, appCompatActivity: AppCompatActivity?) {
            val fragmentTransaction = appCompatActivity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction!!.replace(R.id.user_content, fragmentInstance).commit()
        }

        fun<T : Fragment?> gotoFragmentWithBundleData(
            fragmentClass: Class<T>,
            fragmentActivity: FragmentActivity?,
            value: Map<String,Any>
        ) {
            lateinit var fragment: Fragment
            when (fragmentClass) {
                VerifyOtpFragment::class.java -> fragment = VerifyOtpFragment.newInstance(value)
                ApartmentListFragment::class.java -> fragment = ApartmentListFragment.newInstance(value)
                RegisterBuyerFragment::class.java -> fragment = RegisterBuyerFragment.newInstance(value)
                else -> print("otherwise")
            }
            val fragmentManager = fragmentActivity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction!!.replace(R.id.user_content, fragment).addToBackStack(null).commit()
        }

    }
}