package com.taeyang.autocrypt.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taeyang.autocrypt.database.model.DBCenterData
import com.taeyang.autocrypt.repository.CenterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewmodel @Inject constructor(
    private val centerRepository: CenterRepository
): ViewModel(){

    private val _center : MutableStateFlow<List<DBCenterData>> = MutableStateFlow(listOf())
    val center : StateFlow<List<DBCenterData>> = _center

    suspend fun getDBCenterData() = viewModelScope.launch{
        _center.value = centerRepository.getCenters()
    }

}