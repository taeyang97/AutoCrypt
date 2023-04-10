package com.taeyang.autocrypt.model

data class CenterData(
    val currentCount: Int = 0,
    val `infoData`: List<InfoData> = listOf(),
    val matchCount: Int = 0,
    val page: Int = 0,
    val perPage: Int = 0,
    val totalCount: Int = 0
){
    companion object{
        val Empty = CenterData()
    }
}

