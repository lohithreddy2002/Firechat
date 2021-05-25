package com.example.firechat

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.firechat.Chat
import com.example.firechat.Interface
import kotlinx.coroutines.delay
import java.util.Collections.reverse


val m = mutableListOf(
    Chat(listOf("hai"),"Lohith reddy"),
    Chat(listOf("hello","wassup"),"Lohith"),
    Chat(listOf("hai"),"Lohith reddy"),
    Chat(listOf("hello","wassup"),"Lohith"),
    Chat(listOf("hai"),"Lohith reddy"),

    Chat(listOf("hello","wassup"),"Lohith"),


    )

class repo:Interface {
    override suspend fun getmessages(): List<Chat> {
        delay(1000)
        return  m;
    }

    override suspend fun sendmessage(chat: Chat) {
        m.add(chat)
        Log.d("m", "${m.size}")
    }

}