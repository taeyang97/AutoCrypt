package com.taeyang.autocrypt.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.taeyang.autocrypt.database.dao.CenterDao
import com.taeyang.autocrypt.database.model.DBCenterData

@Database(
    entities = [DBCenterData::class],
    version = 1,
    exportSchema = true
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun centersDao(): CenterDao
}