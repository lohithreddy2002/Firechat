package com.example.firechat

import androidx.room.*


@Entity
data class User(

    val Name:String,
    @PrimaryKey
    val userid:Int
    )

data class userwithmessages(
    @Embedded val user:User,
    @Relation(
        parentColumn = "userid",
        entityColumn = "touserid",
    )
    val messages:List<Chat>
)