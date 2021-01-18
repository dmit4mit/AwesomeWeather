package com.dmitron.bottlerocketweather.main

import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dmitron.bottlerocketweather.R
import com.dmitron.bottlerocketweather.databinding.ActivityMainBinding
import com.dmitron.bottlerocketweather.utils.executeAfter
import com.dmitron.bottlerocketweather.utils.inputMethodManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), MainUiController {
    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.executeAfter {
            viewModel = this@MainActivity.viewModel
            lifecycleOwner = this@MainActivity
        }
    }

    override fun hideKeyboard() {
        inputMethodManager().hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    override fun showKeyboard() {
        inputMethodManager().toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }
}