package com.example.nextdoormvvm.user.ui.verifyuser


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.internal.Utility
import com.example.nextdoormvvm.internal.Validator
import com.example.nextdoormvvm.user.ui.UserBaseFragment
import com.example.nextdoormvvm.user.ui.otp.VerifyOtpFragment
import com.example.nextdoormvvm.user.ui.otp.VerifyOtpFragment.Companion.KEY_MOBILE_NUMBER
import kotlinx.android.synthetic.main.verify_user_by_mobile_number_fragment.*


class VerifyUserByMobileNumberFragment : UserBaseFragment(), View.OnClickListener {

    private lateinit var mEtMobileNumber: EditText
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.verify_user_by_mobile_number_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_next_activity_verification.setOnClickListener(this)
        mEtMobileNumber = et_mobile_number_verification
    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_next_activity_verification -> {
                val mobileNumber = mEtMobileNumber.text.toString()

                if (Validator.validateMobileNumber(mobileNumber).isNullOrEmpty()) {
                    val map=mapOf(KEY_MOBILE_NUMBER to mobileNumber)
                    gotoFragmentWithBundleData(
                        fragmentClass = VerifyOtpFragment::class.java,
                        fragmentActivity = this.activity,
                        value = map
                    )
                } else {
                    Utility.toastThis(Validator.validateMobileNumber(mobileNumber))
                }

            }
        }
    }


}
