package com.blez.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.Root(){


        get("/") {
            call.respond("Welcome to my game server.")
        }

}