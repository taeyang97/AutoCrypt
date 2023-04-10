package com.taeyang.autocrypt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.naver.maps.map.overlay.InfoWindow
import com.taeyang.autocrypt.database.model.DBCenterData
import com.taeyang.autocrypt.databinding.AdapterInfoBinding

class InfoAdapter(
    context: Context,
    val centers: DBCenterData
) : InfoWindow.DefaultViewAdapter(context) {

    private val binding by lazy { AdapterInfoBinding.inflate(LayoutInflater.from(context)) }

    override fun getContentView(info: InfoWindow): View {

        binding.apply {
            address.text = centers.address
            centerName.text = centers.centerName
            facilityName.text = centers.facilityName
            phoneNumber.text = centers.phoneNumber
            updateAt.text = centers.updatedAt
        }

        return binding.root
    }
}