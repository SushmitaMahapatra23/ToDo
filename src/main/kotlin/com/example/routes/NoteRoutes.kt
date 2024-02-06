package com.example.routes

import com.example.data.model.ToDo
import com.example.repository.insertInTable
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.NoteRoutes(db:insertInTable){

    post("/addToDo"){
        val formParameters = call.receiveParameters()
        val id = formParameters["id"].toString()
        val email= formParameters["userEmail"].toString()
        val description = formParameters["description"].toString()
        val date = formParameters["date"]!!.toLong()
        db.addToDo(ToDo(id, email,description,date))

        call.respond("ToDo 2 added successfully")
    }

    get("/gettodo/{email}") {
        val email = call.parameters["email"].toString()
        val todo = db.getAllToDo(email)
        call.respond(todo)
    }

    post("/deleteToDo"){
        val formParameters = call.receiveParameters()
        val id = formParameters["id"].toString()
        val email = formParameters["email"].toString()
        val b = db.deleteToDO(id,email)
        if (b)
        call.respond("ToDo with id = $id deleted successfully")
        else
            call.respond("ToDo with id = $id doesn't exists")
    }
}










