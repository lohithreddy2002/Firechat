package com.example.firechat

import androidx.room.*

@Dao
interface dao {

    @Insert
    suspend fun insertmessage(chat: Chat)

    @Delete
    suspend fun deletemessage(cacac: User, chat: Chat)


    @Query("select * from user where Name = :Name")
    suspend fun getuser(Name: String):User

    @Transaction
    @Query("select * from user where userid = :userid")
    suspend fun getusermessages(userid: Int): List<userwithmessages>


    @Insert
    suspend fun insertuser(User:User)
}