package com.example.plugins

import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }
}
