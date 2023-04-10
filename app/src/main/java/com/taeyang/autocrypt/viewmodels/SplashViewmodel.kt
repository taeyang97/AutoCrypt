package com.taeyang.autocrypt.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taeyang.autocrypt.database.model.DBCenterData
import com.taeyang.autocrypt.model.CenterData
import com.taeyang.autocrypt.model.Data
import com.taeyang.autocrypt.repository.CenterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewmodel @Inject constructor(
    private val centerRepository: CenterRepository,
): ViewModel(){

    private val _center : MutableStateFlow<CenterData> = MutableStateFlow(CenterData.Empty)
    val center : StateFlow<CenterData> = _center
    suspend fun getCenterData(
        page: Int,
        perPage: Int
    ) = viewModelScope.launch {
        _center.value = centerRepository.getBestAPI(
            page,
            perPage
        )
    }

    suspend fun insertCenters(centers: Data)= viewModelScope.launch {
        val dbCenter = DBCenterData(
            id = centers.id,
            address = centers.address,
            centerName = centers.centerName,
            centerType = centers.centerType,
            facilityName = centers.facilityName,
            createdAt = centers.createdAt,
            phoneNumber = centers.phoneNumber,
            updatedAt = centers.updatedAt,
            lat = centers.lat,
            lng = centers.lng
        )
        centerRepository.insert(dbCenter)
    }

}