package com.example.nextdoormvvm.common.ui.launch

import androidx.lifecycle.ViewModel
import com.example.nextdoormvvm.common.network.post.PostDishViewed
import com.example.nextdoormvvm.common.repository.CommonRepository
import com.example.nextdoormvvm.internal.BusinessObject
import com.example.nextdoormvvm.internal.lazyDeferred


@Suppress("UNCHECKED_CAST")
class LaunchActivityViewModel(private val commonRepository: CommonRepository, private val businessObjects: ArrayList<BusinessObject> ): ViewModel() {

    val insertDishViewed by lazyDeferred {
        commonRepository.postDishViewed(businessObjects as  ArrayList<PostDishViewed> )
    }

}


// This is working code, Just call this code from respective Fragment/ Activity. This model (LaunchActivityViewModel) dont have any
// supporting ui so we are keeping the code here.

//    private fun PostDishViewed() {
//        val dishViewedList =  ObjectGenerator.generateDishViewedPost()
//
//        val model = CommonModelGenerator.getModelNewNew(
//            this,
//            CommonViewModelTypeEnum.LaunchActivityViewModel,
//            this.context!!,
//            dishViewedList
//        ) as LaunchActivityViewModel
//
//        GlobalScope.launch(Dispatchers.Main) {
//            val userInfoResponse = model.insertDishViewed.await()
//            textView.text = userInfoResponse.toString()
//
//        }
//    }

