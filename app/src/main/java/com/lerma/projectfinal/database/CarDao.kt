package com.lerma.projectfinal.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CarDao {
    @Insert
    fun insert(car: Car)

    @Query("SELECT * FROM car")
    fun getAll(): List<Car>

    @Query("DELETE FROM car WHERE id=:idCar")
    fun delCarId(idCar: Int)

    @Query("DELETE FROM car WHERE 1 = 1")
    fun delCar()
}