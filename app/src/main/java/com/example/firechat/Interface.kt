package com.example.firechat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface Interface {
suspend fun getmessages():List<Chat>

suspend fun sendmessage(chat:Chat)
}