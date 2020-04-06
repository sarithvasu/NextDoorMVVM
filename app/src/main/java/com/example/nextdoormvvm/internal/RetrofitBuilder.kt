package com.example.nextdoormvvm.internal

import com.example.nextdoormvvm.internal.interceptor.ConnectivityInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

        private  val BASE_URL="http://192.168.2.186:8080/api/"    // Publish
    //    private  val BASE_URL="http://192.168.2.186:51461/api/"  // Local //private  val BASE_URL="http://localhost:51461/api/"

        operator fun<T> invoke(connectivityInterceptor: ConnectivityInterceptor, serviceType:Class<T>): T {
            val headerInterceptor = Interceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .header("Accept","application/json")
                    .header("Content-Type","application/json")
                    .build()
                return@Interceptor chain.proceed(request)
            }
          //  val loggingInterceptor = HttpLoggingInterceptor()
          //  loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(connectivityInterceptor)
                .addInterceptor(headerInterceptor)
               // .addInterceptor(loggingInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(serviceType)
        }
}