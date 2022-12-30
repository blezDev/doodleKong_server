package com.blez.other

import com.blez.data.models.ChatMessage

fun ChatMessage.matchesWord(word : String) : Boolean {
    return message.lowercase().trim() == word.lowercase().trim()
}