package com.example.firechat

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.firechat.Chat
import com.example.firechat.Interface
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import java.util.Collections.reverse




class repo:Interface {
    val m = mutableListOf<Chat>(Chat("lac,sa","cswa"))
    override suspend fun getmessages(): List<Chat> {

        val db = Firebase.firestore
        val col = db.collection("Rooms").document("tolohithreddy").collection("messages")

//        col.addSnapshotListener { value, error ->
//            if (value != null) {
//                for(a in value){
//                    var x = Chat("${a.data.get("user")}","${a.data.get("message")}")
//                    Log.d("value","$x")
//                    m.add(x)
//                    Log.d("values","$m")
//                }
//            }
//        }

        return  m;
    }

    override suspend fun sendmessage(chat: Chat) {

        m += chat
        Log.d("m", "${m.size}")
    }

}