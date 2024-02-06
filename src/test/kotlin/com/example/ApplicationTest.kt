package com.example

import com.example.data.model.User
import com.example.plugins.configureRouting
import com.example.repository.DatabaseFactory
import com.example.repository.insertInTable
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.testing.*
import kotlin.test.*
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.mockito.Mockito.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {

        application{
            configureRouting()
        }
        val response = client.get("/todo")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Started todo", response.bodyAsText())

    }
}

