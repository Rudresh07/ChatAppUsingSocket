package com.example.chatapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ChatMessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: Chat)

    @Query("SELECT * FROM chat")
    suspend fun getAllMessages(): List<Chat>
}
