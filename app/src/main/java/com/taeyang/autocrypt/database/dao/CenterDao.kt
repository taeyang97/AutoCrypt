package com.taeyang.autocrypt.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.taeyang.autocrypt.database.model.DBCenterData

@Dao
interface CenterDao {

    @Query("SELECT * FROM centers")
    suspend fun getAll() : List<DBCenterData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DBCenterData)

}