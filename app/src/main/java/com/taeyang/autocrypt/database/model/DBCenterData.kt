package com.taeyang.autocrypt.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "centers")
data class DBCenterData(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "centerName") val centerName: String,
    @ColumnInfo(name = "centerType") val centerType: String,
    @ColumnInfo(name = "facilityName") val facilityName: String,
    @ColumnInfo(name = "createdAt") val createdAt: String,
    @ColumnInfo(name = "phoneNumber") val phoneNumber: String,
    @ColumnInfo(name = "updatedAt") val updatedAt: String,
    @ColumnInfo(name = "lat")val lat: String,
    @ColumnInfo(name = "lng")val lng: String
)