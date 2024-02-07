package com.example.routes

import com.example.data.model.ToDo
import com.example.data.model.User
import com.example.repository.insertInTable
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.lettuce.core.api.sync.RedisCommands
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
fun Route.NoteRoutes(db:insertInTable,redisCommands: RedisCommands<String,String>){

    post("/addToDo"){
        val formParameters = call.receiveParameters()
        val id = formParameters["id"].toString()
        val email= formParameters["userEmail"].toString()
        val description = formParameters["description"].toString()
        val date = formParameters["date"]!!.toLong()
        db.addToDo(ToDo(id, email,description,date))
        redisCommands.del("gettodo:$email")
        call.respond("ToDo 2 added successfully")
    }

    get("/gettodo/{email}") {
        val email = call.parameters["email"].toString()

        val cachedData = redisCommands.get("gettodo:$email")
        if (cachedData != null) {
            redisCommands.del("gettodo:$email")
            call.respond(cachedData)
        } else {
            val todo = db.getAllToDo(email)
            if (todo.size!=0) {

                redisCommands.set("gettodo:$email", todo.toString())
                call.respond(todo)
            } else {
                call.respond("No todos for $email")
            }
        }
    }

    post("/deleteToDo"){
        val formParameters = call.receiveParameters()
        val id = formParameters["id"].toString()
        val email = formParameters["email"].toString()
        val b = db.deleteToDO(id,email)

        redisCommands.del("gettodo:$email")
        if (b)
        call.respond("ToDo with id = $id deleted successfully")
        else
            call.respond("ToDo with id = $id doesn't exists")
    }
}










