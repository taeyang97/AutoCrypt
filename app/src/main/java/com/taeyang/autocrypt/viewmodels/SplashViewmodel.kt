package com.taeyang.autocrypt.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewmodel @Inject constructor(

): ViewModel(){

    suspend fun getCenterData(){}

    suspend fun insertCenterData(){}

}