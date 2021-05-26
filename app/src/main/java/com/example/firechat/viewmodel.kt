package com.example.firechat

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


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
        val a = Chat(this.message.value,"Lohith")
        viewModelScope.launch {
            repo.sendmessage(a)
            Log.d("asa","${_chat.value}")
            onmessagesend()
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