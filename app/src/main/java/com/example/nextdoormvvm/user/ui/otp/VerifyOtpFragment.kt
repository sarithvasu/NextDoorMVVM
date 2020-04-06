package com.example.nextdoormvvm.user.ui.otp


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.common.ui.CommonActivity
import com.example.nextdoormvvm.internal.Preference
import com.example.nextdoormvvm.user.network.injection.UserModelGenerator
import com.example.nextdoormvvm.user.network.injection.UserViewModelTypeEnum
import com.example.nextdoormvvm.user.network.response.UserInfoResponse
import com.example.nextdoormvvm.user.ui.UserBaseFragment
import com.example.nextdoormvvm.user.ui.apartments.ApartmentListFragment
import kotlinx.android.synthetic.main.fragment_verify_otp.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException


class VerifyOtpFragment : UserBaseFragment() {

    private lateinit var viewModel: VerifyOtpViewModel
    private lateinit var phoneNo: String

    companion object {
        const val KEY_MOBILE_NUMBER = "mobileNumber"
        fun newInstance(values: Map<String, Any>): VerifyOtpFragment {
            val thisFragment = VerifyOtpFragment()
            val args = Bundle()
            args.putString(KEY_MOBILE_NUMBER, values[KEY_MOBILE_NUMBER].toString())
            thisFragment.arguments = args
            return thisFragment
        }
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val args = arguments

        args?.let {
            phoneNo = it.getString(KEY_MOBILE_NUMBER, "")
        }
        return inflater.inflate(R.layout.fragment_verify_otp, container, false)
    }



    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.textView114.text = "+91-$phoneNo"
        verifyUserByMobileNumberApi()

    }


    private fun verifyUserByMobileNumberApi() {
        //busy_bar.visibility = View.VISIBLE

        val queryParameterMap = mapOf(KEY_MOBILE_NUMBER to "91$phoneNo")
        viewModel = UserModelGenerator.getModel( this,UserViewModelTypeEnum.VerifyOtpViewModel,queryParameterMap) as VerifyOtpViewModel


        GlobalScope.launch(Dispatchers.Main) {
            try {
                val userInfoResponse = viewModel.fetchUserInfoByMobile.await()

                userInfoResponse.observe(viewLifecycleOwner, Observer {
                    if (it == null) return@Observer
                    Preference.saveUserId(it.UserId)
                    val intent=Intent(activity, CommonActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)

                })






//                /*   if(userInfoResponse!=null)
//                    startActivity(Intent(activity,CommonActivity::class.java))*/
//                userInfoResponse.removeObservers(viewLifecycleOwner)
//                userInfoResponse.observe(viewLifecycleOwner, object : Observer<UserInfoResponse>{
//                    override fun onChanged(t: UserInfoResponse?) {
//
//                        if(t==null) return else {
//                            Preference.saveUserId(t.UserId)
//                            val intent=Intent(activity, CommonActivity::class.java)
//                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                            startActivity(intent)
//                        }
//                        /* removed the obserer to prevent the multple call*/
//                        userInfoResponse.removeObserver(this)
//                    }
//
//                })
//             //       busy_bar.visibility = View.GONE





            } catch (e: HttpException) {
                //busy_bar.visibility = View.GONE
                if (e.response()?.code() == 404) {
                    handleMsg(e)
                }

            } catch (e: IOException) {
               // busy_bar.visibility = View.GONE
            }
        }
    }

    private fun handleMsg(e: HttpException) {
        try {
            if (e.response()?.errorBody() != null) {
                val bodyStr = e.response()?.errorBody()!!.string()
                val jObjError = JSONObject(bodyStr)
                val msg = jObjError.getString("Message")
                val map=mapOf(KEY_MOBILE_NUMBER to phoneNo)
                if (msg.isNotEmpty() && msg.contains(phoneNo)) {
                    gotoFragmentWithBundleData(
                        ApartmentListFragment::class.java,
                        activity,map
                    )
                }
            }
        } catch (e: JSONException) {

        }
    }

}
