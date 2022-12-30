package com.blez.routes

import com.blez.data.Room
import com.blez.data.models.BasicApiResponse
import com.blez.data.models.CreateRoomRequest
import com.blez.data.models.RoomResponse
import com.blez.other.Constants.MAX_ROOM_SIZE
import com.blez.server
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.math.sign

fun Route.CreateRoomRoute(){

        post("/api/createRoom") {
            val roomRequest = call.receive<CreateRoomRequest>()
            if(roomRequest == null){
                call.respond(HttpStatusCode.BadRequest)
              return@post
            }
            if(server.rooms[roomRequest.name] != null){
                    call.respond(HttpStatusCode.OK,
                    BasicApiResponse(false,"Room already exists."))
                    return@post
                }
            if(roomRequest.maxPlayer < 2){
                call.respond(
                    HttpStatusCode.OK,
                    BasicApiResponse(false,"The minimum room size is 2.")
                )
            }
            if(roomRequest.maxPlayer >MAX_ROOM_SIZE){
                call.respond(HttpStatusCode.OK,
                BasicApiResponse(false,"The maximum room size is ${MAX_ROOM_SIZE}"))
                return@post
            }
            val room = Room(
                roomRequest.name,
                roomRequest.maxPlayer
            )
            server.rooms[roomRequest.name] = room
            println("Room created : ${roomRequest.name}")
            call.respond(HttpStatusCode.OK,BasicApiResponse(true))



        }
    }

fun Route.getRoomsRoute(){
    get("/api/getRooms") {
        val searchQuery = call.parameters["searchQuery"]
        if(searchQuery == null)
        {
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }
        val roomsResult = server.rooms.filterKeys {
            it.contains(searchQuery, ignoreCase = true)
        }
        val roomResponses = roomsResult.values.map {
            RoomResponse(it.name,it.maxPlayer,it.players.size)
        }.sortedBy { it.name }

        call.respond(HttpStatusCode.OK,roomResponses)
    }
}

fun Route.joinRoomRoute(){
    get("/api/joinRoom"){
        val username = call.parameters["username"]
        val roomName = call.parameters["roomName"]
        if( username==null || roomName == null){
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }
        val room = server.rooms[roomName]
        when{
            room ==null ->{
                call.respond(HttpStatusCode.OK,BasicApiResponse(false,"Room not found."))
            }
            room.containsPlayer(username)->{
                call.respond(HttpStatusCode.OK,BasicApiResponse(false,"A Player with this username already joined."))
            }

           room.players.size >= room.maxPlayer->{
               call.respond(HttpStatusCode.OK,BasicApiResponse(false,"This room is already full."))
           }
            else ->{
                call.respond(HttpStatusCode.OK,BasicApiResponse(true))
            }


        }

    }
}

