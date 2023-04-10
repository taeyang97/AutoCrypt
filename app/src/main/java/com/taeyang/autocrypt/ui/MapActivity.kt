package com.taeyang.autocrypt.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.taeyang.autocrypt.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
    }
}