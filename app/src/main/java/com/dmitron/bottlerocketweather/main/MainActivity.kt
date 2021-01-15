package com.dmitron.bottlerocketweather.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.dmitron.bottlerocketweather.R
import com.dmitron.bottlerocketweather.databinding.ActivityMainBinding
import com.dmitron.bottlerocketweather.utils.executeAfter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.executeAfter {
            viewModel = this@MainActivity.viewModel
            lifecycleOwner = this@MainActivity
        }
    }
}