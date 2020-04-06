package com.example.nextdoormvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nextdoormvvm.buyer.dao.BuyerDao
import com.example.nextdoormvvm.buyer.network.post.PurchaseOrder
import com.example.nextdoormvvm.buyer.network.post.UpiPaymentDetail
import com.example.nextdoormvvm.user.network.response.UserInfoResponse


@Database(entities = [UserInfoResponse::class, PurchaseOrder::class, UpiPaymentDetail::class],  version = 2)
abstract class NextDoorDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun buyerDao(): BuyerDao

    companion object {
        @Volatile private var instance: NextDoorDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, NextDoorDatabase::class.java, "nextdoor.db").fallbackToDestructiveMigration().build()
    }
}