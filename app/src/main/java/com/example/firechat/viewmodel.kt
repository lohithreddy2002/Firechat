package com.example.firechat

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.*


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

    val message = mutableStateOf("")

    fun onvaluechange(query:String){
        this.message.value = query
    }

    fun sendmessage(){
        val cal = Calendar.getInstance()
        cal.set(
            Calendar.YEAR,
            Calendar.MONTH,
            Calendar.DATE,
            Calendar.HOUR_OF_DAY,
            Calendar.MINUTE
        )
        viewModelScope.launch {
            Log.d("messages", "${repo.getusermessages(1)}")
        }
        val a = Chat(message.value.trim(),"Lohith", Calendar.DATE.toString(),cal.timeInMillis)
        viewModelScope.launch {
            repo.sendmessage(a)
            onmessagesend()
            repo.insertmessage(User("",1),a)
        }

//       col.add(
//            hashMapOf(
//               "user" to xhat.user,
//            "message" to xhat.messages
//            )
//        ).addOnCompleteListener {
//            Log.d("pire","sucess")
//        } .addOnFailureListener { e ->
//            Log.w("pire", "Error adding document", e)
//        }
    }

    fun onmessagesend(){
        this.message.value = ""
    }

    fun onemptymessage():Boolean{
        return this.message.value != ""
    }

}