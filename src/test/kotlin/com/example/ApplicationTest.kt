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
    private val mockinsertTable = mockk<insertInTable>()

    @Test
    fun testRoot() = testApplication {

        application {
            configureRouting()
        }
        val response = client.get("/todo")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Started todo", response.bodyAsText())

    }

    @Test
    fun `find a user by email`() {//returns user

        val email = "sushmita@gmail.com"
        val user = User("sushmita@gmail.com", "Sushmita", "123")
        coEvery {
            mockinsertTable.findUserByEmail(email)
        } returns user

        var result: User?
        runBlocking {
           result =
                mockinsertTable.findUserByEmail(email)
        }


        assertEquals(result, user)


    }

    @Test
    fun `delete user`() = runBlocking {// user is not present

        val email = "sush@gmail.com"
        val pswd = "Sush1123"
        coEvery {
            mockinsertTable.deleteUser(email,pswd)
        } returns false

        val result =
            mockinsertTable.deleteUser(email,pswd)

        assertEquals(result, false)


    }

    @Test
    fun `positive delete todo`() = runBlocking {//deletes todo

        val email = "sushmita@gmail.com"
        val id = "123"

        coEvery {
            mockinsertTable.deleteToDO(id,email)
        } returns true

        val result =
            mockinsertTable.deleteToDO(id,email)

        assertEquals(result, true)


    }

    @Test
    fun `negative delete todo`() = runBlocking {//todo is not present

        val email = "sush@gmail.com"
        val id = "123"

        coEvery {
            mockinsertTable.deleteToDO(id,email)
        } returns false

        val result =
            mockinsertTable.deleteToDO(id,email)

        assertEquals(result, false)


    }
}

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
    private val mockinsertTable = mockk<insertInTable>()

    @Test
    fun testRoot() = testApplication {

        application {
            configureRouting()
        }
        val response = client.get("/todo")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Started todo", response.bodyAsText())

    }

    @Test
    fun `find a user by email`() {//returns user

        val email = "sushmita@gmail.com"
        val user = User("sushmita@gmail.com", "Sushmita", "123")
        coEvery {
            mockinsertTable.findUserByEmail(email)
        } returns user

        var result: User?
        runBlocking {
           result =
                mockinsertTable.findUserByEmail(email)
        }


        assertEquals(result, user)


    }

    @Test
    fun `delete user`() = runBlocking {// user is not present

        val email = "sush@gmail.com"
        val pswd = "Sush1123"
        coEvery {
            mockinsertTable.deleteUser(email,pswd)
        } returns false

        val result =
            mockinsertTable.deleteUser(email,pswd)

        assertEquals(result, false)


    }

    @Test
    fun `positive delete todo`() = runBlocking {//deletes todo

        val email = "sushmita@gmail.com"
        val id = "123"

        coEvery {
            mockinsertTable.deleteToDO(id,email)
        } returns true

        val result =
            mockinsertTable.deleteToDO(id,email)

        assertEquals(result, true)


    }

    @Test
    fun `negative delete todo`() = runBlocking {//todo is not present

        val email = "sush@gmail.com"
        val id = "123"

        coEvery {
            mockinsertTable.deleteToDO(id,email)
        } returns false

        val result =
            mockinsertTable.deleteToDO(id,email)

        assertEquals(result, false)


    }
}
