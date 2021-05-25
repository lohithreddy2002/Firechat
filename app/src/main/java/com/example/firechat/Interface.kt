package com.example.firechat

import androidx.lifecycle.LiveData

interface Interface {
suspend fun getmessages():List<Chat>

suspend fun sendmessage(chat:Chat)
}