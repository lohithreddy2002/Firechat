package com.example.firechat

import androidx.room.*

@Dao
interface dao {

    @Insert
   suspend fun insertmessage(cacac: User,chat: Chat)

    @Delete
    suspend fun deletemessage(cacac: User,chat: Chat)

    @Query("select * from user where userid = :userid")
    suspend fun getusermessages(userid:Int):List<userwithmessages>




}