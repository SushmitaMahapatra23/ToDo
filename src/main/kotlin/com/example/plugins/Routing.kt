package com.example.plugins

import com.example.repository.insertInTable
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.example.routes.UserRoutes
import com.example.routes.NoteRoutes
import io.lettuce.core.*;
import io.lettuce.core.api.sync.RedisCommands


fun Application.configureRouting() {

    val redisClient: RedisClient = RedisClient.create("redis://localhost:6379")
    val redisCommands: RedisCommands<String,String> = redisClient.connect().sync()

    routing {

        val db = insertInTable()

        UserRoutes(db,redisCommands)
        NoteRoutes(db,redisCommands)
        route("/todo") {
            get {
                call.respondText("Started todo")
            }

            post("/{id}") {
                val id = call.parameters["id"]

                call.respondText("$id")
            }


        }


    }
}
