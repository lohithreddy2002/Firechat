package com.example.firechat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class viewmodel(val repo:repo):ViewModel() {

    private val _chat:MutableLiveData<List<Chat>> = MutableLiveData()
    val chat:LiveData<List<Chat>> = _chat

    init {
        getallmessages()
    }
    fun getallmessages(){
        viewModelScope.launch {
            val a = repo.getmessages()
            _chat.postValue(a)
            Log.d("a","${a.size}")
            Log.d("asa","${chat.value}")
        }
    }

    fun sendmessage(xhat: Chat){
        viewModelScope.launch {
            repo.sendmessage(xhat)

            _chat.postValue(m)
            Log.d("asa","${_chat.value}")

        }
    }

}