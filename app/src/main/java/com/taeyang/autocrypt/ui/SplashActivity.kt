package com.taeyang.autocrypt.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.taeyang.autocrypt.R
import com.taeyang.autocrypt.databinding.ActivitySplashBinding
import com.taeyang.autocrypt.viewmodels.SplashViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val vm : SplashViewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, MapActivity::class.java)
        var progressVal = 0
        var insertSuccess = false

        lifecycleScope.launchWhenCreated {
            //insertSuccess통해 통신 성공 여부를 판단합니다.
            for( progress in 1..80){
                if(insertSuccess) break
                progressVal = progress
                binding.splashLodingBar.progress = progress
                delay(20)
            }
        }

        lifecycleScope.launchWhenCreated {

            var page = 1

            //재귀를 통해 api를 호출합니다.
            vm.getCenterData(1,10)
            vm.center.collect{
                if(it.data.isNotEmpty()){
                    it.data.forEach { data -> vm.insertCenters(data) }
                    if(page!=10){
                        page++
                        vm.getCenterData(page,10)
                    }else {
                        // api데이터 호출 완료 시점입니다.
                        insertSuccess = true
                        for( progress in progressVal..100){
                            progressVal = progress
                            binding.splashLodingBar.progress = progress
                            delay(4)
                        }
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }

    }
}