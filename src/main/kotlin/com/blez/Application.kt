package com.blez

import io.ktor.server.application.*
import com.blez.plugins.*
import com.google.gson.Gson

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)
val server = DrawingServer()
val gson = Gson()
@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureSerialization()
    configureSockets()
    configureSessions()
    configureMonitoring()
    configureRouting()
}
