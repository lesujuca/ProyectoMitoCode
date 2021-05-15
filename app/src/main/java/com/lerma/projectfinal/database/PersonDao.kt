package com.lerma.projectfinal.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {
    @Insert
    fun insert(person: Person)

    @Query("SELECT * FROM person WHERE userName=:userNameEntry AND passWord=:passWordEntry")
    fun validateUser(userNameEntry: String, passWordEntry: String): Person

    @Query("SELECT * FROM person WHERE userName=:userNameEntry")
    fun existUser(userNameEntry: String): Person

    @Query("SELECT * FROM person")
    fun getAll(): List<Person>
}