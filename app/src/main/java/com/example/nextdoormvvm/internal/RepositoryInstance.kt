package com.example.nextdoormvvm.internal

import com.example.nextdoormvvm.buyer.network.apiservice.BuyerApiService
import com.example.nextdoormvvm.buyer.network.datasource.BuyerDataSourceImpl
import com.example.nextdoormvvm.buyer.repository.BuyerRepositoryImpl
import com.example.nextdoormvvm.common.network.apiservice.CommonApiService
import com.example.nextdoormvvm.common.network.datasource.CommonDataSourceImpl
import com.example.nextdoormvvm.common.repository.CommonRepositoryImpl
import com.example.nextdoormvvm.internal.interceptor.ConnectivityInterceptorImpl
import com.example.nextdoormvvm.seller.network.apiservice.SellerApiService
import com.example.nextdoormvvm.seller.network.datasource.SellerDataSourceImpl
import com.example.nextdoormvvm.seller.repository.SellerRepositoryImpl
import com.example.nextdoormvvm.user.network.apiservice.UserApiService
import com.example.nextdoormvvm.user.network.datasource.UserDataSourceImpl
import com.example.nextdoormvvm.user.repository.UserRepositoryImpl

class RepositoryInstance {

    companion object {
        val buyerRepositoryInstance: BuyerRepositoryImpl by lazy {
            val apiService = RetrofitBuilder(ConnectivityInterceptorImpl(), BuyerApiService::class.java)
            val buyerDataSource = BuyerDataSourceImpl(apiService)
            BuyerRepositoryImpl(buyerDataSource)
        }

        val sellerRepositoryInstance: SellerRepositoryImpl by lazy {
            val apiService = RetrofitBuilder(ConnectivityInterceptorImpl(), SellerApiService::class.java)
            val sellerDataSource = SellerDataSourceImpl(apiService)
            SellerRepositoryImpl(sellerDataSource)
        }

        val userRepositoryInstance: UserRepositoryImpl by lazy {
            val apiService = RetrofitBuilder(ConnectivityInterceptorImpl(), UserApiService::class.java)
            val userDataSource = UserDataSourceImpl(apiService)
            UserRepositoryImpl(userDataSource)
        }


        val commonRepositoryInstance: CommonRepositoryImpl by lazy {
            val apiService = RetrofitBuilder(ConnectivityInterceptorImpl(), CommonApiService::class.java)
            val commonDataSource = CommonDataSourceImpl(apiService)
            CommonRepositoryImpl(commonDataSource)
        }
    }
}
