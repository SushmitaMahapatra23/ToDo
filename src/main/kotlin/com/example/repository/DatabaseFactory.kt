package com.example.repository

import com.example.data.model.UserTable
import com.example.data.model.DoTable

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction


object DatabaseFactory {
    fun init()
    {
        Database.connect(hikari())

        transaction{
            SchemaUtils.create(UserTable)
            SchemaUtils.create(DoTable)
        }
    }

    fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.jdbcUrl = System.getenv("DATABASE_URL")
        config.driverClassName = System.getenv("JDBC_DRIVER")
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()

        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block: () -> T): T =
        withContext(Dispatchers.IO){
            transaction { block() }
        }

}