package com.taeyang.autocrypt.ui

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import com.taeyang.autocrypt.R
import com.taeyang.autocrypt.adapter.InfoAdapter
import com.taeyang.autocrypt.databinding.ActivityMapBinding
import com.taeyang.autocrypt.viewmodels.MapViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

    private lateinit var binding: ActivityMapBinding
    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource

    private val vm : MapViewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.map.onCreate(savedInstanceState)
        binding.map.getMapAsync(this)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap

        binding.myLocationBtn.setOnClickListener {
            naverMap.locationSource = locationSource
            naverMap.locationTrackingMode = LocationTrackingMode.Follow
        }

        lifecycleScope.launchWhenCreated {
            vm.getDBCenterData()
            vm.center.collect{
                if(it.isNotEmpty()){
                    it.forEach{ centerData ->

                        val infoWindow = InfoWindow()
                        infoWindow.adapter = InfoAdapter(this@MapActivity, centerData)

                        val cameraUpdate = CameraUpdate.scrollTo(LatLng(centerData.lat.toDouble(), centerData.lng.toDouble()))
                            .animate(CameraAnimation.Easing, 2000)
                            .reason(1000)

                        val listener = Overlay.OnClickListener { overlay ->
                            val marker = overlay as Marker

                            if (marker.infoWindow == null) {
                                // 현재 마커에 정보 창이 열려있지 않을 경우 엶
                                infoWindow.open(marker)
                                naverMap.moveCamera(cameraUpdate)
                            } else {
                                // 이미 현재 마커에 정보 창이 열려있을 경우 닫음
                                infoWindow.close()
                            }
                            true
                        }

                        val marker = Marker()
                        marker.position = LatLng(centerData.lat.toDouble(), centerData.lng.toDouble())
                        marker.icon = MarkerIcons.BLACK
                        marker.iconTintColor = if(centerData.centerType == "지역") Color.RED else Color.BLACK
                        marker.onClickListener = listener
                        marker.map = naverMap
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions,
                grantResults)) {
            if (!locationSource.isActivated) { // 권한 거부됨
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onStart() {
        super.onStart()
        binding.map.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.map.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.map.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.map.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        binding.map.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.map.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.map.onLowMemory()
    }
}