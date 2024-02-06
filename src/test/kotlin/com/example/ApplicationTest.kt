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

    private val insertTablemock = mockk<insertInTable>()
    @Test
    fun myfun()
    {
        assertEquals(3,3)
    }
//    @Test
//    fun `find a user by email`() = runBlocking {
//
//        val email = "sushmita@gmail.com"
//        val user = User("sushmita@gmail.com","Sushmita","123")
//        coEvery{
//            insertTablemock.findUserByEmail(email)
//        } returns user
//
//        val result =
//            insertTablemock.findUserByEmail(email)
//
//        assertEquals(result,user)
    }

