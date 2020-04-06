package com.example.nextdoormvvm.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nextdoormvvm.user.network.response.CURRENT_USER_ID
import com.example.nextdoormvvm.user.network.response.UserInfoResponse


@Dao
interface UserDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(userInfo: UserInfoResponse)

    @Query("select * from user_info where id = $CURRENT_USER_ID")
    fun getUserInfo(): LiveData<UserInfoResponse>



    @Query("select * from user_info where id = $CURRENT_USER_ID")
    fun fetchUserInfoFromDB(): UserInfoResponse


    @Query("UPDATE user_info SET ProfileImageUrl = CASE WHEN :columnName = 'ProfileImageUrl' THEN :columnValue ELSE ProfileImageUrl END,  FullName = CASE WHEN :columnName = 'FullName' THEN :columnValue ELSE FullName END, Gender = CASE WHEN :columnName = 'Gender' THEN :columnValue ELSE Gender END, Email = CASE WHEN :columnName = 'Email' THEN :columnValue ELSE Email END WHERE UserId = 86")
    fun updateInfoAtDB(columnName: String, columnValue: String)






}