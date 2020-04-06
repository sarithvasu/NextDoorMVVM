package com.example.nextdoormvvm.user.ui.editprofile

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.internal.Preference
import com.example.nextdoormvvm.internal.ScopedFragment
import com.example.nextdoormvvm.internal.Utility
import com.example.nextdoormvvm.user.network.injection.UserModelGenerator
import com.example.nextdoormvvm.user.network.injection.UserViewModelTypeEnum
import com.example.nextdoormvvm.user.network.response.UserInfoResponse
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.edit_profile_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

@Suppress("SENSELESS_COMPARISON", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class EditProfileFragment : ScopedFragment() {

    companion object {
        fun newInstance() = EditProfileFragment()
    }

    private lateinit var filepath: String
    private lateinit var buyerInfo: UserInfoResponse
    private var userTypeId: Int=0
    private lateinit var viewModel: EditProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_profile_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val queryParameterMap = mapOf(
            "" to ""
        )
        viewModel = UserModelGenerator.getModel( this,
            UserViewModelTypeEnum.EditProfileViewModel,queryParameterMap) as EditProfileViewModel
        loadUserInfo()

    }
    private fun loadUserInfo() = launch(Dispatchers.Main) {
         buyerInfo = viewModel.fetchUserInfoFromDB.await()
        setUI()

    }
    @SuppressLint("SetTextI18n")
    private fun setUI() {
        userTypeId = 12
       // val userId = Preference.getUserId()
       // buyerInfo = UserInfoResponse()
        if(buyerInfo!=null) {
            if (userTypeId == Utility.UserType.SELLER.value) {
                textView107.visibility = View.VISIBLE
                tv_edit_speciality_edit_profile.visibility = View.VISIBLE
                /* this should fill after becoming chef */
                tv_edit_speciality_edit_profile.setText("")
                textView102.visibility = View.VISIBLE
                textInputLayout.visibility = View.VISIBLE
                // textInputLayout.setText(buyerInfo.A)
            } else {
                textView107.visibility = View.GONE
                tv_edit_speciality_edit_profile.visibility = View.GONE
                textView102.visibility = View.GONE
                textInputLayout.visibility = View.GONE
            }
            textInputLayout.setOnClickListener(updateListener)
            tv_edit_speciality_edit_profile.setOnClickListener(updateListener)
            tv_edit_full_name_edit_profile.setText(buyerInfo.FullName)
            tv_edit_email_edit_profile.setOnClickListener(updateListener)
            tv_edit_full_name_edit_profile.setOnClickListener(updateListener)
            tv_edit_gender_edit_profile.setOnClickListener(updateListener)
            tv_edit_flat_number_edit_profile.setOnClickListener(updateListener)
            tv_mobile_number_edit_profile.setOnClickListener(updateListener)
            tv_edit_email_edit_profile.setText(buyerInfo.Email)
            tv_edit_flat_number_edit_profile.setText(buyerInfo.FlatNumber)
            tv_mobile_number_edit_profile.setText(buyerInfo.MobileNumber)
            tv_edit_gender_edit_profile.setText(buyerInfo.Gender)

            tv_address_edit_profile.text =
                buyerInfo.FlatNumber + ", " + buyerInfo.ApartmentName + ", " + buyerInfo.PinCode
            tv_edit_flat_number_edit_profile.setText(buyerInfo.FlatNumber)
            /* PicassoClient.init(this, DropboxClientFactory.init(Utility.DB_ACCESS_TOKEN)!!);
        PicassoClient.picasso?.load(ImagePiccassoRequestHadler.buildPicassoUri(Utility.SLASH +buyerInfo.profileImageUrl))!!
            .placeholder(R.drawable.profile)
            .error(R.drawable.profile)
            .into(img_profile_pic_edit_profile)*/
            tv_upload_profile_pic_edit_profile.setOnClickListener {
                onclickProfileImage()
            }

            filepath = buyerInfo.ProfileImageUrl

            tv_save_changes_edit_profile.setOnClickListener {
                //  updateUser(buyerInfo)
            }
            tv_change_address_edit_profile.setOnClickListener {
                if (Preference.getUserChefId() != -1 && userTypeId == Utility.UserType.SELLER.value)
                    updateAdress()
                else if (Preference.getUserChefId() == -1 && userTypeId == Utility.UserType.BUYER.value)
                    updateAdress()
                else
                    Utility.createCustomDialog(
                        context!!,
                        "please login as chef for change address",
                        true
                    )
            }
        }
    }


    private val updateListener=
        View.OnClickListener { view -> showUpdateDialog(Dialog(activity!!),view as TextView) }

    @SuppressLint("SetTextI18n")
    private fun showUpdateDialog(dialog: Dialog, et: TextView?) {

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.edit_profile_popup)
        (dialog.findViewById(R.id.tv_edit_popup_title) as TextView).text =et?.tag as String
        (dialog.findViewById(R.id.edit_text_popup_edit_profile) as EditText).setText(et.text.toString())
        val radioGroup1=dialog.findViewById<RadioGroup>(R.id.rg_gender_reg)



        radioGroup1.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = dialog.findViewById(checkedId)
            (dialog.findViewById(R.id.edit_text_popup_edit_profile) as EditText).setText(radio.text.toString())
        }
        when {
            et.tag as String=="Gender" -> {
                radioGroup1.visibility= View.VISIBLE
                (dialog.findViewById(R.id.edit_text_popup_edit_profile) as EditText).isFocusableInTouchMode =
                    false
            }
            et.tag as String == "SpecializedOption" -> {
                radioGroup1.visibility= View.VISIBLE
                (dialog.findViewById(R.id.edit_text_popup_edit_profile) as EditText).isFocusableInTouchMode =
                    false
                (dialog.findViewById(R.id.rb_gender_male_reg) as RadioButton).text="Veg"
                (dialog.findViewById(R.id.rb_gender_female_reg) as RadioButton).text="Non-Veg"
                (dialog.findViewById(R.id.rb_gender_other_reg) as RadioButton).text="Both"
            }
            else -> {
                radioGroup1.visibility= View.GONE
                (dialog.findViewById(R.id.edit_text_popup_edit_profile) as EditText).isFocusableInTouchMode =
                    true
            }
        }
        dialog.findViewById<Button>(R.id.button9).setOnClickListener{ dialog.dismiss() }
        (dialog.findViewById(R.id.button8) as Button).setOnClickListener{
            et.text = (dialog.findViewById(R.id.edit_text_popup_edit_profile) as EditText).text.toString()
            if(et.tag as String == "SpecializedOption")
              //  updateProfile(et?.tag as String,getSpecilization((dialog.findViewById(R.id.edit_text_popup_edit_profile) as EditText).text.toString()).toString())
            else
              //  updateProfile(et?.tag as String,(dialog.findViewById(R.id.edit_text_popup_edit_profile) as EditText).text.toString())

            dialog.dismiss()
        }
        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
    private fun getSpecilization(type:String): Int {
        return when (type) {
            "Veg" -> Utility.SpecilizeOption.VEG.value
            "Non-Veg" -> Utility.SpecilizeOption.NON_VEG.value
            "Both" -> Utility.SpecilizeOption.BOTH.value
            else -> {
                0
            }
        }
    }




    private fun updateAdress() {
       // Utility.createCustomTwoButtonDialog(this,"Change address will go through admin verification process. Your Account will be temporarily inactive for 24 to 48 hours.", View.OnClickListener{   startEditAdressScreen()     })
    }

    private fun startEditAdressScreen() {
      /*  val inte= Intent(this@EditProfileActivity,LaunchActivity::class.java)
        inte.putExtra(FROM_EDIT_PROFILE,FROM_EDIT_PROFILE)
        startActivity(inte)*/
    }



    private fun updateBuerDB() {
       // NextDoorDB.invoke(this@EditProfileActivity).daoAccess.updateBuyerProfile(user_id = id!!,fullName = tv_edit_full_name_edit_profile.text.toString(),gender=tv_edit_gender_edit_profile.text.toString(),flatNumber= tv_edit_flat_number_edit_profile.text.toString(),email= tv_edit_email_edit_profile.text.toString(),profileImageUrl = filepath,chefSpeciality =tv_edit_speciality_edit_profile.text.toString(),aboutChef = textInputLayout.text.toString() )
    }





    private fun onclickProfileImage() {
        CropImage.activity()
            .setGuidelines(CropImageView.Guidelines.ON)
            .start(activity!!)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val resultUri = result.uri
                img_profile_pic_edit_profile.setImageURI(resultUri)
                val file = File( resultUri.path)
                // val s=file.absolutePath
                //val newFile = File(file.parent+"/"+buyerInfo.profileImageUrl)
                //file.renameTo(newFile)
                filepath=file.name
                if (file != null) {
                    //Initialize UploadTask
                   /* UploadTask(
                        DropboxClient.getClient(Utility.DB_ACCESS_TOKEN!!),
                        file,
                        applicationContext
                    ).execute()*/
                }
                //updateProfile("ProfileImageUrl",filepath)

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                result.error
            }
        }
    }

}
