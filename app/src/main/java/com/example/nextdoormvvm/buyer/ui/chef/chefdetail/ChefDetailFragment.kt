package com.example.nextdoormvvm.buyer.ui.chef.chefdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.buyer.network.injection.BuyerModelGenerator
import com.example.nextdoormvvm.buyer.network.injection.BuyerViewModelTypeEnum
import com.example.nextdoormvvm.manualunittesting.ObjectGenerator
import kotlinx.android.synthetic.main.chef_detail_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ChefDetailFragment : Fragment() {

    companion object {
        fun newInstance() =
            ChefDetailFragment()
    }

    private lateinit var viewModel: ChefDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chef_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //postChefFollower()

        updateChefFollower()
    }


    //This is working code, Just call this code from respective Fragment/ Activity. This model (ProductDetailViewModel) dont have any supporting ui so we are keeping the code here.
    private fun postChefFollower() {
        val followChef =  ObjectGenerator.generatePostChefFollowerObject()

        val model = BuyerModelGenerator.getModel(
            this,
            BuyerViewModelTypeEnum.ChefDetailViewModel,
            followChef
        ) as ChefDetailViewModel

        GlobalScope.launch(Dispatchers.Main) {
            try{
                val httpResponseMessage = model.postChefFollower.await()
                textViewChefDetail.text = httpResponseMessage.toString()
            }catch (e: HttpException) {
                textViewChefDetail.text = e.response().raw().toString()
            }
            catch (e: IOException) {
                textViewChefDetail.text = e.toString()
            }
        }
    }


    //This is working code, Just call this code from respective Fragment/ Activity. This model (ProductDetailViewModel) dont have any supporting ui so we are keeping the code here.
    private fun updateChefFollower() {
        val unFollowChef =  ObjectGenerator.generateUpdateChefFollowerObject()

        val model = BuyerModelGenerator.getModel(
            this,
            BuyerViewModelTypeEnum.ChefDetailViewModel,
            unFollowChef
        ) as ChefDetailViewModel

        GlobalScope.launch(Dispatchers.Main) {
            try{
                val httpResponseMessage = model.postChefFollower.await()
                textViewChefDetail.text = httpResponseMessage.toString()
            }catch (e: HttpException) {
                textViewChefDetail.text = e.response().raw().toString()
            }
            catch (e: IOException) {
                textViewChefDetail.text = e.toString()
            }
        }
    }

}
