package com.example.data.model

import org.jetbrains.exposed.sql.Table

object DoTable: Table() {

    val id = varchar("id", 512)
    val userEmail = varchar("userEmail", 512).references(UserTable.email)
    val description = text("Description")
    val date = long("date")

    override val primaryKey: PrimaryKey = PrimaryKey(id, userEmail)


}