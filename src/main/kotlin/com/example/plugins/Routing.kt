package com.example.plugins

import com.example.repository.insertInTable
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.example.routes.UserRoutes
import com.example.routes.NoteRoutes


fun Application.configureRouting() {
    routing {

        val db = insertInTable()

        UserRoutes(db)
        NoteRoutes(db)
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
