package com.example.routes

import com.example.data.model.User
import com.example.repository.insertInTable
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.lettuce.core.api.sync.RedisCommands

fun Route.UserRoutes(db:insertInTable,redisCommands: RedisCommands<String,String>){

    post("/adduser"){
        val formParameters = call.receiveParameters()
        val email= formParameters["email"].toString()
        val name = formParameters["name"].toString()
        val password = formParameters["password"].toString()
        db.insert(User(email,name,password))
        call.respond("$name added successfully")
    }

    post("/deleteUser"){
        val formParameters = call.receiveParameters()
        val email= formParameters["email"].toString()
        val password = formParameters["password"].toString()
        val b = db.deleteUser(email,password)
        if(b) {
            call.respond("User Removed successfully")
            redisCommands.del("showuser:$email")
        }
        else
            call.respond("$email doesn't exists")
    }

    get("/showuser/{email}") {
      val email = call.parameters["email"].toString()
        val cachedData = redisCommands.get("showuser:$email")
        if(cachedData!=null)
        {
            call.respond(cachedData)
        }
        else {
            val user: User? = db.findUserByEmail(email)
            if (user != null) {
                val todojson = "{\"email\":${user.email},\"name\": ${user.name},\"password\":${user.password}}"
                redisCommands.set("showuser:$email",todojson)
                call.respond(user)
            } else {
                call.respond("User with mail id $email doesn't exists")
            }
        }
    }
}
