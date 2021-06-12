package com.example.firechat


var list = mutableListOf<Chat>(Chat("Hai","lohith reddy","",1))

class repo(val database:database):Interface,dao {
    override suspend fun getmessages(): List<Chat> {
        return list

    }

    override suspend fun sendmessage(chat: Chat) {
        list.add(chat)

    }

    override suspend fun insertmessage(cacac: User, chat: Chat) {
        database.getdao().insertmessage(cacac,chat)
    }

    override suspend fun deletemessage(cacac: User, chat: Chat) {
        database.getdao().deletemessage(cacac,chat)
    }

    override suspend fun getusermessages(userid: Int): List<userwithmessages> {
       return database.getdao().getusermessages(userid)
    }

}