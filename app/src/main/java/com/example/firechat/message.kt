package com.example.firechat

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Messages")
data class Chat(

    val messages:String,
    val user:String,
    val date:String,
    val timeinmills:Long
    ){
    @PrimaryKey(autoGenerate = true)
    var index:Int = 0
}