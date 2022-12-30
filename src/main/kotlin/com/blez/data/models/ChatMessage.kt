package com.blez.data.models

import com.blez.other.Constants.TYPE_CHAT_MESSAGE

data class ChatMessage(
    val from : String,
    val roomName : String,
    val message : String,
    val timeStamp : Long
) : BaseModel(TYPE_CHAT_MESSAGE)
