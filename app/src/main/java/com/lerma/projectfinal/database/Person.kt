package com.lerma.projectfinal.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Person {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name="name")
    var name: String = ""
    @ColumnInfo(name="last_name")
    var lastName: String = ""
    @ColumnInfo(name="adress")
    var adress: String = ""
    @ColumnInfo(name="email")
    var email: String = ""
    @ColumnInfo(name="userName")
    var userName: String = ""
    @ColumnInfo(name="passWord")
    var passWord: String = ""
}