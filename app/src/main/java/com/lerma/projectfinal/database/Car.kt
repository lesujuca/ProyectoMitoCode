package com.lerma.projectfinal.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Car {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name="idProduct")
    var idProduct: Int = 0
    @ColumnInfo(name="name")
    var name: String = ""
    @ColumnInfo(name="description")
    var description: String = ""
    @ColumnInfo(name="price")
    var price: Double = 0.00
    @ColumnInfo(name="image")
    var image: String = ""
}