package com.example.nextdoormvvm.buyer.ui.chef.cheflist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.buyer.network.response.HomeFeedResponse
import com.example.nextdoormvvm.buyer.ui.home.listofchef.ChefListAdapter
import com.example.nextdoormvvm.internal.ScopedFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.ongoing_dishes_fragment.*


class ChefListFragment : ScopedFragment() {

     private var mHomeFeedInstance: HomeFeedResponse? = null

    //private lateinit var mHomeFeedInstance: HomeFeedResponse
    private lateinit var mChefListAdapter: ChefListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.chef_list_fragment, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHomeFeedInstance = arguments?.getParcelable("homeFeed")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.findViewById<BottomNavigationView>(R.id.buyer_bottom_nav)?.visibility = View.GONE

        initBasicSetup()
    }


    private fun initBasicSetup(){
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Chicken Biriyani"
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Chef: Soumen"

        // Just initialize recyclerview
        initChefListRecyclerView()



        // Load  Ongoing Dish Recycler View with data
        loadChefListRecyclerView()
    }


    private fun initChefListRecyclerView() {
        recyclerView_ongoing_buyer_dishes?.apply {
            layoutManager = LinearLayoutManager(activity)
            mChefListAdapter = ChefListAdapter()
            adapter = mChefListAdapter
        }
    }


    private fun loadChefListRecyclerView() {
        mHomeFeedInstance?.let {
            mChefListAdapter.submitChefListData(it)
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()

        activity?.findViewById<BottomNavigationView>(R.id.buyer_bottom_nav)?.visibility = View.VISIBLE
    }
}
