package com.example.repository

import com.example.data.model.User
import com.example.data.model.ToDo
import com.example.data.model.DoTable

import com.example.data.model.UserTable
import com.example.repository.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class insertInTable {

    suspend fun insert(user:User) {
        dbQuery {
            UserTable.insert {
                it[email] = user.email
                it[name] = user.name
                it[password] = user.password
            }
        }
    }

    private  fun deleteToDoEmail(email:String)
    {
            DoTable.deleteWhere {
                DoTable.userEmail.eq(email)
            }

        }


    suspend fun deleteUser(email:String,password:String) =
        dbQuery {
            deleteToDoEmail(email)
            UserTable.deleteWhere {
                    UserTable.password.eq(password) and UserTable.email.eq(email)
                } > 0
        }


        private fun rowToUser(row: ResultRow?):User?
        {
            if(row==null)
                return null
            return User(
                email = row[UserTable.email],
                name = row[UserTable.name],
                password = row[UserTable.password]
            )
        }

    suspend fun findUserByEmail(email:String)
     =
        dbQuery {
            UserTable.select{
                UserTable.email.eq(email)
            }.map { rowToUser(it) }
                .singleOrNull()
        }




    suspend fun updateToDo(todo:ToDo, email: String){
        dbQuery {
            DoTable.update(
                where = {
                    DoTable.userEmail.eq(todo.userEmail) and DoTable.id.eq(todo.id)
                }
            ){
                it[description] = todo.description
                it[date] = todo.date
            }
        }
    }

    suspend fun deleteToDO(id:String,email: String)
    =
        dbQuery {
            DoTable.deleteWhere {
                DoTable.id.eq(id) and DoTable.userEmail.eq(email)
            }>0

        }

    suspend fun addToDo(todo:ToDo) {
        dbQuery {
            DoTable.insert {
                it[userEmail] = todo.userEmail
                it[id] = todo.id
                it[description] = todo.description
                it[date] = todo.date
            }
        }
    }

    private fun rowToDos(row: ResultRow?):ToDo?
    {
        if(row==null)
            return null
        return ToDo(
            userEmail = row[DoTable.userEmail],
            id = row[DoTable.id],
            date = row[DoTable.date],
            description = row[DoTable.description]
        )
    }

    suspend fun getAllToDo(email:String): List<ToDo>
            =
        dbQuery {
            DoTable.select{
                DoTable.userEmail.eq(email)
            }.mapNotNull { rowToDos(it) }

        }
}