package com.blez.other

import java.io.File

val words = readWordList("resources/programmers_wordlist.txt")

fun readWordList(filename : String) : List<String>{
    val inputStream = File(filename).inputStream()
    val words = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine {
        words.add(it)
    }
    return words
}

fun getRandomWords(amount : Int) : List<String>{
  var currentAmount = 0
    val result = mutableListOf<String>()
    while (currentAmount < amount){
        val word = words.random()
        if(!result.contains(word)){
            result.add(word)
            currentAmount++
        }
    }
    return result
}

fun String.transformToUnderscores() = toCharArray().map {
    if(it != ' ') '_' else ' '
}.joinToString(" ")


