package com.example.chatapp.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Chat::class], version = 1, exportSchema = false)
abstract class ChatDatabase : RoomDatabase() {

    abstract fun chatMessageDao(): ChatMessageDao
}
