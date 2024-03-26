package com.example.chatapp.View

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.Adapter.ChatAdapter
import com.example.chatapp.ViewModel.ChatViewModel
import com.example.chatapp.databinding.ActivityMainBinding

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var chatAdapter: ChatAdapter
    private val viewModel: ChatViewModel by viewModels()

    private var userName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userName = intent.getStringExtra(USERNAME) ?: ""

        if (userName.isEmpty()) {
            finish()
        } else {
            chatAdapter = ChatAdapter()
            binding.rvChat.apply {
                layoutManager = LinearLayoutManager(this@ChatActivity)
                adapter = chatAdapter
            }

            binding.btnSend.setOnClickListener {
                val message = binding.etMsg.text.toString()
                if (message.isNotEmpty()) {
                    viewModel.sendMessage(userName, message)
                    binding.etMsg.setText("")
                }
            }

            viewModel.chatList.observe(this) { chats ->
                chatAdapter.submitChat(chats)
                binding.rvChat.scrollToPosition(chats.size - 1)
            }
        }
    }

    companion object {
        const val USERNAME = "username"
    }
}
