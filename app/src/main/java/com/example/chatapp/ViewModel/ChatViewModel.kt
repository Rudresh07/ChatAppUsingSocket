package com.example.chatapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.room.Chat
import com.example.chatapp.model.SocketHandler

class ChatViewModel : ViewModel() {

    private val socketHandler = SocketHandler()
    private val _chatList = MutableLiveData<List<Chat>>()
    val chatList: LiveData<List<Chat>> = _chatList

    init {
        socketHandler.onNewChat.observeForever { chat ->
            val newList = _chatList.value.orEmpty().toMutableList().apply {
                add(chat)
            }
            _chatList.value = newList
        }
    }

    fun sendMessage(userName: String, message: String) {
        val chat = Chat(username = userName, text = message)
        socketHandler.emitChat(chat)
    }

    override fun onCleared() {
        socketHandler.disconnectSocket()
        super.onCleared()
    }
}